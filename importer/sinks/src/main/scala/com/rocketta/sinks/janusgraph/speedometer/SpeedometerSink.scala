package com.rocketta.sinks.janusgraph.speedometer

import com.rocketta.importer.core.messages.Speed
import com.rocketta.sinks.janusgraph.JanusGraphSink

class SpeedometerSink(implicit propsFileName: String) extends JanusGraphSink[Speed](propsFileName) {

  override protected def createSchema(): Unit = {

  }

  override def save(message: Speed): Unit = ???
}
