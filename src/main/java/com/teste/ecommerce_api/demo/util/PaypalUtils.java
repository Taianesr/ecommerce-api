package com.teste.ecommerce_api.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaypalUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    private PaypalUtils() {
    }

    public static String extractLastHref(String json) throws JsonProcessingException {
        JsonNode root = mapper.readTree(json);
        JsonNode links = root.get("links");

        if (links == null || !links.isArray() || links.isEmpty()) {
            return null;
        }

        JsonNode lastLink = links.get(links.size() - 1);
        JsonNode hrefNode = lastLink.get("href");

        return hrefNode != null ? hrefNode.asText() : null;
    }
}
