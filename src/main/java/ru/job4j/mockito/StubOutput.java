package ru.job4j.mockito;

public class StubOutput implements Output {
    private final StringBuilder sb = new StringBuilder();

    @Override
    public void println(String text) {
        String ln = System.lineSeparator();
        sb.append(text).append(ln);
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
