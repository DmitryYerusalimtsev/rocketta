package com.rocketta.sinks.janusgraph

import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory

abstract class BigGraph(val propsFileName: String) extends LazyLogging with AutoCloseable {

  private[this] val conf = new PropertiesConfiguration(propsFileName)

  protected var graph: Graph = GraphFactory.open(conf)
  protected var g: GraphTraversalSource = graph.traversal()

  def initializeGraph(): Unit = {
    try {
      createSchema()
    }
    catch {
      case e: Exception => logger.error("Error occurred during graph initialization.", e)
    }
  }

  protected abstract def createSchema()

  override def close(): Unit = {
    logger.info("Closing graph.")

    try {
      if (g != null) {
        g.close()
      }
      if (graph != null) {
        graph.close()
      }
    } finally {
      g = null
      graph = null
    }
  }
}
