package ru.job4j.synch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicList<E> implements Iterable<E> {
    Object[] container = new Object[10];
    int index = 0;

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int indexIter = 0;

            @Override
            public boolean hasNext() {
                return indexIter < index;
            }

            @Override
            public E next() {
                if (this.hasNext()) {
                    return (E) container[indexIter++];
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void add(E value) {
        if (container[container.length - 1] != null) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[index++] = value;
    }

    public E get(int index) {
        return (E) container[index];
    }
}
