package org.example.锁;

public class synchronizedExample {
    private int count = 0;

    public synchronized void increase() {
        if (count == 5) {
            throw new RuntimeException("Something went wrong!");
        }
        count++;
    }

    public synchronized int getCount() {
        return count;
    }

    public static void main(String[] args) {
        synchronizedExample example = new synchronizedExample();

        for (int i = 0; i < 10; i++) {
            // 创建一个线程来调用被synchronized修饰的方法
            Thread thread = new Thread(() -> {
                try {
                    example.increase();
                } catch (RuntimeException e) {
                    System.out.println("Exception caught: " + e.getMessage());
                }
                System.out.println("Thread name: " + Thread.currentThread().getName());
            }, "Thread-" + i);

            // 启动线程
            thread.start();
        }
        // 等待线程执行完成
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread name:  " + Thread.currentThread().getName());
        // 输出count的值
        System.out.println("Count after exception: " + example.getCount());
    }

}
