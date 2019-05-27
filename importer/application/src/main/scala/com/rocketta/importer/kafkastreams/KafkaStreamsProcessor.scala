package com.rocketta.importer.kafkastreams

import java.util.Properties

import com.rocketta.importer.config.EnvironmentVariables
import com.rocketta.importer.core.{Message, StreamProcessor}
import org.apache.kafka.streams.{KafkaStreams, StreamsBuilder, StreamsConfig}

trait KafkaStreamsProcessor[M <: Message] extends StreamProcessor[M] with EnvironmentVariables {

  protected val appId: String

  private[this] val props: Properties = {
    val p = new Properties()
    p.put(StreamsConfig.APPLICATION_ID_CONFIG, appId)
    p.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaStreams.bootstrapServers)
    p
  }

  def start(): Unit = streams().start()

  private[this] def streams() = {

    val builder: StreamsBuilder = new StreamsBuilder
    process(builder)

    val streams: KafkaStreams = new KafkaStreams(builder.build(), props)

    streams
  }

  protected def process(builder: StreamsBuilder): Unit
}
