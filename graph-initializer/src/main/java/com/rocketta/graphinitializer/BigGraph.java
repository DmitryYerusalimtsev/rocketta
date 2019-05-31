package com.rocketta.graphinitializer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;

public abstract class BigGraph implements AutoCloseable {
    protected Graph graph;
    protected GraphTraversalSource g;

    protected BigGraph(String propFileName) throws ConfigurationException {

        Configuration conf = new PropertiesConfiguration(propFileName);
        graph = GraphFactory.open(conf);
        g = graph.traversal();
    }

    public void close() throws Exception {
        try {
            if (g != null) {
                g.close();
            }
            if (graph != null) {
                graph.close();
            }
        } finally {
            g = null;
            graph = null;
        }
    }

    protected abstract void createSchema();
}
