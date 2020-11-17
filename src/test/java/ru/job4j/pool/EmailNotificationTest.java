package ru.job4j.pool;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class EmailNotificationTest {
    @Test
    public void testEmailNotification() {
        EmailNotification emailNotification = new EmailNotification();
        List<User> users = Arrays.asList(
                User.builder().username("TestUser1").email("TestUser1@gmail.com").build(),
                User.builder().username("TestUser2").email("TestUser2@gmail.com").build(),
                User.builder().username("TestUser3").email("TestUser3@gmail.com").build()
        );

        users.stream().forEach(emailNotification::emailTo);
    }
}
