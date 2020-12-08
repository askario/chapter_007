package ru.job4j.mockito;

public interface UserAction {
    String name();

    boolean execute(Input input, Tracker tracker);
}
