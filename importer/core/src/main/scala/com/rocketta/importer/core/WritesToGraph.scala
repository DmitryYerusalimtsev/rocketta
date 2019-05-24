package com.rocketta.importer.core

import com.rocketta.importer.core.model.Message

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Illegal write operation. No rule for ${SOURCE} and ${MESSAGE} found.")
trait WritesToGraph[SINK <: Sink[MESSAGE], MESSAGE <: Message] {

  def write(sink: SINK, message: MESSAGE): Unit = {

  }
}

object WritesToGraph {

  def readFrom[SINK <: Sink[Message], MESSAGE <: Message]
  (sink: SINK, message: MESSAGE)(implicit ev: WritesToGraph[SINK, MESSAGE]): Unit = {
    ev.write(sink, message)
  }
}
