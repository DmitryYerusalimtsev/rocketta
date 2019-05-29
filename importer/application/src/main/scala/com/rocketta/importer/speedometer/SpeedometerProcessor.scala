package com.rocketta.importer.speedometer

import com.rocketta.importer.core.Sink
import com.rocketta.importer.kafkastreams.KafkaStreamsProcessor
import com.rocketta.importer.kafkastreams.serde.JsonSerde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed

class SpeedometerProcessor extends KafkaStreamsProcessor[Speed] with JsonSerde[Speed] {
  this: Sink[Speed] =>

  protected val appId: String = "speedometer-processor"
  private[this] val topic: String = "speedometer-telemetry"

  override protected def process(builder: StreamsBuilder): Unit = {

    val speedStream = builder.stream[String, Speed](topic, Consumed.`with`(Serdes.String(), jsonSerde))
    speedStream.foreach((_, value) => save(value))
  }
}
