package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        Thread loadingThread = new Thread(
                () -> {
                    for (int index = 0; index <= 100; index++) {
                        try {
                            System.out.print("\rLoading : " + index + "%");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        loadingThread.start();
        System.out.println("Main");
    }
}
