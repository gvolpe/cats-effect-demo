import Dependencies._

lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.github.gvolpe",
      scalaVersion := "2.12.3",
      version := "0.1.0-SNAPSHOT",
      scalacOptions := Seq("-feature", "-language:higherKinds")
    )
  ),
  name := "cats-effect-demo",
  libraryDependencies ++= Seq(
    catsEffect,
    scalaTest % Test
  )
)
