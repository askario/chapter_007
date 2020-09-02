package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        if (getUser(user).isPresent()) {
            System.out.println("User with this id already exists");
            return false;
        } else {
            users.add(user);
            return true;
        }
    }

    public synchronized boolean update(User user) {
        Optional<User> currentUser = getUser(user);
        if (currentUser.isPresent()) {
            currentUser.get().setAmount(user.getAmount());
            return true;
        } else {
            System.out.println("Can't find user with given id");
            return false;
        }
    }

    public synchronized boolean delete(User user) {
        Optional<User> currentUser = getUser(user);
        if (currentUser.isPresent()) {
            users.remove(currentUser.get());
            return true;
        } else {
            System.out.println("Can't find user with given id");
            return false;
        }
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        Optional<User> firstUser = getUserById(fromId);
        Optional<User> secondUser = getUserById(toId);

        if (firstUser.isPresent() && secondUser.isPresent()) {
            User first = firstUser.get();
            User second = secondUser.get();
            if (amount < first.getAmount()) {
                first.changeAmount(-amount);
                second.changeAmount(amount);
            } else {
                throw new RuntimeException("The transfer amount is more than the first user has");
            }
        } else {
            throw new RuntimeException("Can't find users with given id's");
        }
    }

    private synchronized Optional<User> getUser(User user) {
        return users.stream()
                .filter(userElement -> userElement.equals(user))
                .findFirst();
    }

    private synchronized Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.equals(User.of(id)))
                .findFirst();
    }

    public synchronized List<User> getUsers() {
        return users.stream()
                .map(user -> user.from())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();

        storage.add(new User(1, 100));
        storage.add(new User(1, 200));

        try {
            storage.transfer(2, 1, 50);
        } catch (Exception e) {
            e.printStackTrace();
        }

        storage.delete(User.of(2));

        storage.getUsers().stream()
                .forEach(System.out::println);
    }
}
