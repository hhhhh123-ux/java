package org.example.Netty.FastThreadLocal;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.HashMap;
import java.util.Map;

public class TestFastThreadLocal {
    public static final FastThreadLocal<String> f1 = new FastThreadLocal<>();

    public static final FastThreadLocal<String> f2 = new FastThreadLocal<>();

    public static void main(String[] args) {

        FastThreadLocalThread t1 = new FastThreadLocalThread(()->{
            f1.set("etcEriksen");//InternalThreadLocalMap 存储到该Map对应的Object数组的Object[1]处
            f2.set("111111");//InternalThreadLocalMap 存储到该Map对应的Object数组的Object[2]处

            //获取到当前线程
            Thread thread = Thread.currentThread();

            System.out.println("TestFastThreadLocal.main");
        },"t1");
        t1.start();
    }
}
