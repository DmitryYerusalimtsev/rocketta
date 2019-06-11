name := "analytics"

lazy val commonSettings = Seq(
  organization := "com.rocketta",
  version := "1.0",
  scalaVersion := "2.12.8"
)

val sparkLibs = Seq(
  "org.apache.spark" %% "spark-core" % "2.4.3",
  "org.apache.spark" %% "spark-graphx" % "2.4.3"
)

lazy val spark = (project in file("spark"))
  .settings(
    commonSettings,

    libraryDependencies ++= sparkLibs
  )

lazy val application = (project in file("application"))
  .settings(
    commonSettings,

    libraryDependencies ++= sparkLibs
  ).dependsOn(spark)