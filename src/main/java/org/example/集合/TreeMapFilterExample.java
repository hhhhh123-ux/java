package org.example.集合;

import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TreeMapFilterExample {
    public static void main(String[] args) {
        // 创建一个TreeMap实例
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "Apple");
        treeMap.put(2, "Banana");
        treeMap.put(3, "Cherry");
        treeMap.put(4, "Date");
        treeMap.put(5, "Elderberry");

        // 假设我们想要筛选出长度大于4的values
        int lengthThreshold = 4;

        // 使用Stream API进行筛选
        TreeMap<Integer, String> filteredMap = treeMap.entrySet().stream()
                .filter(entry -> entry.getValue().length() > lengthThreshold)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, TreeMap::new));

        // 打印筛选后的TreeMap
        filteredMap.forEach((key, value) -> System.out.println(key + " => " + value));
    }
}
