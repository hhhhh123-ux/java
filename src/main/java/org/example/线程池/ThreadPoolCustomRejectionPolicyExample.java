package org.example.线程池;


import java.util.concurrent.*;

public class ThreadPoolCustomRejectionPolicyExample {

    private static final BlockingQueue<Runnable> rejectedTasks = new LinkedBlockingQueue<>();

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

        // 关闭线程池并等待任务完成
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // 重新处理被拒绝的任务
        processRejectedTasks(executor);

        // 确保重新提交的任务也能被处理完成
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

    // 自定义拒绝策略
    public static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("Task rejected: " + r.toString());
            try {
                rejectedTasks.put(r); // 将被拒绝的任务放入队列
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 处理被拒绝的任务
    private static void processRejectedTasks(ThreadPoolExecutor executor) {
        while (!rejectedTasks.isEmpty()) {
            try {
                Runnable task = rejectedTasks.take();
                System.out.println("Processing rejected task: " + task);
                executor.execute(task);  // 重新提交到线程池
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (RejectedExecutionException e) {
                System.out.println("Failed to resubmit task");
            }
        }
    }
}


