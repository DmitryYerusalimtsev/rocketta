package com.rocketta.graphinitializer;

import com.rocketta.graphinitializer.janusgraph.JanusGraphDefaultElements;
import com.rocketta.graphinitializer.janusgraph.JanusGraphInitializer;

public class Application {
    public static void main(String[] args) {
        final String fileName = (args != null && args.length > 0) ? args[0] : null;
        initializeSchema(fileName);
    }

    private static void initializeSchema(final String propFileName) {
        try (JanusGraphInitializer initializer = new JanusGraphInitializer(propFileName);
             JanusGraphDefaultElements elements = new JanusGraphDefaultElements(propFileName)) {

            initializer.defineSchema();
            elements.create();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
