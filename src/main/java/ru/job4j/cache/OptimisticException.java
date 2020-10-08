package ru.job4j.cache;

public class OptimisticException extends RuntimeException {
    OptimisticException(String message) {
        super(message);
    }
}
