package com.example.xixi.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Testregex {
    public static void main(String[] args) {
//        Pattern p = Pattern.compile("(?=.*出口报关单结算接口参数customsRtnCode)(?=.*20200)^.*");
//        Pattern p = Pattern.compile("出.*0");
        Pattern p = Pattern.compile("口.*2");

        String a ="出口报关单结算接口参数customsRtnCode：123，eBaoSeq：20200，companyCode：12312312";
//        String a ="我RUNOOB-菜鸟教程, :</h1>";
        // 用模式检查字符串
        Matcher m = p.matcher(a);
//检查匹配结果
        boolean b = m.matches();
//        Pattern p = Pattern.compile("a*b");
//// 用模式检查字符串
//        Matcher m = p.matcher("aaaaab");
////检查匹配结果
//        boolean b = m.matches();
        System.out.println(b);
    }
}
