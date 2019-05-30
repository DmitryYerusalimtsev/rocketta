package com.rocketta.importer.kafkastreams.serde

import com.rocketta.importer.core.Message
import org.apache.kafka.common.serialization.{Serde, Serdes}

import scala.reflect.ClassTag

trait JsonSerde[M <: Message] {

  protected def jsonSerde(implicit ev: ClassTag[M]): Serde[M] = {
    val serializer = new JsonSerializer[M]
    val deserializer = new JsonDeserializer[M]
    Serdes.serdeFrom(serializer, deserializer)
  }
}
