package com.rocketta.importer.kafkastreams.serde

import com.rocketta.importer.core.Message
import org.apache.kafka.common.serialization.{Serde, Serdes}

trait JsonSerde[M <: Message] {
  protected val jsonSerde: Serde[M] = {
    val serializer = new JsonSerializer[M]
    val deserializer = new JsonDeserializer[M]
    Serdes.serdeFrom(serializer, deserializer)
  }
}
