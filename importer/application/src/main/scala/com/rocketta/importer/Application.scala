package com.rocketta.importer

import com.rocketta.importer.speedometer.SpeedometerProcessor

object Application extends App {
  val speedProcessor = new SpeedometerProcessor with TestSink

  speedProcessor.start()
}
