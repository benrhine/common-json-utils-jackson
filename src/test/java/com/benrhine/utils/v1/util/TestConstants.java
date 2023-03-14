package com.benrhine.utils.v1.util;

import java.util.UUID;

/** --------------------------------------------------------------------------------------------------------------------
 * TestConstants: TODO fill me in.
 * ------------------------------------------------------------------------------------------------------------------ */
public final class TestConstants {
    // Universally Available
    public static final String TEST = "TEST";
    public static final UUID RAND_ID = UUID.randomUUID();

    public static final String SIMPLE_JSON = "{\"id\":1,\"name\":\"John Doe\"}";
    public static final String ES_JSON_ONE = "{\"_scroll_id\":1,\"name\":\"John Doe\"}";

    private TestConstants() {
        /* LEAVE BLANK */
    }
}
