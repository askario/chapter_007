package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Runnable threadBody = () -> System.out.println(Thread.currentThread().getName());
        Thread first = new Thread(threadBody),
                second = new Thread(threadBody);

        System.out.println(first.getState());
        first.start();
        second.start();

        while (first.getState() != Thread.State.TERMINATED ||
                second.getState() != Thread.State.TERMINATED) {
            System.out.println(first.getName() + " | " + first.getState());
            System.out.println(second.getName() + " | " + second.getState());
        }
        System.out.println(first.getState());
        System.out.println(second.getState());
        System.out.println("The first and second threads are terminated");
    }
}
