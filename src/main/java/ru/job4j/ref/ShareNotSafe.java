package ru.job4j.ref;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        User user2 = User.of("rename");
        cache.add(user);
        cache.add(user2);

        user.setName("asdsadsadsa");user2.setName("trstarsa");

        for (User user3 : cache.findAll()) {
            System.out.println(user3.getName());
        }
    }
}
