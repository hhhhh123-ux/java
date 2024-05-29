package org.example;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOQueue {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.offer("A");
        queue.offer("B");
        queue.offer("C");
        queue.offer("D");
        System.out.println("队列: " + queue);

        // 查看队首元素
        System.out.println("队首元素: " + queue.peek());

        // 移除并返回队首元素
        System.out.println("移除的队首元素: " + queue.poll());

        // 再次查看队首元素
        System.out.println("现在的队首元素: " + queue.peek());
        System.out.println("新增: " + queue.offer("A"));
        System.out.println("队列: " + queue);
    }

}
