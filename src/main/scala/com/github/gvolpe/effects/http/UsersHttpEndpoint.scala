package com.github.gvolpe.effects.http

import cats.effect._
import cats.syntax.applicativeError._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.client.dsl.Http4sClientDsl
import org.http4s.dsl.io._

import scala.concurrent.TimeoutException

case class User(username: String, email: String)

class UsersHttpEndpoint extends Http4sClientDsl[IO] {

  val usersErrorHandler: PartialFunction[Throwable, IO[Response[IO]]] = {
    case e: TimeoutException => InternalServerError(e.getMessage)
  }

  val service: HttpService[IO] = HttpService[IO] {
    case GET -> Root / "users" =>
      val users: IO[List[User]] = UserService.findAll()
      users.flatMap(u => Ok(u.asJson)).recoverWith(usersErrorHandler)
  }

}

object UserService {

  def findAll(): IO[List[User]] = IO {
    List(User("gvolpe", "gvolpe@github.com"), User("root", "root@unix.org"))
  }

}
