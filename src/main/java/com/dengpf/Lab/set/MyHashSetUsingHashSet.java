package com.dengpf.Lab.set;

import com.dengpf.Lab.MyHashMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kobe73er on 16/11/21.
 */
public class MyHashSetUsingHashSet<E> implements MySet<E> {

    private MyHashMap myHashMap;

    public MyHashSetUsingHashSet(MyHashMap myHashMap) {
        this.myHashMap = myHashMap;
    }

    public MyHashMap getMyHashMap() {
        return myHashMap;
    }

    @Override
    public void clear() {
        myHashMap.clear();
    }

    @Override
    public boolean contains(E e) {
        if (myHashMap.containsKey(e)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if (!myHashMap.containsKey(e)) {
            myHashMap.put(e, e);
        }
        return false;
    }

    @Override
    public boolean remove(E e) {
        if (myHashMap.containsKey(e)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return myHashMap.size() == 0;
    }

    @Override
    public int size() {
        return myHashMap.size();
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashSetUsingHashSetIterator<E>(this);
    }


    private class MyHashSetUsingHashSetIterator<E> implements Iterator<E> {
        int currentElementIndex = 0;

        private List<E> elementList = new ArrayList<E>();

        MyHashSetUsingHashSet set;

        public MyHashSetUsingHashSetIterator(MyHashSetUsingHashSet set) {
            this.set = set;
            setToList(this.set);
        }

        @Override
        public boolean hasNext() {
            if (currentElementIndex < elementList.size()) {
                return true;
            }
            return false;
        }

        private List<E> setToList(MyHashSetUsingHashSet set) {
            Iterator<E> it = set.getMyHashMap().keySet().iterator();
            while (it.hasNext()) {
                elementList.add(it.next());
            }
            return elementList;

        }

        @Override
        public E next() {
            return elementList.get(currentElementIndex++);
        }

        @Override
        public void remove() {
            set.remove(elementList.get(currentElementIndex));
            elementList.remove(currentElementIndex);

        }
    }
}
