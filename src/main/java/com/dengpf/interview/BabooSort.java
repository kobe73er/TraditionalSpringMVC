package com.dengpf.interview;

/**
 * Created by kobe73er on 16/11/11.
 */
public class BabooSort {


    public int[] BabooSorted(int a[]) {
        //if no element change then should stop the loop
        boolean flagContinue = true;

        int loopCounter = 0;

        int outerLoopCount = a.length - 1;
        int innerLoopCount = a.length - 1;

        int m = 0;

        while (m < outerLoopCount && flagContinue) {
            loopCounter++;

            boolean innerFlag = false;
            for (int i = 0; i < innerLoopCount; i++) {
                if (a[i] > a[i + 1]) {
                    int tmp;
                    tmp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = tmp;
                    innerFlag = true;
                }
            }

            flagContinue = innerFlag;
            innerLoopCount--;
            m++;
        }


        System.out.println("loopCounter = " + loopCounter);
        return a;
    }


    public static void main(String args[]) {
        BabooSort bs = new BabooSort();


        int arr[] = {100, 99, 88, 77, 66, 55};
        int result[] = bs.BabooSorted(arr);

        for (int index = 0; index < result.length; index++) {
            System.out.print(result[index] + ",");
        }

        System.out.println();
        int targetArrTwo [] = {1,2,3,4,5,4,5,6,755,-11};

        int resultTwo[] = bs.BabooSorted(targetArrTwo);
        for (int index = 0; index < resultTwo.length; index++) {
            System.out.print(resultTwo[index] + ",");
        }

    }
}
