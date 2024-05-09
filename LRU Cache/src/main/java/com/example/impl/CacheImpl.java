package com.example.impl;

import com.example.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<K, V> implements Cache<K, V> {
    // This is cache size. If cache reaches this size than least recently used key will be evicted.
    private final int maxSize;
    private final Map<K, Node<K, V>> data;
    private DoublyLinkedList<K, V> queue;

    public CacheImpl(int maxSize) {
        this.maxSize = maxSize;
        this.data = new HashMap<>();
    }

    @Override
    public V get(K key) {
        Node<K, V> node = data.get(key);
        if(node == null){
            return null;
        }
        this.queue.deleteNode(node);
        this.queue.addNodeToLast(node);
        return node.getValue();
    }

    @Override
    public void set(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        if(this.queue == null){
            this.queue = new DoublyLinkedList<>(node);
            this.data.put(key, node);
        }else {
            if (this.queue.getSize() >= maxSize) {
                K keyToRemove = this.queue.deleteCurrentHead().getKey();
                this.data.remove(keyToRemove);
            }

            this.data.put(key, node);
            this.queue.addNodeToLast(node);
        }
    }
}
