package com.dengpf.Lab.ClassLoader;

import java.net.URL;

/**
 * Created by kobe73er on 16/11/28.
 */
public class Client {

    public static void main(String args[]) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urls.length; i++) {
            System.out.println(urls[i].toExternalForm());
        }
    }
}
