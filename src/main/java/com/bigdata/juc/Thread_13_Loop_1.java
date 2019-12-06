package com.bigdata.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 线程接力
public class Thread_13_Loop_1 {

	public static void main(String[] args) throws Exception {
	
		ShareData13 sd13 = new ShareData13();
		
		Thread t1 = new Thread(()-> {
			for ( int i = 1; i <= 3; i++ ) {
				sd13.print5();
			}
			
		});
		
		Thread t2 = new Thread(()-> {
			for ( int i = 1; i <= 3; i++ ) {
			sd13.print10();
			}
		});
		
		Thread t3 = new Thread(()-> {
			for ( int i = 1; i <= 3; i++ ) {
			sd13.print15();
			}
		});
		
		t3.start();
		t1.start();
		t2.start();
		

	}
}

class ShareData13 {
	
	public int status = 1;
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition cond1 = lock.newCondition();
	private Condition cond2 = lock.newCondition();
	private Condition cond3 = lock.newCondition();
	
	public void print5() {
		
		lock.lock();
		
		try {
			
			while ( status != 1 ) {
				try {
					cond1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for ( int i = 1; i <=5; i++ ) {
				System.out.println( "i = " + i );
			}
			
			status = 2;
			cond2.signal();
		} finally {
			lock.unlock();
		}
		
	}
	
	public void print10() {
		lock.lock();
		
		try {
			
			while ( status != 2 ) {
				try {
					cond2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for ( int i = 6; i <=10; i++ ) {
				System.out.println( "i = " + i );
			}
			
			status = 3;
			cond3.signal();
		} finally {
			lock.unlock();
		}
	}
	public void print15() {
		lock.lock();
		
		try {
			
			while ( status != 3 ) {
				try {
					cond3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			for ( int i = 11; i <=15; i++ ) {
				System.out.println( "i = " + i );
			}
			
			status = 1;
			cond1.signal();
		} finally {
			lock.unlock();
		}
	}
}


