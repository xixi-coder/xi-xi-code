/*
 * *************************************************************************
 *  Copyright (c) 2006-2022 ZheJiang Electronic Port, Inc.
 *  All rights reserved.
 *
 *  项目名称：结算1.0
 *  版权说明：本软件属易豹网络科技有限公司所有，在未获得易豹网络科技有限公司正式授权
 *            情况下，任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知
 *            识产权保护的内容。
 * *************************************************************************
 */
package com.example.xixi.testMd5;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * md5工具类.
 *
 * @author <a href="mailto:daiyp@zjport.gov.cn">daiyongpei</a>
 * @date 2021/1/14 15:17
 * @since 1.0
 */
public class MD5Utils {

    private static final String ALGORIGTHM_MD5 = "MD5";
    private static final int CACHE_SIZE = 2048;

    /**
     * 加密默认的盐.
     */
    private static final String slat = "&%5123***&&%%$$#@";

    /**
     * 生成md5
     * @param str 需要加密的md5信息
     * @return md5字符串
     */
    public static String createMd5(String str) {
        String base = str + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
    
    /**
     * 生成32位小写md5.
     *
     * @param s s
     * @return md5字符串
     */
    public static String lowerMd5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes()).toLowerCase(Locale.ROOT);
    }

    /**
     * 字符串生成MD5
     *
     * @param input
     * @return
     * @throws Exception
     */
    public static String createMD5(String input) throws Exception {
        return createMD5(input, null);
    }
    /**
     * 字符串生成MD5
     *
     * @param input
     * @param charset 编码(可选)
     * @return
     * @throws Exception
     */
    public static String createMD5(String input, String charset) throws Exception {
        byte[] data;
        if (charset != null && !"".equals(charset)) {
            data = input.getBytes(charset);
        } else {
            data = input.getBytes();
        }
        MessageDigest messageDigest = getMD5();
        messageDigest.update(data);
        return byteArrayToHexString(messageDigest.digest());
    }

    /**
     * <p>
     * 获取MD5实例
     * </p>
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static MessageDigest getMD5() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(ALGORIGTHM_MD5);
    }

    /**
     * <p>
     * MD5摘要字节数组转换为16进制字符串
     * </p>
     *
     * @param data MD5摘要
     * @return
     */
    private static String byteArrayToHexString(byte[] data) {
        // 用来将字节转换成 16 进制表示的字符
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        // 每个字节用 16 进制表示的话，使用两个字符，所以表示成 16 进制需要 32 个字符
        char arr[] = new char[16 * 2];
        int k = 0; // 表示转换结果中对应的字符位置
        // 从第一个字节开始，对 MD5 的每一个字节转换成 16 进制字符的转换
        for (int i = 0; i < 16; i++) {
            byte b = data[i]; // 取第 i 个字节
            // 取字节中高 4 位的数字转换, >>>为逻辑右移，将符号位一起右移
            arr[k++] = hexDigits[b >>> 4 & 0xf];
            // 取字节中低 4 位的数字转换
            arr[k++] = hexDigits[b & 0xf];
        }
        // 换后的结果转换为字符串
        return new String(arr);
    }

}
