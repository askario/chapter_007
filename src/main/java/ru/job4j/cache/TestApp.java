package ru.job4j.cache;

public class TestApp {
    public static void main(String[] args) throws InterruptedException {
        NonBlockingCache cache = new NonBlockingCacheImpl();

        Base model = new Base(1, "Book model");
        cache.add(model);

        Runnable threadBody = () -> {
            Base another = new Base(1, Thread.currentThread().getName());
            cache.update(another);
        };

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(threadBody);
            thread.start();
            thread.join();
        }

        System.out.println("test");
    }
}
