package com.rocketta.importer

import com.rocketta.importer.speedometer.SpeedometerProcessor
import com.rocketta.sinks.janusgraph.speedometer.SpeedometerSink

object Application extends App {
  val speedProcessor = new SpeedometerProcessor with SpeedometerSink

  speedProcessor.start()
}
