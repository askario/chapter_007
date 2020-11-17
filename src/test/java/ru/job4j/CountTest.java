package ru.job4j;

import org.junit.Test;

public class CountTest {
    private class ThreadCount extends Thread {
        private final Count count;

        ThreadCount(Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecuteTwoThreadThenTwo() throws InterruptedException {
        final Count count = new Count();
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);

        first.start();
        second.start();

        first.join();
        second.join();
    }
}
