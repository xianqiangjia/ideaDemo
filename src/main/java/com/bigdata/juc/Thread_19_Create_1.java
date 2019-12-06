package com.bigdata.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// 抢车位
public class Thread_19_Create_1 {
	static int sum = 0;
	public static void main(String[] args) throws Exception {
		
		FutureTask<Integer> ft1 = new FutureTask<Integer>(new MyCallable1());
		FutureTask<Integer> ft2 = new FutureTask<Integer>(new MyCallable2());
		FutureTask<Integer> ft3 = new FutureTask<Integer>(new MyCallable3());
		Thread t1 = new Thread( ft1);
		Thread t2 = new Thread( ft2);
		Thread t3 = new Thread( ft3);
		t1.start();
		t2.start();
		t3.start();
		System.out.println("统计结果 = " + (ft1.get() + ft2.get() + ft3.get()));
	}
}

class MyCallable1 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		int sum = 0;
    	for ( int i = 1; i<=10; i++ ) {
			sum+= i;
		}
    	return sum;
	}
}

class MyCallable2 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		int sum = 0;
    	for ( int i = 1; i<=100; i++ ) {
			sum+= i;
		}
    	return sum;
	}
}

class MyCallable3 implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		int sum = 0;
    	for ( int i = 1; i<=1000; i++ ) {
			sum+= i;
		}
    	return sum;
	}
}


