package ru.job4j.mockito;

import java.util.ArrayList;
import java.util.List;

public class Tracker {
    private final List<Item> items = new ArrayList<>();

    public boolean replace(int id, Item item) {
        return true;
    }

    public void add(Item item) {
        items.add(item);
    }

    public List<Item> findAll() {
        return items;
    }
}
