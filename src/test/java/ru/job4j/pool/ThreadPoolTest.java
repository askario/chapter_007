package ru.job4j.pool;

import org.junit.Test;

public class ThreadPoolTest {
    @Test
    public void threadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        ThreadPool threadPool = new ThreadPool(size);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            threadPool.work(() -> {
                System.out.println("Working on task : " + finalI);
            });
        }
    }
}
