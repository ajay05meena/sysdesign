package com.example.impl;

public class Node<K, V> {
    private final V value;
    private final K key;

    private Node<K, V> previous;
    private Node<K, V> next;

    public Node(final K key,
                final V value){
        this.value = value;
        this.key = key;
    }

    public void addNext(Node<K, V> node){
        this.next = node;
    }

    public void addPrevious(Node<K, V> node){
        this.previous = node;
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
 }
