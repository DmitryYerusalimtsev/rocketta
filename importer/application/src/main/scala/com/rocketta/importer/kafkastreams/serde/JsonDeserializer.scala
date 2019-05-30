package com.rocketta.importer.kafkastreams.serde

import java.util

import com.google.gson.Gson
import org.apache.kafka.common.serialization.Deserializer

import scala.reflect.ClassTag
import scala.reflect._

class JsonDeserializer[T : ClassTag] extends Deserializer[T] {

  val gson = new Gson()

  override def configure(configs: util.Map[String, _], isKey: Boolean): Unit = {}

  override def deserialize(topic: String, data: Array[Byte]): T = {
    val json = data.map(_.toChar).mkString
    gson.fromJson(json, classTag[T].runtimeClass)
  }

  override def close(): Unit = {}
}
