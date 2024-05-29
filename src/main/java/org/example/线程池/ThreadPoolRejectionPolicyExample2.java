package org.example.线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolRejectionPolicyExample2 {
    private static final List<Runnable> rejectedTasks = new ArrayList<>();
    private static final AtomicInteger rejectedCount = new AtomicInteger(0);

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        int queueCapacity = 2;

        // 使用阻塞队列
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);

        // 使用自定义拒绝策略
        RejectedExecutionHandler rejectionHandler = new CustomRejectedExecutionHandler();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                workQueue,
                rejectionHandler
        );

        // 提交超出队列和最大线程数的任务
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("Rejected tasks: " + rejectedCount.get());
        for (Runnable task : rejectedTasks) {
            System.out.println("Rejected task: " + task);
        }
    }

    // 自定义拒绝策略
    public static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            rejectedTasks.add(r);
            rejectedCount.incrementAndGet();
            System.out.println("Task rejected: " + r.toString());
            // 这里可以实现将任务存储在数据库或消息队列中的逻辑
        }
    }
}
