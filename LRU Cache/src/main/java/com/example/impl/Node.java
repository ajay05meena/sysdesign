package com.example.impl;

import java.time.Instant;

public class Node<K, V> {
    private final V value;
    private final K key;
    private final Instant expireAt;

    private Node<K, V> previous;
    private Node<K, V> next;

    public Node(final K key,
                final V value,
                Instant expireAt){
        this.value = value;
        this.key = key;
        this.expireAt = expireAt;
    }

    public void addNext(Node<K, V> node){
        this.next = node;
        if(node!=null){
            node.previous = this;
        }
    }

    public void addPrevious(Node<K, V> node){
        this.previous = node;
        if(node!=null){
            node.next = this;
        }
    }

    public Node<K, V> getNext(){
        return this.next;
    }

    public Node<K, V> getPrevious(){
        return this.previous;
    }

    public V getValue(){
        return this.value;
    }

    public K getKey(){
        return this.key;
    }

    public Instant getExpireAt(){
        return this.expireAt;
    }

}
