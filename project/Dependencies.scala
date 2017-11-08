import sbt._

object Dependencies {

  lazy val Http4sVersion  = "0.18.0-M5"
  lazy val CirceVersion   = "0.9.0-M2"
  lazy val DoobieVersion  = "0.5.0-M9"
  lazy val SttpVersion    = "1.0.2"

  lazy val catsEffect     = "org.typelevel"   %% "cats-effect"          % "0.5"

  lazy val fs2            = "co.fs2"          %% "fs2-core"             % "0.10.0-M8"

  lazy val http4sServer   = "org.http4s"      %% "http4s-blaze-server"  % Http4sVersion
  lazy val http4sSClient  = "org.http4s"      %% "http4s-blaze-client"  % Http4sVersion
  lazy val http4sDsl      = "org.http4s"      %% "http4s-dsl"           % Http4sVersion
  lazy val http4sSCirce   = "org.http4s"      %% "http4s-circe"         % Http4sVersion

  lazy val circeCore      = "io.circe"        %% "circe-core"           % CirceVersion
  lazy val circeGeneric   = "io.circe"        %% "circe-generic"        % CirceVersion

  lazy val doobieCore     = "org.tpolecat"    %% "doobie-core"          % DoobieVersion
  lazy val doobieH2       = "org.tpolecat"    %% "doobie-h2"            % DoobieVersion

  lazy val sttp           = "com.softwaremill.sttp" %% "core"                           % SttpVersion
  lazy val sttpCats       = "com.softwaremill.sttp" %% "async-http-client-backend-cats" % SttpVersion

  lazy val logback        = "ch.qos.logback"  % "logback-classic"       % "1.2.1"
  lazy val scalaTest      = "org.scalatest"   %% "scalatest"            % "3.0.3"

}
