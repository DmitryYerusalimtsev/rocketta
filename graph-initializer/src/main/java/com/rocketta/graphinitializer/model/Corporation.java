package com.rocketta.graphinitializer.model;

public class Corporation implements EntityConfiguration {

    public String configure() {
        final StringBuilder schema = new StringBuilder();

        schema.append("management.makeVertexLabel(\"corporation\").make(); ");

        schema.append("PropertyKey name = management.makePropertyKey(\"id\").dataType(String.class).make(); ");
        schema.append("management.makePropertyKey(\"name\").dataType(String.class).make(); ");
        schema.append("management.makePropertyKey(\"address\").dataType(String.class).make(); ");

        schema.append("management.makeEdgeLabel(\"builds\").multiplicity(Multiplicity.MANY2ONE).make(); ");

        return schema.toString();
    }
}
