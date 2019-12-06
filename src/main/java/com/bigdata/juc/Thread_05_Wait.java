package com.bigdata.juc;

public class Thread_05_Wait {

	public static void main(String[] args) throws Exception {
		
		
		ShareData5 sd5 = new ShareData5();
		
		Thread t1 = new Thread(()-> {
			for ( int i = 1; i <= 10; i++ ) {
				// 生产
				sd5.produce();
			}
		});
		
		Thread t2 = new Thread(()-> {
			for ( int i = 1; i <= 10; i++ ) {
				// 消费
				sd5.consume();
			}
		});
		
		Thread t3 = new Thread(()-> {
			for ( int i = 1; i <= 10; i++ ) {
				// 生产
				sd5.produce();
			}
		});
		
		Thread t4 = new Thread(()-> {
			for ( int i = 1; i <= 10; i++ ) {
				// 消费
				sd5.consume();
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		t1.join();
		t2.join();
		t3.join();
		t4.join();
		System.out.println( "main方法执行完毕" );
		
	}
}

class ShareData5 {
	public int count = 0;
	
	/**
	 * 消费
	 */
	// 多个生产者和消费者同时运行，可能会导致数据不正确，也因为判断机制存在问题
	// 本来wait后被唤醒开始执行逻辑，可是这个时候，判断条件被其他线程更改，导致唤醒的时机不正确，导致程序出现错误
	// 将这种现象称之为虚假唤醒SpuriousWakeup
	// 解决方案：将条件判断从if改为while
	public synchronized void consume() {
	
		// 判断
		while ( count == 0 ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 消费
		count--;
		
		System.out.println( "产品数量[消费者] = " + count );
		
		// 通知
		notifyAll();
	}
	
	/**
	 * 生产
	 */
	public synchronized void produce() {
		// 判断
		while ( count == 1 ) {
			try {
				// 等待
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// 生产
		count++;
		System.out.println( "产品数量[生产者] = " + count );
		
		// 通知
		notifyAll();
	}
}