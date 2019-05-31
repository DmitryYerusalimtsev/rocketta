package com.rocketta.graphinitializer.model;

public class Device implements EntityConfiguration {

    public String configure() {
        final StringBuilder schema = new StringBuilder();

        schema.append("management.makeVertexLabel(\"device\").make(); ");

        schema.append("management.makePropertyKey(\"device_type\").dataType(String.class).make(); ");

        schema.append("management.makeEdgeLabel(\"hasTelemetry\").multiplicity(Multiplicity.MULTI).make(); ");

        return schema.toString();
    }
}
