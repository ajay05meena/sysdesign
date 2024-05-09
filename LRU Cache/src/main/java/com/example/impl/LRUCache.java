package com.example.impl;

import com.example.Cache;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {
    // This is cache size. If cache reaches this size than least recently used key will be evicted.
    private final int maxSize;
    private final Map<K, Node<K, V>> data;
    private final DoublyLinkedList<K, V> queue;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.data = new HashMap<>();
        this.queue = new DoublyLinkedList<>();
    }

    @Override
    public V get(K key) {
        Node<K, V> node = data.get(key);
        if(node == null || node.getExpireAt().isBefore(Instant.now())){
            return null;
        }
        this.queue.deleteNode(node);
        this.queue.addNodeToLast(node);
        return node.getValue();
    }

    @Override
    public void set(K key, V value) {
        Node<K, V> node = new Node<>(key, value, Instant.MAX);
        setNode(key, node);
    }

    @Override
    public void set(K key, V value, Duration duration) {
        if(duration.isZero() || duration.isNegative()){
            throw new IllegalArgumentException("Duration can not be negative or zero");
        }
        Node<K, V> node = new Node<>(key, value, Instant.now().plus(duration));
        setNode(key, node);
    }

    private void setNode(K key, Node<K, V> node) {
        if (this.queue.getSize() >= maxSize) {
            K keyToRemove = this.queue.deleteCurrentHead().getKey();
            this.data.remove(keyToRemove);
        }
        this.data.put(key, node);
        this.queue.addNodeToLast(node);
    }
}
