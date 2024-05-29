package org.example.Delayed;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedTask implements Delayed {

    /**
     * 任务到期时间
     */
    private final long executeTime;

    private final Integer taskId;

    private final String taskName;

    public DelayedTask(long executeTime, String taskName, Integer taskId) {
        this.executeTime = System.currentTimeMillis() / 1000 + executeTime;
        this.taskId = taskId;
        this.taskName = taskName;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis() / 1000, TimeUnit.SECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeTime, ((DelayedTask) o).executeTime);
    }


    public static void main(String[] args) throws InterruptedException {
        // 创建延迟队列，并添加任务
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<>();
        System.out.println("当前时间是：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //分别添加1s、2s、3s到期的任务
        delayQueue.add(new DelayedTask(20, "Task 2", 2));
        delayQueue.add(new DelayedTask(10, "Task 1", 1));
        delayQueue.add(new DelayedTask(30, "Task 3", 3));

        // 取出任务并执行
        while (!delayQueue.isEmpty()) {
            DelayedTask task = delayQueue.take();
            //阻塞获取最先到期的任务
            System.out.println(task.taskId + " ------ " + task.taskName + "---" + task.executeTime + "s" + "---" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        }
    }
}

