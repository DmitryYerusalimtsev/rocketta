package com.rocketta.graphinitializer.model;

import org.janusgraph.core.schema.JanusGraphManagement;

public interface EntityConfiguration {
    void configure(final JanusGraphManagement management);
}
