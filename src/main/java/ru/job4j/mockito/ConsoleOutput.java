package ru.job4j.mockito;

public class ConsoleOutput implements Output {
    @Override
    public void println(String text) {
        System.out.println(text);
    }
}
