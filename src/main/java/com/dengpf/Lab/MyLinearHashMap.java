package com.dengpf.Lab;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kobe73er on 16/11/19.
 */
public class MyLinearHashMap<K, V> implements MyMap<K, V> {


    private static final int MAXIMUM_CAPACITY = 1 << 30;
    private static float DEFAULT_LOAD_FACTOR = 0.5f;

    private static int DEFAULT_CAPACITY = 4;
    private int capacity;

    private MyMap.Entry<K, V>[] table;

    int tableSize = 4;

    int size = 0;

    float loadFactor;


    public MyLinearHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyLinearHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public MyLinearHashMap(int capacity, float loadFactor) {
        if (capacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPower(capacity);
        }

        table = new MyMap.Entry[capacity];

        this.loadFactor = loadFactor;


    }

    private int trimToPower(int initialCapacity) {
        int c = 1;

        while (c < initialCapacity) {
            c <<= 1;
        }
        return c;
    }


    @Override
    public void clear() {
        for (Entry<K, V> entryItem : table) {
            entryItem = null;
        }
        size = 0;

    }

    @Override
    public boolean containsKey(K key) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                if (table[i].getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                if (table[i].getValue().equals(value)) {
                    return true;
                }

            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V get(K key) {
        int tableIndex = hash(key.hashCode());
        if (null != table[tableIndex]) {
            return table[tableIndex].getValue();
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = hash(key.hashCode());
        while (table[hash] != null) {
            hash++;
        }

        if (size() >= capacity * loadFactor) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            reHash();
        }

        table[hash] = new MyMap.Entry<K, V>(key, value);

        size++;

        return value;
    }

    private void reHash() {
        Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1;
        table = new MyMap.Entry[capacity];
        size = 0;

        for (Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue()); // Store to new table
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> valueSet = new HashSet<V>();
        for (Entry<K, V> entryItem : table) {
            valueSet.add(entryItem.getValue());
        }
        return valueSet;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                keySet.add(table[i].getKey());
            }
        }
        return keySet;
    }

    @Override
    public void remove(K key) {
        for (Entry<K, V> entryItem : table) {
            if (entryItem.getKey().equals(key)) {
                entryItem = null;
                size--;
            }
        }

    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<Entry<K, V>>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                entrySet.add(table[i]);
            }
        }
        return entrySet;
    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        hashCode = Math.abs(hashCode);
        return hashCode % capacity;
    }


}
