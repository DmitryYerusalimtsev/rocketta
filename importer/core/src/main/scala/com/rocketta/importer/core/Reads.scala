package com.rocketta.importer.core

import com.rocketta.importer.core.model.Message

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Illegal read operation. No rule for ${SOURCE} and ${MESSAGE} found.")
trait Reads[SOURCE <: Source[MESSAGE], MESSAGE <: Message] {
  def read(source: SOURCE): MESSAGE = {

  }
}

object Reads {
  def readFrom[SOURCE <: Source[Message], MESSAGE <: Message]
  (source: SOURCE)(implicit ev: Reads[SOURCE, MESSAGE]): MESSAGE = {
    ev.read(source)
  }
}
