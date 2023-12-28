name := """scalaplay"""
organization := "com.scalaplay"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

//libraryDependencies += guice
//libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
//libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.20"
libraryDependencies ++= Seq(
  guice,
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test,
  jdbc,
  "mysql" % "mysql-connector-java" % "8.0.27",
  "com.typesafe.play" %% "play-slick" % "5.2.0-M3",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.2.0-M3" % Test
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.scalaplay.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.scalaplay.binders._"
