package com.dengpf.Lab;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Created by kobe73er on 16/11/19.
 */
public class MyHashMap<K, V> implements MyMap<K, V> {


    private static int DEFAULT_INITAL_CAPACITY = 4;
    private static int MAXIMUM_CAPACITY = 1 << 30;

    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    private int capacity = 0;

    private float loadFactor;

    private LinkedList<MyMap.Entry<K, V>>[] table;

    private int size = 0;

    public MyHashMap() {
        this(DEFAULT_INITAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int capacity) {
        this(capacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int capacity, float loadFactor) {
        if (capacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPower(capacity);
        }

        table = new LinkedList[capacity];

        this.loadFactor = loadFactor;


    }

    private int trimToPower(int initialCapacity) {
        int capacitpy = 1;

        while (capacitpy < initialCapacity) {
            capacitpy <<= 1;
        }
        return capacitpy;
    }

    @Override
    public void clear() {
        size = 0;
        removeEntries();
    }

    /**
     * Remove all entries from each bucket
     */
    private void removeEntries() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
    }

    @Override
    public boolean containsKey(K key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> bucketItem : bucket) {
                    if (bucketItem.getValue().equals(value)) {
                        return true;
                    }
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
        if (table[tableIndex] != null) {

            LinkedList<Entry<K, V>> bucket = table[tableIndex];

            for (Entry<K, V> entryItem : bucket) {
                if (entryItem.getKey().equals(key)) {
                    return entryItem.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Hash function
     */
    private int hash(int hashCode) {
        return supplementalHash(hashCode) & (capacity - 1);
    }


    /**
     * Ensure the hashing is evenly distributed
     */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    @Override
    public V put(K key, V value) {
        if (null != get(key)) {
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for (Entry<K, V> entryItem : bucket) {
                if (entryItem.getKey().equals(key)) {
                    V oldVal = entryItem.getValue();
                    entryItem.setValue(value);
                    return oldVal;
                }
            }
        }

        if (size() >= capacity * loadFactor) {
            if (capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            reHash();
        }


        if (table[hash(key.hashCode())] == null) {
            table[hash(key.hashCode())] = new LinkedList<Entry<K, V>>();
        }


        table[hash(key.hashCode())].add(new MyMap.Entry<K, V>(key, value));

        size++;

        return value;
    }

    private void reHash() {
        java.util.Set<Entry<K, V>> set = entrySet(); // Get entries
        capacity <<= 1;
        table = new LinkedList[capacity];
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

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    valueSet.add(entry.getValue());
            }
        }
        return valueSet;
    }

    @Override
    public Set<K>
    keySet() {
        java.util.Set<K> set = new java.util.HashSet<K>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entry : bucket)
                    set.add(entry.getKey());
            }
        }
        return set;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = key.hashCode();

        if (null != table[bucketIndex]) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];

            for (Entry<K, V> entryItem : bucket) {
                if (entryItem.getKey().equals(key)) {
                    bucket.remove();
                    size--;
                    break;
                }
            }
        }


    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<Entry<K, V>>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for (Entry<K, V> entryItem : bucket) {
                    entrySet.add(entryItem);
                }
            }
        }
        return entrySet;
    }


}
