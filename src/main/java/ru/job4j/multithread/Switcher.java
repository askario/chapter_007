package ru.job4j.multithread;

public class Switcher {
    public static void main(String[] args) throws InterruptedException {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread master = new Thread(() -> barrier.tryMaster());
        Thread slave = new Thread(() -> barrier.trySlave());

        master.start();
        slave.start();
        master.join();
        slave.join();
    }
}
