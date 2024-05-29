package org.example.线程池;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class ThreadPoolRejectionPolicyExample {

    private static final Logger logger = Logger.getLogger(ThreadPoolRejectionPolicyExample.class.getName());

    public static void main(String[] args) {
        int corePoolSize = 2;
        int maximumPoolSize = 4;
        long keepAliveTime = 10;
        int queueCapacity = 2;
        testRejectionPolicy(new ThreadPoolExecutor.CallerRunsPolicy(), "CallerRunsPolicy", corePoolSize, maximumPoolSize, keepAliveTime, queueCapacity);
        testRejectionPolicy(new ThreadPoolExecutor.DiscardPolicy(), "DiscardPolicy", corePoolSize, maximumPoolSize, keepAliveTime, queueCapacity);
        testRejectionPolicy(new ThreadPoolExecutor.DiscardOldestPolicy(), "DiscardOldestPolicy", corePoolSize, maximumPoolSize, keepAliveTime, queueCapacity);
        testRejectionPolicy(new LoggingRejectedExecutionHandler(), "LoggingRejectedExecutionHandler", corePoolSize, maximumPoolSize, keepAliveTime, queueCapacity);
        testRejectionPolicy(new ThreadPoolExecutor.AbortPolicy(), "AbortPolicy", corePoolSize, maximumPoolSize, keepAliveTime, queueCapacity);
    }

    private static void testRejectionPolicy(RejectedExecutionHandler rejectionHandler, String policyName, int corePoolSize, int maximumPoolSize, long keepAliveTime, int queueCapacity) {
        System.out.println("Testing " + policyName);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueCapacity),
                rejectionHandler
        );

        // 提交超出队列和最大线程数的任务
        for (int i = 0; i < 10; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Executing task " + taskNumber + " by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
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
        System.out.println();
    }

    // 自定义拒绝策略
    public static class LoggingRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task " + r.toString() + " rejected from " + executor.toString());
        }
    }

}
