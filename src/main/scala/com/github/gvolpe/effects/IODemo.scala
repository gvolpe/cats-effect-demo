package com.github.gvolpe.effects

import cats.effect.IO
import monix.execution.Scheduler

import scala.util.{Failure, Success}

object IODemo extends IOApp with EffectDemo with AsyncDemo with SyncDemo with LiftIODemo {

  override def run: IO[Unit] = {
    // delay[IO]
    // suspend[IO]

    val ioa = IO { "Hey!" }
    val result: MyEffect[String] = liftIO[MyEffect, String](ioa)

    val S: Scheduler = monix.execution.Scheduler.Implicits.global

    result.runAsync(S).onComplete {
      case Success(x) => println(x)
      case Failure(e) => e.printStackTrace()
    }

    ioa.map(_=> ())

    //  val result: Future[String] = liftIO[Future, String](io)
    //
    //  println(result)

    //  shiftingProgram[IO]

//    val io: IO[String] = IO("Hello World!")
//    runAsync(io)

    //  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
    //
    //  val result: String = async[IO].unsafeRunSync()
    //  println(result)
  }

}
