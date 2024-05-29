package org.example.订单号;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class OrderNumberGenerator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final int RANDOM_NUM_BOUND = 10000; // 定义随机数范围

    public static String generateOrderNumber(String prefix) {
        // 生成时间戳部分
        //String timestamp = dateFormat.format(new Date());
        String timestampStr = LocalDateTime.now().format(dateTimeFormatter);
        // 生成随机数部分
        int randomNumber = ThreadLocalRandom.current().nextInt(RANDOM_NUM_BOUND);

        // 组合成订单号
        return prefix + timestampStr + String.format("%04d", randomNumber);
    }

    public static void main(String[] args) {
        // 示例：生成订单号，假设业务前缀为"ORD"
        String orderNumber = generateOrderNumber("ORD");
        System.out.println("Generated Order Number: " + orderNumber);
    }
}
