package com.rocketta.importer.core

trait WithSinkFor[M <: Message] {
  this: Sink[M] =>
  {
    def writeTo(message: M): Unit = save(message)
  }
}