package com.rocketta.graphinitializer.model;

import org.janusgraph.core.Multiplicity;
import org.janusgraph.core.schema.JanusGraphManagement;

public class Device implements EntityConfiguration {

    public void configure(JanusGraphManagement management) {
        management.makeVertexLabel("device").make();

        management.makePropertyKey("id").dataType(String.class).make();
        management.makePropertyKey("name").dataType(String.class).make();
        management.makePropertyKey("device_type").dataType(DeviceType.class).make();

        management.makeEdgeLabel("hasTelemetry").multiplicity(Multiplicity.ONE2ONE).make();
    }
}
