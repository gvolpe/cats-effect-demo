package com.github.gvolpe.effects.persistence

import cats.effect.IO
import doobie.free.connection.ConnectionIO
import doobie.implicits._
import doobie.util.transactor.Transactor

class DoobieDemo {

  val xa = Transactor.fromDriverManager[IO](
    "org.postgresql.Driver", "jdbc:postgresql:world", "postgres", ""
  )

  case class Country(code: String, name: String, population: Long)

  def find(n: String): ConnectionIO[Option[Country]] =
    sql"select code, name, population from country where name = $n".query[Country].option

  val ioa: IO[Option[Country]] = find("France").transact(xa)

}
