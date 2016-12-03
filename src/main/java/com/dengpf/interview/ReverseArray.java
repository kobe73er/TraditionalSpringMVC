package com.dengpf.interview;

/**
 * Created by kobe73er on 16/11/11.
 */
public class ReverseArray {

    public static String getReversedArr(String target) {
        StringBuffer sb = new StringBuffer();
        char[] targetArr = target.toCharArray();

        for (int index = target.length() - 1; index >= 0; index--) {
            sb.append(targetArr[index]);
        }


        return sb.toString();
    }

    public static void main(String args[]) {

        String result = getReversedArr("i am dengpf");
        System.out.println(result);
    }
}
