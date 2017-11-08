package com.github.gvolpe.effects.stream

import cats.effect.IO
import fs2._

object Fs2Demo {

  val src: Stream[IO, String] = Stream.eval(IO("Hello World!"))

  val pipe: Pipe[IO, String, List[String]] = { x => x.map(_.split(" ").toList) }

  val sink: Sink[IO, List[String]] = { x => x.map(println) }

  val program: Stream[IO, Unit] = src through pipe to sink

  val ioa: IO[Unit] = program.run

}
