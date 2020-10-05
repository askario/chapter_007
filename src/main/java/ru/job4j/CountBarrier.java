package ru.job4j;

import net.jcip.annotations.GuardedBy;

public class CountBarrier {
    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);

        Thread first = new Thread(() -> {
            countBarrier.count();
            countBarrier.count();

            System.out.println("First thread finished");
        });

        Thread second = new Thread(() -> {
            countBarrier.await();
            System.out.println("Second thread finished");
        });

        Thread third = new Thread(() -> {
            countBarrier.count();
            countBarrier.count();
            countBarrier.count();

            System.out.println("Third thread finished");
        });

        first.start();
        second.start();
        third.start();
    }

    @GuardedBy("this")
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            if (count == total) {
                monitor.notifyAll();
            } else {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
