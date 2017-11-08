package com.github.gvolpe.effects.http

import cats.effect.IO
import com.softwaremill.sttp._
import com.softwaremill.sttp.asynchttpclient.cats.AsyncHttpClientCatsBackend

object SttpIODemo {

  implicit val backend: SttpBackend[IO, Nothing] = AsyncHttpClientCatsBackend[IO]()
  val ioa: IO[Response[String]] = sttp.get(uri"http://httpbin.org/ip").send()

}
