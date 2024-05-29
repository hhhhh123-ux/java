package org.example.实际项目;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 示例数据
        List<ReportWaferIvHour> reportWaferIvHours = new ArrayList<>();
        reportWaferIvHours.add(new ReportWaferIvHour(new BigDecimal("1.0"), new BigDecimal("2.0"), new BigDecimal("3.0"), new BigDecimal("4.0"), new BigDecimal("5.0")));
        reportWaferIvHours.add(new ReportWaferIvHour(new BigDecimal("6.0"), new BigDecimal("7.0"), new BigDecimal("8.0"), new BigDecimal("9.0"), new BigDecimal("10.0")));
        reportWaferIvHours.add(new ReportWaferIvHour(new BigDecimal("11.0"), new BigDecimal("12.0"), new BigDecimal("13.0"), new BigDecimal("14.0"), new BigDecimal("15.0")));

        BigDecimal minValue = new BigDecimal("3.0"); // 区间最小值
        BigDecimal maxValue = new BigDecimal("12.0"); // 区间最大值

        // 使用 Stream 进行处理
        List<String> data = reportWaferIvHours.stream()
                .map(entry -> {
                    BigDecimal etaMin = adjustValue(entry.getEtaMin(), minValue, maxValue);
                    BigDecimal etaDownQuar = adjustValue(entry.getEtaDownQuar(), minValue, maxValue);
                    BigDecimal etaMid = adjustValue(entry.getEtaMid(), minValue, maxValue);
                    BigDecimal etaUpQuar = adjustValue(entry.getEtaUpQuar(), minValue, maxValue);
                    BigDecimal etaMax = adjustValue(entry.getEtaMax(), minValue, maxValue);
                    return etaMin + "," + etaDownQuar + "," + etaMid + "," + etaUpQuar + "," + etaMax;
                })
                .collect(Collectors.toList());

        // 输出结果
        System.out.println(data);
    }

    // 调整值的方法
    private static BigDecimal adjustValue(BigDecimal value, BigDecimal minValue, BigDecimal maxValue) {
        if (value.compareTo(minValue) < 0) {
            return minValue;
        } else if (maxValue != null && value.compareTo(maxValue) > 0) {
            return maxValue;
        } else {
            return value;
        }
    }
}
