package org.example.Netty.HashedWheelTimerTest;

import io.netty.util.HashedWheelTimer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class HashedWheelTimerTest {
    //创建一个HashedWheelTimer对象，参数分别为：tickDuration、tickUnit、wheelSize
    //tickDuration：表示时间轮中刻度的时间间隔，单位为毫秒
    //tickUnit：表示时间轮中刻度的时间单位，可以是毫秒、秒、分钟等
    //wheelSize：表示时间轮中刻度的数量，建议为2的幂次方，如16、32、64等
    //HashedWheelTimer对象创建后，会启动一个线程，该线程会定时执行时间轮的转动，并检查时间轮上所有的任务是否到期。

    public static void main(String[] args) {
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(100, TimeUnit.MILLISECONDS, 256);
        //加入三个任务，依次设置超时时间是 10s 5s 20s

        System.out.println("加入一个任务，ID = 1, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 1, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }, 10, TimeUnit.SECONDS);

        System.out.println("加入一个任务，ID = 2, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 2, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }, 5, TimeUnit.SECONDS);

        System.out.println("加入一个任务，ID = 3, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        hashedWheelTimer.newTimeout(timeout -> {
            System.out.println("执行一个任务，ID = 3, time= " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }, 20, TimeUnit.SECONDS);

        System.out.println("等待任务执行===========");
    }
}
