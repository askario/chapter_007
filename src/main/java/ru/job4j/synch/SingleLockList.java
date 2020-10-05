package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final DynamicList<T> dynamicList = new DynamicList<>();

    public synchronized void add(T value) {
        dynamicList.add(value);
    }

    public synchronized T get(int index) {
        return (T) dynamicList.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(dynamicList).iterator();
    }

    private DynamicList<T> copy(DynamicList<T> dynamicList) {
        DynamicList<T> copy = new DynamicList<>();
        dynamicList.forEach(item -> copy.add(item));
        return copy;
    }
}


