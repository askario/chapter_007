package ru.job4j;

public class Barrier {
    public static void main(String[] args) {
        Barrier barrier = new Barrier();

        Thread master = new Thread(() -> {
            barrier.on();
            System.out.println("Master started");
        });

        Thread slave = new Thread(() -> {
            barrier.check();
            System.out.println("Slave started");
        });

        master.start();
        slave.start();
    }

    private boolean flag = false;

    private final Object monitor = this;

    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
