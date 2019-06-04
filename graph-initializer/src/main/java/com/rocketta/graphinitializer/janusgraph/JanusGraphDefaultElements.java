package com.rocketta.graphinitializer.janusgraph;

import com.rocketta.graphinitializer.BigGraph;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.tinkerpop.gremlin.process.traversal.Bindings;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import static com.rocketta.graphinitializer.janusgraph.JgBindings.*;

public class JanusGraphDefaultElements extends BigGraph {

    private final Bindings b = Bindings.instance();

    public JanusGraphDefaultElements(String propFileName) throws ConfigurationException {
        super(propFileName);
    }

    public void create() {
        Vertex spaceX = g.addV(b.of(LABEL, "corporation"))
                .property(ID, b.of(ID, "2cc829e4-2bee-410a-a551-c49defc967af"))
                .property(NAME, b.of(NAME, "SpaceX"))
                .property(ADDRESS, b.of(ADDRESS, "USA, Redmond, WA")).next();

        Vertex falcon9 = g.addV(b.of(LABEL, "rocket"))
                .property(ID, b.of(ID, "0051bbf5-8e18-4fab-8cc5-7bcc65128601"))
                .property(MODEL, b.of(MODEL, "Falcon9")).next();

        Vertex speedometer = g.addV(b.of(LABEL, "device"))
                .property(ID, b.of(ID, "9010cede-520c-432a-942a-66c8a715d920"))
                .property(DEVICE_TYPE, b.of(DEVICE_TYPE, "speedometer")).next();

        g.V(b.of(OUT_V, falcon9)).as("a")
                .V(b.of(IN_V, spaceX))
                .addE(b.of(LABEL, "builtBy")).from("a").next();

        g.V(b.of(OUT_V, falcon9)).as("a")
                .V(b.of(IN_V, speedometer))
                .addE(b.of(LABEL, "contains")).from("a").next();
    }
}
