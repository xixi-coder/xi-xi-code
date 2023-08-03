package com.example.xixi.import_declare;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class RandomTime {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //指定开始日期
        long start = sdf.parse("2021-1-1 00:00:00").getTime();
        //指定结束日期
        long end = sdf.parse("2021-11-11 00:00:00").getTime();

        //调用方法产生随机数
         nextLong(start, end,sdf);


        //格式化输出日期

    }

    public static void nextLong(long start, long end, SimpleDateFormat sdf) {
        Random random = new Random();
        long randomDate= start + (long) (random.nextDouble() * (end - start + 1));
        Date date = new Date(randomDate);
        String format = sdf.format(date);
        System.out.println(format);
    }

}
