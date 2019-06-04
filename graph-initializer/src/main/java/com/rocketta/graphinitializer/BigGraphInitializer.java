package com.rocketta.graphinitializer;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

import java.util.stream.Stream;

public abstract class BigGraphInitializer extends BigGraph {

    protected BigGraphInitializer(String propFileName) throws ConfigurationException {
        super(propFileName);
    }

    public void defineSchema() {
        final String req = schemaDefinition();
        final ResultSet resultSet = client.submit(req);
        Stream<Result> futureList = resultSet.stream();
        futureList.map(Result::toString).forEach(System.out::println);
    }

    protected abstract String schemaDefinition();
}
