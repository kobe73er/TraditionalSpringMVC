package com.dengpf.interview;

/**
 * Created by kobe73er on 16/11/11.
 */
public class ClientInterview {
    public static int[] getSortedArray(int a[], int b[]) {
        int result[] = new int[a.length + b.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }

        }

        while (i < a.length) {
            result[k++] = a[i++];

        }
        while (j < b.length) {
            result[k++] = b[j++];
        }

        return result;
    }


    public static void main(String args[]) {
        int a[] = {1, 2, 3, 4, 5, 5, 5};

        int b[] = {6, 7, 8, 9, 10, 34, 56};

        int[] result = getSortedArray(a, b);

        for (int index = 0; index < result.length; index++) {
            System.out.println(result[index]);
        }

    }
}
