package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount<T> {
    private final AtomicReference<Integer> count = new AtomicReference<>(0);

    public void increment() {
        Integer temp;
        do {
            temp = count.get();
            temp++;
        } while (!count.compareAndSet(count.get(), temp));
    }

    public int get() {
        return count.get();
    }
}
