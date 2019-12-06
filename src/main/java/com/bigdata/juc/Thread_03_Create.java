package com.bigdata.juc;

public class Thread_03_Create {

	public static void main(String[] args) throws Exception {
		
		// 创建线程 - 1
		//Thread thread = new MyThread();
		//thread.start();
		
		// 创建线程 - 2
		//Thread thread = new Thread( new MyRunnable() );
		// thread.start();
		
		// 创建线程 - 2 - 1
//		Thread thread = new Thread( new Runnable() {
//			@Override
//			public void run() {
//				System.out.println( "inner Class..." );
//			}
//		} );
//		thread.start();
		
		// 创建线程 - 2 - 2 (λ表达式)
		Thread thread = new Thread(()->{
			for ( int i = 0; i < 10; i++ ) {
				if ( i == 5 ) {
					break;
				} else {
					System.out.println( "λ表达式..." );
				}
			}
			
		});
		thread.start();
		// 真正的线程关闭
		thread.stop();
		
	}
}

class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println( "MyThread..." );
	}
	
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println( "MyRunnable..." );
	}
	
}