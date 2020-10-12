package ru.job4j.pool;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class IndexSearchTest {
    @Test
    public void findIndexOfInteger() {
        Integer[] array = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
        Integer result = IndexSearch.search(array, 12);
        Assert.assertThat(result, is(11));
    }

    @Test
    public void findIndexOfString() {
        String[] array = new String[]{"A", "B", "C", "D", "E", "F", "G", "J", "K", "L", "M", "N"};
        Integer result = IndexSearch.search(array, "M");
        Assert.assertThat(result, is(10));
    }
}
