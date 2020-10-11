package ru.job4j.pool;

import ru.job4j.concurrent.prodcons.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(10);

    public ThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            TaskExecutor thread = new TaskExecutor(tasks);
            thread.start();
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        threads.stream().forEach(Thread::interrupt);
    }
}
