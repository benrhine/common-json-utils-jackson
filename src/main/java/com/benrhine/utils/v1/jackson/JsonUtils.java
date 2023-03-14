package com.benrhine.utils.v1.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** --------------------------------------------------------------------------------------------------------------------
 * JsonHelpers: Helper class containing methods that do different conversions to and from JsonNode using Jackson.
 * Resources:
 * - <a href="https://attacomsian.com/blog/jackson-json-node-tree-model">jackson-json-node-tree-model</a>
 * ------------------------------------------------------------------------------------------------------------------ */
public final class JsonUtils {

    public static final String ERROR_MSG_CREATE_BLANK_JSON_NODE     = "createBlankJsonNode: Failed to create blank JsonNode";
    public static final String ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_JSON_STR = "createJsonNodeFromJsonString: Failed to create JsonNode from a JSON string";
    public static final String ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_OBJ = "createJsonNodeFromObject: Failed to create JsonNode from Object";
    public static final String ERROR_MSG_CONVERT_TO_STR             = "convertToString: Failed to create String from Object";
    public static final String ERROR_MSG_CONVERT_TO_OPTIONAL_STR    = "convertToOptionalString: Failed to create Optional String from Object";

    private JsonUtils() {
        /* LEAVE BLANK */
    }

    public static JsonNode createBlankJsonNode() {
        return createBlankJsonNode(new ObjectMapper());
    }

    public static JsonNode createBlankJsonNode(final ObjectMapper mapper) {
        try {
            mapper.findAndRegisterModules();

            return mapper.createObjectNode();
        } catch (final Exception e) {
            throw new RuntimeException(ERROR_MSG_CREATE_BLANK_JSON_NODE);
        }
    }

    public static JsonNode createJsonNodeFromJsonString(final String jsonStr) {
        return createJsonNodeFromJsonString(jsonStr, new ObjectMapper());
    }

    public static JsonNode createJsonNodeFromJsonString(final String jsonStr, final ObjectMapper mapper) {
        try {
            mapper.findAndRegisterModules();

            return mapper.readTree(jsonStr);
        } catch (final Exception e) {
            throw new RuntimeException(ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_JSON_STR);
        }
    }

    public static JsonNode createJsonNodeFromObject(final Object payload) {
        return createJsonNodeFromObject(payload, new ObjectMapper());
    }

    public static JsonNode createJsonNodeFromObject(final Object payload, final ObjectMapper mapper) {
        try {
            mapper.findAndRegisterModules();

            return mapper.valueToTree(payload);
        } catch (final Exception e) {
            throw new RuntimeException(ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_OBJ);
        }
    }

    public static String convertToString(final Object payload) {
        return convertToString(payload, new ObjectMapper());
    }

    public static String convertToString(final Object payload, final ObjectMapper mapper) {
        try {
            mapper.findAndRegisterModules();

            return mapper.writeValueAsString(payload);
        } catch (final Exception e) {
            throw new RuntimeException(ERROR_MSG_CONVERT_TO_STR);
        }
    }

    public static Optional<String> convertToOptionalString(final Object payload) {
        return convertToOptionalString(payload, new ObjectMapper());
    }

    public static Optional<String> convertToOptionalString(final Object payload, final ObjectMapper mapper) {
        try {
            mapper.findAndRegisterModules();

            return Optional.of(mapper.writeValueAsString(payload));
        } catch (final Exception e) {
            throw new RuntimeException(ERROR_MSG_CONVERT_TO_OPTIONAL_STR);
        }
    }

    public static Optional<String> convertJsonValue(final Object value) {
        if (!(value instanceof List || value instanceof Map)) { // No way to test instanceof Map unless we update getType
            return Optional.empty();
        }

        return convertToOptionalString(value);
    }
}
