name := "importer"

lazy val commonSettings = Seq(
  organization := "com.rocketta",
  version := "1.0",
  scalaVersion := "2.12.8"
)

val janusgraphVersion = "0.3.0"
val janusgraph = Seq(
  "org.janusgraph" % "janusgraph-core" % janusgraphVersion,
  "org.janusgraph" % "janusgraph-berkeleyje" % janusgraphVersion,
  "org.janusgraph" % "janusgraph-lucene" % janusgraphVersion,
  "org.apache.tinkerpop" % "gremlin-server" % "3.3.3"
)

val scalaTest = Seq(
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5" % Test
)

lazy val core = (project in file("core"))
  .settings(
    commonSettings,

    libraryDependencies ++= janusgraph ++ Seq(
      "com.google.code.gson" % "gson" % "2.8.5"
    )
  )

lazy val application = (project in file("application"))
  .settings(
    commonSettings,

    libraryDependencies ++= scalaTest ++ Seq(
      "org.apache.kafka" % "kafka-streams" % "2.2.0"
    )
  ).dependsOn(core)