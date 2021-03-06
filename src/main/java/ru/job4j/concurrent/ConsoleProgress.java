package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());

        progress.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progress.interrupt();
    }

    @Override
    public void run() {
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("");
            try {
                Thread.sleep(500);
                System.out.print("\rLoading ... " + (count++ % 2 == 0 ? "/" : "\\"));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
