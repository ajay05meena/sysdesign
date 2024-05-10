package com.example.impl;

import com.example.Cache;
import com.example.OperationType;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LRUCache<K, V> implements Cache<K, V> {
    // This is cache size. If cache reaches this size than least recently used key will be evicted.
    private final int maxSize;
    private final Map<K, Node<K, V>> data;
    private final DoublyLinkedList<K, V> queue;
    private final Queue<Operation<K,V>> operations;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        this.operations = new LinkedList<>();
        this.data = new HashMap<>();
        this.queue = new DoublyLinkedList<>();
        Thread.startVirtualThread(this::consumeOperationQueue);
    }

    @Override
    public V get(K key) {
        Node<K, V> node = data.get(key);
        if(node == null || node.getExpireAt().isBefore(Instant.now())){
            return null;
        }
        this.operations.add(new Operation<>(node, OperationType.REMOVE_NODE));
        this.operations.add(new Operation<>(node, OperationType.ADD_NODE_TO_LAST));
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
            this.operations.add(new Operation<>(null, OperationType.REMOVE_HEAD));
        }
        this.data.put(key, node);
        this.queue.addNodeToLast(node);
    }

    private void consumeOperationQueue(){
        while (true){
            Operation<K, V> ops = this.operations.poll();
            if(ops != null){
                switch (ops.operationType()){
                    case REMOVE_HEAD:
                        K keyToRemove = this.queue.deleteCurrentHead().getKey();
                        this.data.remove(keyToRemove);
                        break;
                    case REMOVE_NODE:
                        Node<K, V> nodeToDelete = ops.node();
                        this.queue.deleteNode(nodeToDelete);
                        break;
                    case ADD_NODE_TO_LAST:
                        Node<K, V> nodeToAdd = ops.node();
                        this.queue.addNodeToLast(nodeToAdd);
                        break;
                }
            }
        }
    }
}
