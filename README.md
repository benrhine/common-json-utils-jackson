# common-json-utils-jackson

[![Full CI Build](https://github.com/benrhine/common-json-utils-jackson/actions/workflows/ci.yml/badge.svg)](https://github.com/benrhine/common-json-utils-jackson/actions/workflows/ci.yml)

This library contains a number of common functions for parsing JSON using the jackson library. It also supports 
inheriting the jackson library via
```groovy
api 'com.fasterxml.jackson.core:jackson-databind:2.14.2'
```

#### Available JSON Methods
All the following are static methods and can be declared anywhere in your code.

- JsonNode createBlankJsonNode
- JsonNode createBlankJsonNode(final ObjectMapper mapper)
- JsonNode createJsonNodeFromJsonString(final String jsonStr)
- JsonNode createJsonNodeFromJsonString(final String jsonStr, final ObjectMapper mapper)
- JsonNode createJsonNodeFromObject(final Object payload)
- JsonNode createJsonNodeFromObject(final Object payload, final ObjectMapper mapper)
- String convertToString(final Object payload)
- String convertToString(final Object payload, final ObjectMapper mapper)
- Optional<String> convertToOptionalString(final Object payload)
- Optional<String> convertToOptionalString(final Object payload, final ObjectMapper mapper)
- Optional<String> convertJsonValue(final Object value)

Currently, all of the above are capable of returning a RuntimeException with a custom message.

