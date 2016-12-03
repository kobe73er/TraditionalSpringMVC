package com.dengpf.Lab;

import java.util.Set;

/**
 * Created by kobe73er on 16/11/19.
 */
public interface MyMap<K, V> {
    public void clear();

    public boolean containsKey(K key);

    public boolean containsValue(V value);

    public boolean isEmpty();

    public V get(K key);

    public V put(K key, V value);

    public int size();

    public Set<V> values();

    public Set<K> keySet();

    public void remove(K key);

    public Set<Entry<K, V>> entrySet();


    public static class Entry<K, V> {
        K key;
        V value;


        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }


}
