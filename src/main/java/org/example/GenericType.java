package org.example;

import java.util.ArrayList;

public class GenericType {
    public static void main(String[] args) {
        // 创建一个 ArrayList<Integer> 集合
        ArrayList<Object> list = new ArrayList<>();
//        list.add(1);
//        // 调用 fillNumList() 方法，传入 ArrayList<Integer> 集合
        fillNumList(list);// 编译错误
    }

    public static void fillNumList(ArrayList<? super Number> list) {
//        list.add(new Integer(0));// 编译正确
//        list.add(new Float(1.0));// 编译正确
        Object obj = new Object();
//        list.add();
//        // 遍历传入集合中的元素，并赋值给 Number 对象；会编译错误
//        for (Number number : list) {
//            System.out.print(number.intValue() + " ");
//            System.out.println();
//        }
//        // 遍历传入集合中的元素，并赋值给 Object 对象；可以正确编译
//        // 但只能调用 Object 类的方法，不建议这样使用
//        for (Object obj : list) {
//            System.out.println(obj);
//        }
    }
}
