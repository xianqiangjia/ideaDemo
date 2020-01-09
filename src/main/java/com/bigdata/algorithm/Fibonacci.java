package com.bigdata.algorithm;

/**
 * 1 1 2 3 5 8 13 .。。。。
 */
public class Fibonacci {

    public static void main(String[] args) {

        System.out.println(fibo(6));
    }

    public static int fibo(int number) {

        if (number == 1 || number == 2) {
            return number = 1;
        }

        return fibo(number - 1) + fibo(number - 2);
    }
}
