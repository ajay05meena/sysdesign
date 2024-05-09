package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class LruCacheTest {
    @Test
    public void testCache(){
        int size = 5;
        Cache<String, String> cache = new CacheBuilder<String, String>().getCacheWithSize(size);
        for(int i =1;i<size+1;i++){
            cache.set(String.format("Key#%s", i), String.format("Value#%s", i));
        }
        Assertions.assertEquals("Value#1", cache.get("Key#1"));
        Assertions.assertEquals("Value#2", cache.get("Key#2"));
        Assertions.assertEquals("Value#3", cache.get("Key#3"));
        Assertions.assertEquals("Value#4", cache.get("Key#4"));
        Assertions.assertEquals("Value#5", cache.get("Key#5"));
        Assertions.assertEquals("Value#1", cache.get("Key#1"));
        cache.set("Key6", "Value6");
        Assertions.assertEquals("Value6", cache.get("Key6"));
        Assertions.assertEquals("Value#1", cache.get("Key#1"));
        Assertions.assertNull(cache.get("Key#2"));
        cache.set("Key#2", "updatedValue2");
        Assertions.assertEquals("updatedValue2", cache.get("Key#2"));
        Assertions.assertNull(cache.get("Key#3"));
        Assertions.assertEquals("Value#4", cache.get("Key#4"));
    }

}
