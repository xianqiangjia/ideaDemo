package com.bigdata.juc;

import java.util.concurrent.CyclicBarrier;

// 线程的读写
public class Thread_16_Lock_2 {

	public static void main(String[] args) throws Exception {
	
		// 开会
		CyclicBarrier cb = new CyclicBarrier(5, ()->{
			System.out.println( "开始会议" );
		}) ;
		
		for ( int i = 1; i <= 5; i++ ) {
			new Thread(()->{
				System.out.println( "参会人员" + Thread.currentThread().getName() + "来到会议室。。。" );
				try {
					cb.await();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}, ""+i).start();
		}
		
		

	}
}


