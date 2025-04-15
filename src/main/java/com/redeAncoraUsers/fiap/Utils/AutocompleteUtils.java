package com.redeAncoraUsers.fiap.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.TextNode;
import com.redeAncoraUsers.fiap.Utils.MaxHeapUtils;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class AutocompleteUtils {

    public static List<String> getSuggestions(String prefix, List<String> words) {
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            if (word.startsWith(prefix)) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }

        MaxHeapUtils heap = new MaxHeapUtils(frequencyMap.size());

        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            JsonNode node = new TextNode(entry.getKey());
            heap.insert(entry.getValue(), node);
        }

        List<String> suggestions = new ArrayList<>();
        int limit = 4;

        while (!heap.isEmpty() && suggestions.size() < limit) {
            suggestions.add(heap.extractMax().asText());
        }

        return suggestions;
    }
}
