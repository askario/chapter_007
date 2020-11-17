package ru.job4j;

public enum TrackerSingle {
    INSTANCE;

    public <Item> Item add(Item model) {
        return model;
    }

    public static void main(String[] args) {
        TrackerSingle tracker = TrackerSingle.INSTANCE;
    }
}