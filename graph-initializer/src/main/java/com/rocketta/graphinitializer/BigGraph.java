package com.rocketta.graphinitializer;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.structure.Graph;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;

import java.util.stream.Stream;

public abstract class BigGraph implements AutoCloseable {
    Graph graph;
    Cluster cluster;
    Client client;

    BigGraph(String propFileName) throws ConfigurationException {
        Configuration conf = new PropertiesConfiguration(propFileName);

        try {
            cluster = Cluster.open(conf.getString("gremlin.remote.driver.clusterFile"));
            client = cluster.connect();
        } catch (Exception e) {
            throw new ConfigurationException(e);
        }

        graph = EmptyGraph.instance();
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

    void defineSchema() {
        final String req = schemaDefinition();
        final ResultSet resultSet = client.submit(req);
        Stream<Result> futureList = resultSet.stream();
        futureList.map(Result::toString).forEach(System.out::println);
    }

    protected abstract String schemaDefinition();
}
