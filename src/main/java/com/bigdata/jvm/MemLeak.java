package com.bigdata.jvm;

/**
 * 内存泄漏，不用的对象拿着引用没有释放，最终导致OOM
 * 简单示例
 */
public class MemLeak {

    public static void main(String[] args) {

        Object obj1 = new Object();
        Object obj2 = new Object();
        obj1 = obj2;

//        obj2对象赋值给了obj1,但是obj2引用还在，虽然不用了

    }
}
