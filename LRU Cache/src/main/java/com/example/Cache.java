package com.example;

import java.time.Duration;

public interface Cache<K, V> {
    V get(K k);
    void set(K k, V v);
    void set(K k, V v, Duration duration);
}
