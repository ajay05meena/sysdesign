package com.example;

import com.example.impl.CacheImpl;

public class CacheBuilder<K, V> {

    public Cache<K, V> getCacheWithSize(int size){
        return new CacheImpl<>(size);
    }
}
