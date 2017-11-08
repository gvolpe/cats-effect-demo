import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.github.gvolpe",
      scalaVersion := "2.12.3",
      version := "0.1.0-SNAPSHOT",
      scalacOptions := Seq(
        "-deprecation",
        "-encoding",
        "UTF-8",
        "-feature",
        "-language:existentials",
        "-language:higherKinds",
        "-Ypartial-unification"
      )
    )
  ),
  name := "cats-effect-demo",
  libraryDependencies ++= Seq(
    catsEffect,
    fs2,
    http4sServer,
    http4sSClient,
    http4sDsl,
    http4sSCirce,
    circeCore,
    circeGeneric,
    doobieCore,
    doobieH2,
    sttp,
    sttpCats,
    scalaTest % Test
  )
)
