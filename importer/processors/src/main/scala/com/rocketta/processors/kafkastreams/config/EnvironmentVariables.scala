package com.rocketta.processors.kafkastreams.config

trait EnvironmentVariables {

  val kafkaStreams = new {
    val bootstrapServers: String = sys.env("KAFKASTREAMS_BOOTSTRAP_SERVERS")
  }
}
