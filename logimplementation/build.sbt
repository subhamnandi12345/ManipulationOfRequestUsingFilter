name := """logimplementation"""
organization := "com.logimplementation"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-slf4j" % "2.6.15",
  "ch.qos.logback" % "logback-classic" % "1.2.6"
)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.logimplementation.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.logimplementation.binders._"
