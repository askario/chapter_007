package ru.job4j.cache;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;

public class NonBlockingCacheTest {
    @Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlockingCache cache = new NonBlockingCacheImpl();
        cache.add(new Base(1, "Book model"));

        Runnable changeName = () ->
        {
            try {
                cache.update(new Base(1, Thread.currentThread().getName()));
            } catch (Exception e) {
                ex.set(e);
            }
        };

        Thread first = new Thread(changeName);
        Thread second = new Thread(changeName);

        second.start();
        first.start();
        first.join();
        second.join();

        Assert.assertThat(ex.get().getMessage(), is("Version of given object not corresponds to current"));
    }
}
