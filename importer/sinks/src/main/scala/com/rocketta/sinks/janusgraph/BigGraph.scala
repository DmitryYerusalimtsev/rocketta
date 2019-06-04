package com.rocketta.sinks.janusgraph

import com.rocketta.sinks.janusgraph.config.EnvironmentVariables
import com.typesafe.scalalogging.LazyLogging
import gremlin.scala._
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph

trait BigGraph extends EnvironmentVariables with LazyLogging {

  private[this] val conf = new PropertiesConfiguration(janusGraph.propsFilePath)

  implicit val graph: ScalaGraph = EmptyGraph.instance.asScala.configure(_.withRemote(conf))
  protected val g: TraversalSource = graph.traversal
}
