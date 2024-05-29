package org.example.TestThreadLocal;

/**
 * @Description TODO
 * @Author etcEriksen
 * @Date 2024/2/6 22:49
 * @Version 1.0
 */
public class TestThreadLocal {

    //String泛型代表将来通过tl这一ThreadLocal对象加入的数据的类型为String
    //也就是Entry对象的value值为String类型数据
    public static final ThreadLocal<String> tl = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
            //通过tl这一工具类设置
            //这句代码执行完，最终的结果为：封装了一个Entry<ThreadLocal,String>的对象存储到table数组中
            //存储到table数组的哪一个索引位置？ index = ThreadLocal对象这一key键值对应的哈希值 & (table.length-1)
            tl.set("leomessi");
            //获取到当前线程
            Thread t1 = Thread.currentThread();
            System.out.println("TestThreadLocal.main");
        },"t1").start();

        new Thread(()->{
            //获取到当前线程
            Thread t2 = Thread.currentThread();
            System.out.println("TestThreadLocal.main");
        },"t2").start();
    }

}