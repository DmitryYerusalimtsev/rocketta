package com.rocketta.importer.graph

import com.rocketta.importer.core.Sink
import com.rocketta.importer.speedometer.Speed

trait TestSink extends Sink[Speed] {
  override def save(message: Speed): Unit = println("YAHOOO!!!")
}
