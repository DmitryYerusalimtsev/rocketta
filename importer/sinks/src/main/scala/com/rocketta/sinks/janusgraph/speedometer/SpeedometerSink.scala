package com.rocketta.sinks.janusgraph.speedometer

import com.rocketta.importer.core.Sink
import com.rocketta.importer.core.messages.Speed
import com.rocketta.sinks.janusgraph.BigGraph
import gremlin.scala._

trait SpeedometerSink extends BigGraph with Sink[Speed] {

  override def save(message: Speed): Unit = {
    try {
      val id = Key[String]("id")
      val model = Key[String]("model")
      val telemetry = Key(message.timestamp.toString) -> message.value

      val rocket = g.V().has(model, "Falcon9").has(id, message.deviceId).head()
      g.V(rocket).addE("hasTelemetry", telemetry).to(rocket).head()

      // rocket --- ("hasTelemetry", telemetry) --> rocket
    }
    catch {
      case e: NoSuchElementException => logger.warn(s"Rocker with id: ${message.deviceId} does not exist.", e)
      case e: Exception => {
        println(e.getMessage)
        logger.error(s"Error occurred during processing request for rocket id: ${message.deviceId}", e)
      }
    }
  }
}
