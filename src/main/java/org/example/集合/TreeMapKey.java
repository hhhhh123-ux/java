package org.example.集合;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Map;

public class TreeMapKey {
    public static void main(String[] args) {
        // 创建 TreeMap，并根据字符串长度进行排序
        TreeMap<String, Integer> newMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int lengthCompare = Integer.compare(o1.length(), o2.length());
                if (lengthCompare == 0) {
                    return o1.compareTo(o2); // 如果长度相同，按字典顺序排序
                }
                return lengthCompare;
            }
        });

        // 添加示例数据
        newMap.put("apple", 1);
        newMap.put("banana", 2);
        newMap.put("pear", 3);
        newMap.put("kiwi", 4);

        // 打印 TreeMap 内容
        for (Map.Entry<String, Integer> entry : newMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}

