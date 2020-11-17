package ru.job4j.pool;

public class MergeSort {
    public static void main(String[] args) {

    }

    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {
        if (from == to) {
            return new int[]{array[from]};
        }

        int mid = (from + to) / 2;

        return merge(
                sort(array, from, mid),
                sort(array, mid + 1, to)
        );
    }

    static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI !=result.length){
            if(li == left.length){
                result[resI++] = right[ri++];
            }
        }
        return new int[]{1, 2, 3, 4};
    }
}
