package com.example.xixi.juc.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : xi-xi
 */
@Slf4j
public class TestCount {
    static final int SHARED_SHIFT   = 16;

    //计算过程
    //左移 按二进制形式把所有的数字向左移动对应的位数，高位移出(舍弃)，低位的空位补零。
    // 数学意义
    //a 左移2 位 代表 a*2^2
    // 计算过程
    //右移 按二进制形式把所有的数字向右移动对应的位数，低位移出(舍弃)，高位的空位补符号位，即正数补零，负数补1。
    // 数学意义
    // 右移n位相当于除以2的n次方。这里是取商哈

    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    /** Returns the number of shared holds represented in count  */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    static int sharedCount1(int c)    { return c >> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    public static void main(String[] args) {
        int nextc = 0 + SHARED_UNIT;
        int i = exclusiveCount(nextc);
        int j = sharedCount(nextc);
        int n = sharedCount1(nextc);
        log.info("读锁数量 {}",j);
        log.info("写锁数量 {}",i);
        log.info("a >> 16 = {} ",n);
        log.info("a >>> 16 = {} ",j);
    }
}
