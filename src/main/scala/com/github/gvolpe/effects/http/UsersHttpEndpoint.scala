package com.github.gvolpe.effects.http

import cats.Applicative
import cats.effect._
import cats.syntax.applicative._
import cats.syntax.applicativeError._
import cats.syntax.flatMap._
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

import scala.concurrent.TimeoutException

case class User(username: String, email: String)

object UsersHttpEndpoint extends UsersHttpEndpoint[IO](new UserService[IO])

class UsersHttpEndpoint[F[_] : Effect](userService: UserService[F]) extends Http4sDsl[F] {

  val usersErrorHandler: PartialFunction[Throwable, F[Response[F]]] = {
    case e: TimeoutException => InternalServerError(e.getMessage)
  }

  val service: HttpService[F] = HttpService[F] {
    case GET -> Root / "users" =>
      val users: F[List[User]] = userService.findAll()
      users.flatMap(u => Ok(u.asJson)).recoverWith(usersErrorHandler)
  }

}

class UserService[F[_] : Applicative] {

  def findAll(): F[List[User]] = {
    List(User("gvolpe", "gvolpe@github.com"), User("root", "root@unix.org")).pure[F]
  }

}
