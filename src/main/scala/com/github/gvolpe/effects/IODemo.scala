package com.github.gvolpe.effects

import cats.effect.IO

object IODemo extends IOApp with EffectDemo with AsyncDemo with SyncDemo with LiftIODemo {

  override def run: IO[Unit] = {
    // delay[IO]
    // suspend[IO]

    //  val result: Future[String] = liftIO[Future, String](io)
    //
    //  println(result)

    //  shiftingProgram[IO]

    val io: IO[String] = IO("Hello World!")
    runAsync(io)

    //  implicit val ec: ExecutionContext = scala.concurrent.ExecutionContext.Implicits.global
    //
    //  val result: String = async[IO].unsafeRunSync()
    //  println(result)
  }

}
