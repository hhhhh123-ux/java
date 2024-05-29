package org.example.集合;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TreeMapSort {
    public static void main(String[] args) {
        // 创建一个TreeMap，并添加一些键值对
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("pear", 3);
        map.put("kiwi", 4);
        // 过滤掉小于3的值，并排序 对key进行排序
        TreeMap<String, Integer> sortedMap = map.entrySet().stream()
                .filter(entry -> entry.getValue() >= 3)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        () -> new TreeMap<>(new Comparator<String>() {
                            @Override
                            public int compare(String o1, String o2) {
                                int lengthCompare = Integer.compare(o1.length(), o2.length());
                                if (lengthCompare == 0) {
                                    return o1.compareTo(o2); // 如果长度相同，按字典顺序排序
                                }
                                return lengthCompare;
                            }
                        })
                ));

        System.out.println(sortedMap);
    }
}
