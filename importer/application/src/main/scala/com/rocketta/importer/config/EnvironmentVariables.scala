package com.rocketta.importer.config

trait EnvironmentVariables {

  val kafkaStreams = new {
    val bootstrapServers: String = sys.env("KAFKASTREAMS_BOOTSTRAP_SERVERS")
  }
}
