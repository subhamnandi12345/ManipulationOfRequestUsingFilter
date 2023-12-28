name := """playAPI"""
organization := "com.playAPI"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.20"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "5.0.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.playAPI.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.playAPI.binders._"
