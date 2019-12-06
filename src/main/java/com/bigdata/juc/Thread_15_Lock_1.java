package com.bigdata.juc;

import java.util.concurrent.CountDownLatch;

// 线程的读写
public class Thread_15_Lock_1 {

	public static void main(String[] args) throws Exception {
	
		// 
		CountDownLatch latch = new CountDownLatch(6);
		
		for ( int i = 1; i <= 6; i++ ) {
			new Thread( ()->{
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println( "学生"+Thread.currentThread().getName() + "打扫完卫生离开教室" );
				latch.countDown();
			}, "" + i ).start();
		}
		latch.await();
		System.out.println( "老师锁门！！" );
		
		

	}
}


