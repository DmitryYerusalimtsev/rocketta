package com.rocketta.graphinitializer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory;

public abstract class BigGraph implements AutoCloseable {
    Graph graph;

    BigGraph(String propFileName) throws ConfigurationException {
        Configuration conf = new PropertiesConfiguration(propFileName);
        graph = GraphFactory.open(conf);
    }

    public void close() throws Exception {
        try {
            if (graph != null) {
                graph.close();
            }
        } finally {
            graph = null;
        }
    }

    protected abstract void createSchema();
}
