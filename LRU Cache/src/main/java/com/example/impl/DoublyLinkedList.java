package com.example.impl;

public class DoublyLinkedList<K, V> {
    private Node<K, V> tail;
    private Node<K, V> head;
    private int size = 0;

    public DoublyLinkedList (Node<K, V> node){
        this.tail = node;
        this.head = node;
        node.addNext(node);
        node.addPrevious(node);
        this.size = 1;
    }




    public void addNodeToLast(Node<K, V> node){
        this.tail.addNext(node);
        node.addPrevious(this.tail);
        this.tail = node;
        size++;
    }

    public int getSize(){
        return this.size;
    }

    public void deleteNode(Node<K, V> node) {
        if(this.head == node){
            this.head = this.head.getNext();
        }
        if(this.tail == node){
            this.tail = this.tail.getPrevious();
        }
        node.getPrevious().addNext(node.getNext());
        size--;
    }

    public Node<K, V> deleteCurrentHead(){
        Node<K, V> tmp = this.head;
        this.head = this.head.getNext();
        size--;
        return tmp;
    }
}
