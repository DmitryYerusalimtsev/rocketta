package com.rocketta.importer.speedometer

import com.rocketta.importer.core.Sink
import com.rocketta.importer.kafkastreams.KafkaStreamsProcessor
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream

final class SpeedometerProcessor(sink: Sink[Speed]) extends KafkaStreamsProcessor[Speed] {
  protected val appId: String = "speedometer-processor"
  private[this] val topic: String = "speedometer-telemetry"

  override protected def process(builder: StreamsBuilder): KStream[String, Speed] = {
    val speed = builder.stream[String, Speed](topic)

    speed.foreach(st => save(st))
  }
}
