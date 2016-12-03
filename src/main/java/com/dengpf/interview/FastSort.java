package com.dengpf.interview;

import java.util.Collections;
import java.util.Random;

/**
 * Created by kobe73er on 16/11/12.
 */
public class FastSort {


    public void getFastSortArr(int arr[], int left, int right) {

        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;


        while (i != j) {
            while (arr[j] > arr[left] && i != j) {
                j--;
            }
            while (arr[i] <= arr[left] && i != j) {
                i++;
            }
            if (i != j) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            } else {
                int tmp;
                tmp = arr[left];
                arr[left] = arr[i];
                arr[i] = tmp;
            }
        }

        getFastSortArr(arr, left, i - 1);
        getFastSortArr(arr, j + 1, right);
    }


    public static void main(String args[]) {
//         int a[] = {34,5,1,23,555};
//
//        FastSort fs =new FastSort();
//        fs.getFastSortArr(a,0,a.length-1);


        int[] bigIntArr = new int[100000];
        int count = 0;
        while (count < 100000) {
            Random random = new Random();
            int randomInt = random.nextInt(100000);
            bigIntArr[count] = randomInt;
            count++;
        }

        int desArr[] = new int[100000];
        System.arraycopy(bigIntArr, 0, desArr, 0, bigIntArr.length);


        BabooSort bs = new BabooSort();

        long startTime = System.currentTimeMillis();
        bs.BabooSorted(desArr);

        long endTime = System.currentTimeMillis();

        System.out.println("Baboo Sort Time:" + (endTime - startTime));

//        for (int i = 0; i < desArr.length; i++) {
//            System.out.print(desArr[i] + " ");
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////

        int[] bigIntArr2 = new int[100000];
        Random random = new Random();
        int counter = 0;
        while (counter < 100000) {
            bigIntArr2[counter] =  random.nextInt(100000);
            counter++;
        }

        FastSort fs = new FastSort();
        long start =System.currentTimeMillis();
        fs.getFastSortArr(bigIntArr2, 0, bigIntArr2.length-1);
        long end = System.currentTimeMillis();

        System.out.println("FastSort: "+(end-start));

    }
}
