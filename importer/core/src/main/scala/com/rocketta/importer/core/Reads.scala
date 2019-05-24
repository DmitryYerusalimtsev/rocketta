package com.rocketta.importer.core

import scala.annotation.implicitNotFound

@implicitNotFound(msg = "Illegal read operation. No rule for ${SOURCE} and ${MESSAGE} found.")
trait Reads[SOURCE <: Source[MESSAGE], MESSAGE <: Message] {
  def read(source: SOURCE): MESSAGE = {
    source.read()
  }
}

object Reads {
  def readFrom[SOURCE <: Source[Message], MESSAGE <: Message]
  (source: SOURCE)(implicit ev: Reads[SOURCE, MESSAGE]): MESSAGE = {
    ev.read(source)
  }
}
