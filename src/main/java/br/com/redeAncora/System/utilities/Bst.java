package br.com.redeAncora.System.utilities;

import java.util.ArrayList;
import java.util.List;

public class Bst {
    private BstNode root;

    public void insert(String value) {
        root = insertRecursive(root, value);
    }

    private BstNode insertRecursive(BstNode node, String value) {
        if (node == null) {
            return new BstNode(value);
        }

        int compare = value.compareTo(node.value);

        if (compare < 0) {
            node.left = insertRecursive(node.left, value);
        } else if (compare > 0) {
            node.right = insertRecursive(node.right, value);
        }

        return node;
    }

    public List<String> getSuggestions(String prefix, int limit) {
        List<String> results = new ArrayList<>();
        collectSuggestions(root, prefix, results, limit);
        return results;
    }

    private void collectSuggestions(BstNode node, String prefix, List<String> results, int limit) {
        if (node == null) {
            return;
        }

        collectSuggestions(node.left, prefix, results, limit);

        if (results.size() >= limit) {
            return;
        }

        if (node.value.startsWith(prefix)) {
            results.add(node.value);
        }

        collectSuggestions(node.right, prefix, results, limit);
    }

    private static class BstNode {
        String value;
        BstNode left;
        BstNode right;

        BstNode(String value) {
            this.value = value;
        }
    }
}