package com.rocketta.importer.core

trait Sink[-MESSAGE <: Message] {
  def save(message: MESSAGE): Unit
}
