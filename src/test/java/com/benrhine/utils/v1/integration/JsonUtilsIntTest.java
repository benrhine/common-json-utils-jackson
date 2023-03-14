package com.benrhine.utils.v1.integration;

import static com.benrhine.utils.v1.jackson.JsonUtils.ERROR_MSG_CONVERT_TO_OPTIONAL_STR;
import static com.benrhine.utils.v1.jackson.JsonUtils.ERROR_MSG_CONVERT_TO_STR;
import static com.benrhine.utils.v1.jackson.JsonUtils.ERROR_MSG_CREATE_BLANK_JSON_NODE;
import static com.benrhine.utils.v1.jackson.JsonUtils.ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_JSON_STR;
import static com.benrhine.utils.v1.jackson.JsonUtils.ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_OBJ;
import static com.benrhine.utils.v1.jackson.JsonUtils.convertJsonValue;
import static com.benrhine.utils.v1.jackson.JsonUtils.convertToOptionalString;
import static com.benrhine.utils.v1.jackson.JsonUtils.convertToString;
import static com.benrhine.utils.v1.jackson.JsonUtils.createBlankJsonNode;
import static com.benrhine.utils.v1.jackson.JsonUtils.createJsonNodeFromJsonString;
import static com.benrhine.utils.v1.jackson.JsonUtils.createJsonNodeFromObject;
import static com.benrhine.utils.v1.util.TestConstants.RAND_ID;
import static com.benrhine.utils.v1.util.TestConstants.SIMPLE_JSON;
import static com.benrhine.utils.v1.util.TestConstants.TEST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Tag("all-test")
@Tag("int")
final class JsonUtilsIntTest {

    @Mock
    private ObjectMapper mockMapper;
    private static ObjectMapper mapper;

    @BeforeAll
    static void setupClass() {
        mapper = new ObjectMapper();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Happy Path
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testCreateBlankJsonNode() {
        // When
        final JsonNode node = createBlankJsonNode();
        // Then
        assertNotNull(node);
    }

    @Test
    void testCreateJsonNodeFromJsonString() {
        // When
        final JsonNode node = createJsonNodeFromJsonString(SIMPLE_JSON);
        // Then
        assertNotNull(node);
        assertEquals("1", node.get("id").toString());
    }

    @Test
    void testCreateJsonNodeFromObject() {
        // When
        final JsonNode node = createJsonNodeFromObject(RAND_ID);
        // Then
        assertNotNull(node);
    }

    @Test
    void testConvertToString() {
        // When
        final String result = convertToString(RAND_ID);
        // Then
        assertNotNull(result);
    }

    @Test
    void testConvertToOptionalString() {
        // When
        final Optional<String> result = convertToOptionalString(RAND_ID);
        // Then
        assertNotNull(result);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Error Path
    // -----------------------------------------------------------------------------------------------------------------
    @Test
    void testCreateBlankJsonNodeWhenException() {
        // Given
        when(mockMapper.createObjectNode()).thenThrow(RuntimeException.class);
        // When
        final Exception exception = assertThrows(RuntimeException.class,
                () -> createBlankJsonNode(mockMapper)
        );
        // Then
        assertEquals(ERROR_MSG_CREATE_BLANK_JSON_NODE, exception.getMessage());
        // And
        verify(mockMapper, times(1)).createObjectNode();
    }

    @Test
    void testCreateJsonNodeFromJsonStringWhenException() throws JsonProcessingException {
        // Given
        when(mockMapper.readTree(any(String.class))).thenThrow(JsonProcessingException.class);
        // When
        final Exception exception = assertThrows(RuntimeException.class,
                () -> createJsonNodeFromJsonString(SIMPLE_JSON, mockMapper)
        );
        // Then
        assertEquals(ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_JSON_STR, exception.getMessage());
        // And
        verify(mockMapper, times(1)).readTree(any(String.class));
    }

    @Test
    void testCreateJsonNodeFromObjectWhenException() {
        // Given
        when(mockMapper.valueToTree(any(Object.class))).thenThrow(RuntimeException.class);
        // When
        final Exception exception = assertThrows(RuntimeException.class,
                () -> createJsonNodeFromObject(RAND_ID, mockMapper)
        );
        // Then
        assertEquals(ERROR_MSG_CREATE_BLANK_JSON_NODE_FROM_OBJ, exception.getMessage());
        // And
        verify(mockMapper, times(1)).valueToTree(any(Object.class));
    }

    @Test
    void testConvertToStringWhenException() throws JsonProcessingException {
        // Given
        when(mockMapper.writeValueAsString(any(Object.class))).thenThrow(JsonProcessingException.class);
        // When
        final Exception exception = assertThrows(RuntimeException.class,
                () -> convertToString(RAND_ID, mockMapper)
        );
        // Then
        assertEquals(ERROR_MSG_CONVERT_TO_STR, exception.getMessage());
        // And
        verify(mockMapper, times(1)).writeValueAsString(any(Object.class));
    }

    @Test
    void testConvertToOptionalStringWhenException() throws JsonProcessingException {
        // Given
        when(mockMapper.writeValueAsString(any(Object.class))).thenThrow(JsonProcessingException.class);
        // When
        final Exception exception = assertThrows(RuntimeException.class,
                () -> convertToOptionalString(RAND_ID, mockMapper)
        );
        // Then
        assertEquals(ERROR_MSG_CONVERT_TO_OPTIONAL_STR, exception.getMessage());
        // And
        verify(mockMapper, times(1)).writeValueAsString(any(Object.class));
    }

    @Test
    void testConvertJsonValueWhenEmpty() {
        // When
        final Optional<String> result = convertJsonValue(TEST);
        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testConvertJsonValueWhenList() {
        // Given
        final List<String> list = List.of(TEST);
        // When
        final Optional<String> result = convertJsonValue(list);
        // Then
        assertFalse(result.isEmpty());
    }

    @Test
    void testConvertJsonValueWhenMap() {
        // Given
        final Map<String, String> list = Map.of(TEST, TEST);
        // When
        final Optional<String> result = convertJsonValue(list);
        // Then
        assertFalse(result.isEmpty());
    }
}
