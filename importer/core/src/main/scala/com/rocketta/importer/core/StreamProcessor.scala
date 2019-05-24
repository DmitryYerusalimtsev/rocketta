package com.rocketta.importer.core

trait StreamProcessor[M <: Message] {
  def start(): Unit
}