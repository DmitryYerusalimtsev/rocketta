package com.rocketta.importer.core

import com.typesafe.scalalogging.LazyLogging

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Illegal write operation. No rule for ${SOURCE} and ${MESSAGE} found.")
trait Writes[SINK <: Sink[MESSAGE], MESSAGE <: Message] extends LazyLogging {

  def write(sink: SINK, message: MESSAGE): Unit = {
    try {
      sink.save(message)
    }
    catch {
      case e: Exception => logger.error(s"Error occurred during saving to ${classOf[SINK]}", e)
    }
  }
}

object Writes {
  def readFrom[SINK <: Sink[Message], MESSAGE <: Message]
  (sink: SINK, message: MESSAGE)(implicit ev: Writes[SINK, MESSAGE]): Unit = {
    ev.write(sink, message)
  }
}
