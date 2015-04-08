name := """eclesia"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "commons-io" % "commons-io" % "2.3"
)