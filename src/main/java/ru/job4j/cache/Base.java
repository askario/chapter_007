package ru.job4j.cache;

import lombok.Getter;

@Getter
public class Base {
    private final int id;
    private int version = 0;
    private String name;

    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void updateVersion() {
        version++;
    }

    public void setName(String name) {
        this.name = name;
    }
}
