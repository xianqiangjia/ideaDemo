package com.bigdata.jvm;

/**
 * 内存逃逸，简单理解对象的动态作用范围，引用离开了方法的范围
 */
public class MemEscape {

    //比如，返回的stringBuffer对象，方法结束了，这个对象不能被回收，依然存活
    public static StringBuffer getStringBuffer(String str1, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str1);
        stringBuffer.append(str2);
        return stringBuffer;
        //修改为stringBuffer.toString()
    }

//    这是方法逃逸，还有线程逃逸，是对象被其他线程引用

//    逃逸分析
//    优化：栈上分配
//    逃逸分析确定某些对象是一定不会逃逸出方法之外的，就可以直接让这个对象在栈上分配内存，
//    该对象随方法的执行结束栈帧出栈而销毁，减轻了GC的压力。
    //还有同步清除，标量替代

}
