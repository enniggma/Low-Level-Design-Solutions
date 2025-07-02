package org.lrucache.cache;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class LRUCacheTest {


    private LRUCache<Integer, Integer> lruCache;

    @BeforeEach
    public void setUp() {
        lruCache = new LRUCache<>(2);
    }

    @Test
    public void testGetAndPut() {
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        assertEquals(lruCache.get(1), 1);
    }

    @Test
    public void testEviction() {
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        lruCache.get(1);
        lruCache.put(3, 3);

        assertEquals(lruCache.get(1), 1);
        assertEquals(lruCache.get(3), 3);
        assertNull(lruCache.get(2));
    }

}
