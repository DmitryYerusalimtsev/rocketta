package com.rocketta.importer.kafkastreams.serde

import java.util

import com.google.gson.Gson
import org.apache.kafka.common.serialization.Deserializer

class JsonDeserializer[T] extends Deserializer[T] {

  val gson = new Gson()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): T = {
    data match {
      case null => new T
      case _ => {
        val json = data.map(_.toChar).mkString
        gson.fromJson(json, classOf[T])
      }
    }
  }

  override def close(): Unit = {}
}