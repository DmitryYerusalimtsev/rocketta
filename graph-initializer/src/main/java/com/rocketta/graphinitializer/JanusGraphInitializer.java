package com.rocketta.graphinitializer;

import com.rocketta.graphinitializer.model.EntityConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.janusgraph.core.JanusGraph;
import org.janusgraph.core.RelationType;
import org.janusgraph.core.schema.JanusGraphManagement;
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
    protected void createSchema() {
        final JanusGraphManagement management = getJanusGraph().openManagement();

        try {
            // naive check if the schema was previously created
            if (management.getRelationTypes(RelationType.class).iterator().hasNext()) {
                management.rollback();
                return;
            }

            configureGraph(management);
            management.commit();
        } catch (Exception e) {
            management.rollback();
        }
    }

    private void configureGraph(final JanusGraphManagement management) {
        Reflections reflections = new Reflections("com.rocketta.graphinitializer");
        Set<Class<? extends EntityConfiguration>> classes = reflections.getSubTypesOf(EntityConfiguration.class);

        classes.forEach(c -> {
            try {
                EntityConfiguration entity = c.newInstance();
                entity.configure(management);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
