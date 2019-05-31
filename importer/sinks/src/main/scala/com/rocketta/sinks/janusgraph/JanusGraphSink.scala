package com.rocketta.sinks.janusgraph

import com.rocketta.importer.core.{Message, Sink}
import org.janusgraph.core.JanusGraph

abstract class JanusGraphSink[M <: Message](propsFileName: String)
  extends BigGraph(propsFileName) with Sink[M] {

  val janusgraph: JanusGraph = graph.asInstanceOf[JanusGraph]

  def runSink(): Unit = {
    initializeGraph()
  }
}
