package ru.job4j.cache;

public interface NonBlockingCache<T> {
    void add(T t);

    void update(T t);

    void delete(T t);
}
