package com.example;

import com.example.impl.LRUCache;

public class CacheBuilder<K, V> {

    public Cache<K, V> getCacheWithSize(int size){
        return new LRUCache<>(size);
    }
}
