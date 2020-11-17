package ru.job4j;

import org.junit.Test;

public class CASCountTest {
    @Test
    public void threadSafeCounter() throws InterruptedException {
        CASCount<Integer> counter = new CASCount<>();

        Runnable incrementCounter = () -> {
            counter.increment();
            System.out.println(Thread.currentThread().getName() + " " + counter.get());
        };

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(incrementCounter);
            thread.start();
            thread.join();
        }

        System.out.println("Result: " + counter.get());
    }
}
