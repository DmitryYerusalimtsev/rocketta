name := "importer"

lazy val commonSettings = Seq(
  organization := "com.rocketta",
  version := "1.0",
  scalaVersion := "2.12.8"
)

val janusgraphVersion = "0.3.0"
val janusgraph = Seq(
  "org.janusgraph" % "janusgraph-core" % janusgraphVersion,
  "org.apache.tinkerpop" % "gremlin-driver" % "3.3.3",
  "com.michaelpollmeier" %% "gremlin-scala" % "3.4.0.4"
)

val scalaTest = Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

val logging = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,

    libraryDependencies ++= logging ++ Seq(
      "com.google.code.gson" % "gson" % "2.8.5"
    )
  )

lazy val processors = (project in file("processors"))
  .settings(
    commonSettings,

    libraryDependencies ++= logging ++ Seq(
      "org.apache.kafka" % "kafka-streams" % "2.2.0",
      "com.google.code.gson" % "gson" % "2.8.5"
    )
  ).dependsOn(core)

lazy val sinks = (project in file("sinks"))
  .settings(
    commonSettings,

    libraryDependencies ++= logging ++ janusgraph
  ).dependsOn(core)

lazy val application = (project in file("application"))
  .settings(
    commonSettings
  ).dependsOn(core, processors, sinks)