package com.bigdata.algorithm;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

//3000w数据的表，取某项字段前50项数据 ，内存2g

public class TestSF {
    public static Set<Integer> getTop100(int[] inputArray) {
        TreeSet<Integer> top100 = new TreeSet();
        for (int i = 0; i < inputArray.length; i++) {
            if (top100.size() < 100) {
                top100.add(inputArray[i]);
            } else if ((Integer) top100.first() < inputArray[i]) {
                Object obj = top100.first();
                top100.remove(obj);
                top100.add(inputArray[i]);
            }
        }
        return top100;
    }

    public static void main(String[] args) {  //循环读取数据到内存
        int numberCount = 30000000;
        int maxNumber = numberCount;
        int inputArray[] = new int[numberCount];
        Random random = new Random();
        for (int i = 0; i < numberCount; ++i) {
            inputArray[i] = Math.abs(random.nextInt(maxNumber));
        }
        System.out.println("Sort begin...");
        long current = System.currentTimeMillis();
        Set<Integer> result = TestSF.getTop100(inputArray);
        System.out.println("Spend time:" + (System.currentTimeMillis() - current));

    }
}

