package org.lrucache.cache;

public interface Cache <T, U>{
    U get(T t);
    void put(T t, U u);
}
