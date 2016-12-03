package com.dengpf.Lab.set;

import com.dengpf.Lab.MyMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by kobe73er on 16/11/20.
 */
public class MyHashSet<E> implements MySet<E> {

    private static int DEFAULT_INITIAL_CAPACITY = 4;

    private static int MAXIMUM_CAPACITY = 1 << 30;

    private int capacity;

    // Define default load factor
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    private float loadFactorThreshold;

    private int size = 0;

    private LinkedList<E>[] table;


    public MyHashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity) {
        this.capacity = initialCapacity;
    }


    public MyHashSet(int initialCapacity, float loadFactorThreshold) {

        if (initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;

        table = new LinkedList[capacity];

    }

    /**
     * Return a power of 2 for initialCapacity
     */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while (capacity < initialCapacity) {
            capacity <<= 1; // Same as capacity *= 2. <= is more efficient
        }

        return capacity;
    }

    @Override
    public void clear() {
        size = 0;
        removeElements();

    }

    /**
     * Remove all e from each bucket
     */
    private void removeElements() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                table[i].clear();
            }
        }
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
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        if (table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            for (E element : bucket)
                if (element.equals(e))
                    return true;
        }
        return false;
    }

    /**
     * Copy elements in the hash set to an array list
     */
    private java.util.ArrayList<E> setToList() {
        java.util.ArrayList<E> list = new java.util.ArrayList<E>();

        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (E e : table[i]) {
                    list.add(e);
                }
            }
        }

        return list;
    }

    /**
     * Rehash the set
     */
    private void rehash() {
        java.util.ArrayList<E> list = setToList(); // Copy to a list
        capacity <<= 1; // Same as capacity *= 2. <= is more efficient
        table = new LinkedList[capacity]; // Create a new hash table
        size = 0;

        for (E element : list) {
            add(element); // Add from the old table to the new table
        }
    }

    @Override
    public boolean add(E e) {
        if (contains(e)) {
            return false;
        }
        if (size + 1 > capacity * loadFactorThreshold) {
            if (capacity == MAXIMUM_CAPACITY)
                throw new RuntimeException("Exceeding maximum capacity");

            rehash();
        }

        int bucketIndex = hash(e.hashCode());

        // Create a linked list for the bucket if not already created
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<E>();
        }

        // Add e to hashTable[index]
        table[bucketIndex].add(e);

        size++; // Increase size

        return true;
    }

    @Override
    public boolean remove(E e) {
        if (null != table[hash(e.hashCode())]) {
            LinkedList<E> bucket = table[hash(e.hashCode())];
            if (bucket.contains(e)) {
                bucket.remove(e);
                size--;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashSetIterator(this);
    }

    private class MyHashSetIterator implements Iterator<E> {
        // Store the elements in a list
        private java.util.ArrayList<E> list;
        private int current = 0; // Point to the current element in list
        private MyHashSet<E> set;

        public MyHashSetIterator(MyHashSet<E> set) {
            this.list = setToList();
            this.set = set;
        }

        @Override
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            set.remove(list.get(current));
            list.remove(current);

        }
    }
}
