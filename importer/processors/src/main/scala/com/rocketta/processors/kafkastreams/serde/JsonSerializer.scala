package com.rocketta.processors.kafkastreams.serde

import java.util

import com.google.gson.Gson
import org.apache.kafka.common.serialization.Serializer

class JsonSerializer[T] extends Serializer[T] {

  val gson = new Gson()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def serialize(topic: String, data: T): Array[Byte] = {
    data match {
      case null => null
      case dat => {
        val json = gson.toJson(dat)
        json.getBytes()
      }
    }
  }

  override def close(): Unit = {}
}
