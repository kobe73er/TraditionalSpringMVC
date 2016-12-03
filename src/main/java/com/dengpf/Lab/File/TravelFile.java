package com.dengpf.Lab.File;

import java.io.File;

/**
 * Created by kobe73er on 16/11/25.
 */
public class TravelFile {

    // 递归遍历
    private static void getDirectory(File file) {
        File flist[] = file.listFiles();
        if (flist == null || flist.length == 0) {
            return;
        }
        for (File f : flist) {
            if (f.isDirectory()) {
                //这里将列出所有的文件夹
                System.out.println("Dir==>" + f.getAbsolutePath());
                getDirectory(f);
            } else {
                //这里将列出所有的文件
                System.out.println("file==>" + f.getAbsolutePath());
            }
        }
    }

    private static String normalize(String pathname, int len, int off) {
        if (len == 0) return pathname;
        int n = len;
        while ((n > 0) && (pathname.charAt(n - 1) == '/')) n--;
        if (n == 0) return "/";
        StringBuffer sb = new StringBuffer(pathname.length());
        if (off > 0) sb.append(pathname.substring(0, off));
        char prevChar = 0;
        for (int i = off; i < n; i++) {
            char c = pathname.charAt(i);
            if ((prevChar == '/') && (c == '/')) continue;
            sb.append(c);
            prevChar = c;
        }
        return sb.toString();
    }

    public static void main(String args[]) {

//        getDirectory(new File("/Users/kobe73er/Downloads/TraditionalSpringMVC/src/main"));

       System.out.println(normalize("/Users/kobe73er/Downloads/TraditionalSpringMVC/src/main", 0, 10));
    }
}
