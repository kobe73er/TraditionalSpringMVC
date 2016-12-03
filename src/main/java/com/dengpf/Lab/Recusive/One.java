package com.dengpf.Lab.Recusive;

import java.text.DecimalFormat;

/**
 * Created by kobe73er on 16/12/1.
 */
public class One {
    private static DecimalFormat df = new DecimalFormat("0.000 ");

    public static double getResult(int num) {
        if (num == 1) {
            return 1;
        }
        return 1 / num + getResult(num - 1);
    }

    public static void main(String args[]) {


        System.out.println(df.format(getResult(2)));

    }

}
