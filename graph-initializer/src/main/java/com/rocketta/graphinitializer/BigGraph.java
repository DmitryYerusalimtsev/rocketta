package com.rocketta.graphinitializer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;

public abstract class BigGraph implements AutoCloseable {
    private Cluster cluster;
    Client client;
    protected Graph graph;
    protected GraphTraversalSource g;

    protected BigGraph(String propFileName) throws ConfigurationException {
        Configuration conf = new PropertiesConfiguration(propFileName);

        try {
            cluster = Cluster.open(conf.getString("gremlin.remote.driver.clusterFile"));
            client = cluster.connect();
        } catch (Exception e) {
            throw new ConfigurationException(e);
        }

        graph = EmptyGraph.instance();
        g = graph.traversal().withRemote(conf);
    }

    public void close() throws Exception {
        try {
            if (g != null) {
                g.close();
            }
            if (cluster != null) {
                cluster.close();
            }
        } finally {
            graph = null;
            g = null;
            cluster = null;
        }
    }
}
