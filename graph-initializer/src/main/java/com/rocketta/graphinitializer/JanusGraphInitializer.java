package com.rocketta.graphinitializer;

import com.rocketta.graphinitializer.model.EntityConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.janusgraph.core.JanusGraph;
import org.reflections.Reflections;

import java.util.Set;

public class JanusGraphInitializer extends BigGraph {

    JanusGraphInitializer(String propFileName) throws ConfigurationException {
        super(propFileName);
    }

    private JanusGraph getJanusGraph() {
        return (JanusGraph) graph;
    }

    @Override
    protected String schemaDefinition() {
        Reflections reflections = new Reflections("com.rocketta.graphinitializer");
        Set<Class<? extends EntityConfiguration>> classes = reflections.getSubTypesOf(EntityConfiguration.class);

        final StringBuilder request = new StringBuilder();

        request.append("JanusGraphManagement management = graph.openManagement(); ");
        request.append("boolean created = false; ");

        // naive check if the schema was previously created
        request.append("if (management.getRelationTypes(RelationType.class).iterator().hasNext()) { management.rollback(); created = false; } else { ");

        classes.forEach(c -> {
            try {
                EntityConfiguration entity = c.newInstance();
                String schema = entity.configure();
                request.append(schema).append(" ");

            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        request.append("management.commit(); created = true; }");
        return request.toString();
    }
}
