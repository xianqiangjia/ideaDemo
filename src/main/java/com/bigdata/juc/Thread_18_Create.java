package com.bigdata.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

// 抢车位
public class Thread_18_Create {
	static int sum = 0;
	public static void main(String[] args) throws Exception {
		
//		Thread t1 = new Thread(
//		    ()->{
//		    	for ( int i = 1; i<=10; i++ ) {
//		    		sum+= i;
//		    	}
//		    	System.out.println( "sum = " + sum );
//		    }		
//		);
//		t1.start();
//		
//		Thread t2 = new Thread(
//		    ()->{
//		    	for ( int i = 1; i<=100; i++ ) {
//		    		sum+= i;
//		    	}
//		    	System.out.println( "sum = " + sum );
//		    }		
//		);
//		t2.start();
//		
//		Thread t3 = new Thread(
//		    ()->{
//		    	for ( int i = 1; i<=1000; i++ ) {
//		    		sum+= i;
//		    	}
//		    	System.out.println( "sum = " + sum );
//		    }		
//		);
//		t3.start();
//		
//		System.out.println( "total sum = " + sum );
		FutureTask<Integer> ft = new FutureTask<Integer>(new MyCallable());
		Thread t = new Thread( ft);
		t.start();
		
		System.out.println(ft.get());
	}
}

// Runnable接口需要重写run方法，run方法不需要返回值，run方法没有异常
// Callable接口需要重写call方法，call方法可以返回结果，call方法可以抛出异常，进行统一异常处理
class MyRunnable1 implements Runnable {
	@Override
	public void run() {
		System.out.println( "MyRunnable1..." );
	}
}

class MyCallable implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println( "MyCallable..." );
		return 1;
	}
}


