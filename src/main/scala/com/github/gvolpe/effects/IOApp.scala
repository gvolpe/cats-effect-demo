package com.github.gvolpe.effects

import cats.effect.IO

trait IOApp {
  def run: IO[Unit]
  def main(args: Array[String]): Unit = run.runAsync(_ => IO(())).unsafeRunSync()
}
