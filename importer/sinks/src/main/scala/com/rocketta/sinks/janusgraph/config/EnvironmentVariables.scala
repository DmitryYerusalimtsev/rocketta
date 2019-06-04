package com.rocketta.sinks.janusgraph.config

trait EnvironmentVariables {

  val janusGraph = new {
    val propsFilePath: String = sys.env("JANUSGRAPH_PROPS_FILE_PATH")
  }
}