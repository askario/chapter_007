package ru.job4j.pool;

import ru.job4j.concurrent.prodcons.SimpleBlockingQueue;

public class TaskExecutor extends Thread {
    private final SimpleBlockingQueue<Runnable> tasks;

    public TaskExecutor(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Runnable task = tasks.poll();
            task.run();
        }
    }
}
