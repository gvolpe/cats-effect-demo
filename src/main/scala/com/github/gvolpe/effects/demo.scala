package com.github.gvolpe.effects

import cats.effect._

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}

trait EffectDemo {

  def runAsync[F[_] : Effect, A](task: F[A]): IO[Unit] =
    Effect[F].runAsync(task) {
      case Right(value) => IO(println(value))
      case Left(error)  => IO.raiseError(error)
    }

}

trait AsyncDemo {

  private val futureOfString = Future.successful("Hi, I come from the Future!")

  // Useful for integration with callback-based APIs
  def async[F[_] : Async](implicit ec: ExecutionContext): F[String] =
    Async[F].async { cb =>
      import scala.util.{Failure, Success}

      futureOfString.onComplete {
        case Success(value) => cb(Right(value))
        case Failure(error) => cb(Left(error))
      }
    }

  import java.util.concurrent.Executors
  private val cachedThreadPool = Executors.newCachedThreadPool()
  private val BlockingFileIO = ExecutionContext.fromExecutor(cachedThreadPool)
  implicit val Main: ExecutionContextExecutor = ExecutionContext.global

  import cats.syntax.flatMap._
  import cats.syntax.functor._

  def shiftingProgram[F[_] : Async]: F[Unit] =
    for {
      _     <- Sync[F].delay { println("Enter your name: ")}
      _     <- Async[F].shift(BlockingFileIO)
      name  <- Sync[F].delay { scala.io.StdIn.readLine() }
      _     <- Async[F].shift
      _     <- Sync[F].delay { println(s"Welcome $name!") }
      _     <- Sync[F].delay(cachedThreadPool.shutdown())
    } yield ()

}

trait SyncDemo {

  def delay[F[_] : Sync]: F[Unit] = Sync[F].delay {
    println("delay(A) === suspend(F[A])")
  }

  def suspend[F[_] : Sync]: F[Unit] = Sync[F].suspend {
    Sync[F].pure(println("suspend(F[A])"))
  }

}

trait LiftIODemo {

  implicit def listLiftIO: LiftIO[List] =
    new LiftIO[List] {
      override def liftIO[A](ioa: IO[A]): List[A] = {
        ioa.attempt.unsafeRunSync.fold(_ => List.empty[A], List(_))
      }
    }

  implicit def futureLiftIO: LiftIO[Future] =
    new LiftIO[Future] {
      override def liftIO[A](ioa: IO[A]): Future[A] =
        ioa.unsafeToFuture()
    }

  def liftIO[F[_] : LiftIO, A](ioa: IO[A]): F[A] =
    LiftIO[F].liftIO(ioa)
}
