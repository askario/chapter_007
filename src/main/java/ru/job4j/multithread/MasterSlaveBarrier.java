package ru.job4j.multithread;

import lombok.SneakyThrows;

public class MasterSlaveBarrier {
    private boolean isMasterTurn = true;

    @SneakyThrows
    public synchronized void tryMaster() {
        while (!Thread.currentThread().isInterrupted()) {
            if (isMasterTurn) {
                doneMaster();
                isMasterTurn = false;
                notify();
            } else {
                notify();
                wait();
            }
        }
    }

    @SneakyThrows
    public synchronized void trySlave() {
        while (!Thread.currentThread().isInterrupted()) {
            if (isMasterTurn) {
                notify();
                wait();
            } else {
                doneSlave();
                isMasterTurn = true;
                notify();
            }
        }
    }

    public void doneMaster() {
        System.out.println("A");
    }

    public void doneSlave() {
        System.out.println("B");
    }
}
