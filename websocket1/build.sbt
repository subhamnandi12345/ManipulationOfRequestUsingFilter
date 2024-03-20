name := """websocket1"""
organization := "com.websocket1"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.13"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-ws" % "2.8.11"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.websocket1.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.websocket1.binders._"
