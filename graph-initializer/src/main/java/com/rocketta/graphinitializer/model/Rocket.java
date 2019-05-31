package com.rocketta.graphinitializer.model;

import org.janusgraph.core.Multiplicity;
import org.janusgraph.core.schema.JanusGraphManagement;

public class Rocket implements EntityConfiguration {

    public void configure(JanusGraphManagement management) {
        management.makeVertexLabel("rocket").make();

        management.makePropertyKey("id").dataType(String.class).make();
        management.makePropertyKey("name").dataType(String.class).make();
        management.makePropertyKey("model").dataType(RocketModel.class).make();

        management.makeEdgeLabel("contains").multiplicity(Multiplicity.ONE2ONE).make();
    }
}

