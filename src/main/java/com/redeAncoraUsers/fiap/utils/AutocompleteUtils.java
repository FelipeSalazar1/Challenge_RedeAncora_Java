package com.redeAncoraUsers.fiap.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.redeAncoraUsers.fiap.services.AutoCompleteService;

import java.util.*;

public class AutocompleteUtils {

    public List<String> suggest(String prefix) {
        Set<String> allWords = new HashSet<>();
        List<JsonNode> products = AutoCompleteService.fetchProducts();

        for (JsonNode product : products) {
            JsonNode productData = product.path("data");
            String productName = productData.path("nomeProduto").asText();
            String[] words = productName.split("\\s+");
            allWords.addAll(Arrays.asList(words));
        }

        return AutocompleteUtils.getSuggestions(prefix.toUpperCase(), new ArrayList<>(allWords));
    }

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
