package ru.job4j.userstorage;

public class User {
    private int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void changeAmount(int value) {
        this.amount += value;
    }

    public int getAmount() {
        return this.amount;
    }

    public int getId() {
        return this.id;
    }

    public User from() {
        return new User(this.id, this.amount);
    }

    public static User of(int id) {
        return new User(id, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof User)) {
            return false;
        }

        User c = (User) o;

        return Integer.compare(this.id, c.getId()) == 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
