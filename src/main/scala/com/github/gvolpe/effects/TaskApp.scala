package com.github.gvolpe.effects

import monix.eval.Task
import monix.execution.Scheduler

import scala.util.{Failure, Success}

trait TaskApp {
  implicit val S: Scheduler = monix.execution.Scheduler.Implicits.global
  def run: Task[Unit]
  def main(args: Array[String]): Unit = run.runAsync.onComplete {
    case Success(_) => ()
    case Failure(e) => e.printStackTrace()
  }
}
