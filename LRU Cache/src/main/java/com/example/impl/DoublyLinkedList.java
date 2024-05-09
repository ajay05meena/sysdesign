package com.example.impl;

public class DoublyLinkedList<K, V> {
    private Node<K, V> tail;
    private Node<K, V> head;
    private int size;

    public DoublyLinkedList (){
        this.tail = null;
        this.head = null;
        this.size = 0;
    }

    public void addNodeToLast(Node<K, V> node){
        if(this.tail == null){
            head = tail = node;
        }else{
            tail.addNext(node);
            node.addPrevious(tail);
            tail = node;
        }
        size++;
    }

    public int getSize(){
        return this.size;
    }

    public void deleteNode(Node<K, V> node) {
        if(node.getPrevious()!=null){
            node.getPrevious().addNext(node.getNext());
        }else {
            head = head.getNext();
        }

        if(node.getNext() != null){
            node.getNext().addPrevious(node.getPrevious());
        }else{
            tail = node.getPrevious();
        }
        size--;
    }

    public Node<K, V> deleteCurrentHead(){
        if(head == null){
            return null;
        }
        Node<K, V> tmp = this.head;
        if(head == tail){
            head = tail = null;
        }else {
            head = head.getNext();
            head.addPrevious(null);
        }
        size--;
        tmp.addPrevious(null);
        tmp.addNext(null);
        return tmp;
    }
}
