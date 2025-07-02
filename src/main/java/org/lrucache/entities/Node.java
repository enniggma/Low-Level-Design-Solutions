package org.lrucache.entities;

public class Node <K, V> {
    public K key;
    public V value;
    public Node prev;
    public Node next;

    public Node() {}  // for dummy nodes

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
