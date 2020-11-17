package ru.job4j;

public class CountShareMain {
    public static void main(String[] args) throws InterruptedException {
        Count count = new Count();

        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        Thread third = new Thread(count::increment);
        Thread fourth = new Thread(count::increment);

        first.start();
        second.start();
        third.start();
        fourth.start();

//        first.join();
        second.join();
        third.join();
        fourth.join();

        System.out.println(count.get());
    }
}
