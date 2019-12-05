package c11_sorts;

import java.util.Arrays;

public class SelectionSort {


    public static void selectionSort(int[] array, int len) {
        if (len <= 1) return;

        for (int i = 0; i < len - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < len; ++j) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // 交换
            int tmp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = tmp;
        }
    }


    public static void main(String[] args) {
        int[] data = new int[]{4, 5, 6, 3, 2, 1};
        SelectionSort.selectionSort(data, data.length);
        System.out.println(Arrays.toString(data));
    }
}
