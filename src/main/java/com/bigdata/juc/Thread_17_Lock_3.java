package com.bigdata.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;

// 抢车位
public class Thread_17_Lock_3 {

	public static void main(String[] args) throws Exception {
	
		// 信号灯
		Semaphore s = new Semaphore(3);
		
		for ( int i = 1; i <= 6; i++ ) {
			new Thread(()->{
				try {
					s.acquire();
					System.out.println( "车辆" + Thread.currentThread().getName() + "进入停车位" );
					Thread.sleep(new Random().nextInt(1000));
					System.out.println( "车辆" + Thread.currentThread().getName() + "离开停车位" );
					s.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}, "" + i).start();
		}
		
		

	}
}


