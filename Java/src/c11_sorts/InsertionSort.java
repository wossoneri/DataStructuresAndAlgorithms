package c11_sorts;

import java.util.Arrays;

public class InsertionSort {


    public static void insertionSort(int[] array, int len) {
        if (len <= 1) return;

        for (int i = 1; i < len; ++i) {
            int cur = array[i];
            int j = i - 1;
            for (; j >= 0; --j) {
                if (array[j] > cur) {
                    array[j + 1] = array[j]; // move
                } else {
                    break;
                }
            }
            array[j + 1] = cur; // insert
        }
    }


    public static void main(String[] args) {
        int[] data = new int[]{4, 6, 5, 3, 7, 1, 2};
        InsertionSort.insertionSort(data, data.length);
        System.out.println(Arrays.toString(data));
    }
}
