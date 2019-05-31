package com.rocketta.graphinitializer.model;

import org.janusgraph.core.Multiplicity;
import org.janusgraph.core.schema.JanusGraphManagement;

public class Corporation implements EntityConfiguration {

    public void configure(JanusGraphManagement management) {
        management.makeVertexLabel("corporation").make();

        management.makePropertyKey("id").dataType(String.class).make();
        management.makePropertyKey("name").dataType(String.class).make();
        management.makePropertyKey("address").dataType(String.class).make();

        management.makeEdgeLabel("builds").multiplicity(Multiplicity.MANY2ONE).make();
    }
}

