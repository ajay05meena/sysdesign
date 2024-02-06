package com.example;

public interface Cache<K, V> {
    V get(K k);
    void set(K k, V v);
}
