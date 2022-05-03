package com.sias.springcloud.entities;

public class SleepUtils {


    /*这个是一个睡眠工具类*/
    public static void sleep(int second){
        try {
            Thread.sleep(1000*second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
