package com.rocketta.graphinitializer.model;

public class Common implements EntityConfiguration {

    @Override
    public String configure() {
        final StringBuilder schema = new StringBuilder();

        schema.append("management.makePropertyKey(\"id\").dataType(String.class).make(); ");
        schema.append("management.makePropertyKey(\"name\").dataType(String.class).make(); ");

        return schema.toString();
    }
}
