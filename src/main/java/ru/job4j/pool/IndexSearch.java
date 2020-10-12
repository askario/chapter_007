package ru.job4j.pool;

import lombok.AllArgsConstructor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

@AllArgsConstructor
public class IndexSearch<T> extends RecursiveTask<Integer> {
    private final Predicate<Integer> isNegative = x -> x < 0;
    private final Predicate<Integer> isPositive = x -> x > 0;

    private final T[] array;
    private final T target;
    private final Integer from;
    private final Integer to;

    public static <T> Integer search(T[] array, T target) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (Integer) forkJoinPool.invoke(new IndexSearch(array, target, 0, array.length - 1));
    }

    private int findPositiveIndex(int left, int right) {
        if (isNegative.test(left) && isNegative.test(right)) {
            return -1;
        }
        return isPositive.test(left) ? left : right;
    }

    @Override
    protected Integer compute() {
        int arraySize = to - from + 1;
        if (arraySize <= 10) {
            int index = -1;
            for (int i = from; i <= to; i++) {
                if (target.equals(array[i])) {
                    index = i;
                }
            }
            return index;
        }

        int mid = (from + to) / 2;

        IndexSearch<T> leftSearch = new IndexSearch(array, target, from, mid);
        IndexSearch<T> rightSearch = new IndexSearch(array, target, mid + 1, to);

        leftSearch.fork();
        rightSearch.fork();

        Integer left = leftSearch.join();
        Integer right = rightSearch.join();

        return findPositiveIndex(left, right);
    }
}


