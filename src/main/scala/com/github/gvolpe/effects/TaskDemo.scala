package com.github.gvolpe.effects

import cats.effect.IO
import monix.eval.Task

object TaskDemo extends TaskApp with EffectDemo with AsyncDemo with SyncDemo with LiftIODemo {
  override def run: Task[Unit] = {
    //delay[Task]
    //suspend[Task

//    val task: Task[String] = Task("Hello World!")
//    val ioa: IO[Unit] = runAsync[Task, String](task)
//    ioa.to[Task]

    shiftingProgram[Task]

//    for {
//      x <- async[Task]
//      _ <- Task.delay(println(x))
//    } yield ()
  }
}
