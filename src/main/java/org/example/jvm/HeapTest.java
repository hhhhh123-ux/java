package org.example.jvm;

import java.util.ArrayList;

public class HeapTest {

    byte[] a = new byte[1024 * 1024];

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> list = new ArrayList<>();
        while (true) {
            list.add(new HeapTest());
            Thread.sleep(10);
            System.out.println(list.size());
        }
    }
}
