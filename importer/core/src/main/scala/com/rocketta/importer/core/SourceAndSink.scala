package com.rocketta.importer.core

trait Source[+MESSAGE <: Message] {
  def read(): MESSAGE
}

trait Sink[-MESSAGE <: Message] {
  def save(message: MESSAGE): Unit
}
