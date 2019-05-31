package com.rocketta.graphinitializer;

import org.apache.commons.configuration.ConfigurationException;

public class Application {
    public static void main(String[] args) {
        final String fileName = (args != null && args.length > 0) ? args[0] : null;
        initializeSchema(fileName);
    }

    private static void initializeSchema(final String propFileName) {
        try {
            final JanusGraphInitializer initializer = new JanusGraphInitializer(propFileName);
            initializer.defineSchema();
        } catch (ConfigurationException e) {
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
