package com.bigdata.juc;

import java.util.concurrent.atomic.AtomicInteger;


public class Thread_09_Atomic {

	public static void main(String[] args) throws Exception {
	
		ShareData9 sd9 = new ShareData9();
		
		Thread t1 = new Thread(()-> {
			
			for ( int i = 1; i <= 20; i++) {
				sd9.produce();
			}
			
		});
		
		
		Thread t2 = new Thread(()-> {
			
			for ( int i = 1; i <= 20; i++) {
				sd9.consume();
			}
			
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println( "main方法执行完毕 =" + sd9.count );
		
	}
}

// java.util.concurrent
class ShareData9 {

	// volatile只能解决线程可见性问题，无法解决原子性问题
	// long, double,  2 slot
	//public volatile int count = 0;
	// JDK1.5之后，提供了JUC相关的线程处理类，极大提高了线程并发访问的效率。
	// AtomicInteger是原子类，解决了原子性问题。
	// 采用CAS(Compare and Swap)算法，实现原子性操作，但是会出现ABA问题
	public AtomicInteger count = new AtomicInteger(0);
	
	// ++不是原子性的，所以中间运行可能会被其他线程打断
	public void produce() {
		count.getAndIncrement();
	}
	
	public void consume() {
		count.getAndDecrement();
	}
}