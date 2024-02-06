package com.example.impl;

import com.example.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements Cache<String, String> {
    // This is cache size. If cache reaches this size than least recently used key will be evicted.
    private final int maxSize;
    private final Map<String, Node<String, String>> data;
    private DoublyLinkedList<String, String> queue;

    public CacheImpl(int maxSize) {
        this.maxSize = maxSize;
        this.data = new HashMap<>();
    }

    @Override
    public String get(String key) {
        Node<String, String> node = data.get(key);
        if(node == null){
            return null;
        }
        this.queue.deleteNode(node);
        this.queue.addNodeToLast(node);
        return node.getValue();
    }

    @Override
    public void set(String key, String value) {
        Node<String, String> node = new Node<>(key, value);
        if(this.queue == null){
            this.queue = new DoublyLinkedList<>(node);
            this.data.put(key, node);
        }else {
            if (this.queue.getSize() >= maxSize) {
                String keyToRemove = this.queue.deleteCurrentHead().getKey();
                this.data.remove(keyToRemove);
            }

            this.data.put(key, node);
            this.queue.addNodeToLast(node);
        }
    }
}
