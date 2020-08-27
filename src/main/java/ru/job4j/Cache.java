package ru.job4j;

public final class Cache {
    private static Cache cache;

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(Cache::insOf);
        Thread second = new Thread(Cache::insOf);

        first.start();
        second.start();
    }

    private static synchronized Cache insOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }
}
