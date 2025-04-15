package com.redeAncoraUsers.fiap.utils;

import com.fasterxml.jackson.databind.JsonNode;

public class MaxHeapUtils {

    private final JsonNode[] heap;
    private final long[] keys;
    private int size;

    public MaxHeapUtils(int capacity) {
        this.heap = new JsonNode[capacity];
        this.keys = new long[capacity];
        this.size = 0;
    }

    public void insert(long key, JsonNode value) {
        heap[size] = value;
        keys[size] = key;
        int current = size++;

        while (current > 0 && keys[parent(current)] < keys[current]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public JsonNode extractMax() {
        if (size == 0) {
            return null;
        }
        JsonNode max = heap[0];
        heap[0] = heap[--size];
        keys[0] = keys[size];
        heapify(0);
        return max;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void heapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest = index;

        if (left < size && keys[left] > keys[largest]) {
            largest = left;
        }

        if (right < size && keys[right] > keys[largest]) {
            largest = right;
        }

        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    private void swap(int i, int j) {
        JsonNode tempValue = heap[i];
        long tempKey = keys[i];
        heap[i] = heap[j];
        keys[i] = keys[j];
        heap[j] = tempValue;
        keys[j] = tempKey;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }
}
