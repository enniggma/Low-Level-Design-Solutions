package org.lrucache.cache;

import org.lrucache.entities.DoublyLinkedList;
import org.lrucache.entities.Node;

import java.util.HashMap;

public class LRUCache<T, U> implements Cache<T, U> {

    private final int capacity;
    private final HashMap<T, Node<T, U>> keyToNodeMap;
    private final DoublyLinkedList<T, U> dll;

    public LRUCache(int capacity) {
        this.keyToNodeMap = new HashMap<>();
        this.capacity = capacity;
        this.dll = new DoublyLinkedList<>();
    }

    @Override
    public synchronized U get(final T key) {
        if (!keyToNodeMap.containsKey(key)) {
            return null; // or throw exception, or return default
        }

        Node<T, U> node = keyToNodeMap.get(key);
        dll.moveToFront(node);
        return node.value;
    }

    @Override
    public synchronized void put(final T key, final U value) {
        if (keyToNodeMap.containsKey(key)) {
            Node<T, U> node = keyToNodeMap.get(key);
            node.value = value;
            dll.moveToFront(node);
        } else {
            if (keyToNodeMap.size() == capacity) {
                Node<T, U> lru = dll.removeLast();
                if (lru != null) keyToNodeMap.remove(lru.key);
            }
            Node<T, U> newNode = new Node<>(key, value);
            dll.addFirst(newNode);
            keyToNodeMap.put(key, newNode);
        }
    }
}
