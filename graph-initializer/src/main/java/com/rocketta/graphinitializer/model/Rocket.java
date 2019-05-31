package com.rocketta.graphinitializer.model;

public class Rocket implements EntityConfiguration {

    public String configure() {
        final StringBuilder schema = new StringBuilder();

        schema.append("management.makeVertexLabel(\"rocket\").make(); ");

        schema.append("management.makePropertyKey(\"model\").dataType(String.class).make(); ");

        schema.append("management.makeEdgeLabel(\"contains\").multiplicity(Multiplicity.MULTI).make(); ");

        return schema.toString();
    }
}
