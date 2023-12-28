name := """doubleparsing"""
organization := "com.doubleparsing"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.10.0"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.doubleparsing.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.doubleparsing.binders._"
