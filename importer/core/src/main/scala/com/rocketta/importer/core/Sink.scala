package com.rocketta.importer.core

trait Sink[-MESSAGE <: Message] {
  def runSink(): Unit
  def save(message: MESSAGE): Unit
}
