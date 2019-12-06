package com.bigdata.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 1. synchronized可能会出现死锁的现象
// 2. synchronized锁定规则： 内部锁释放后，才能释放外部锁
// JUC
public class Thread_10_JUC {

	public static void main(String[] args) throws Exception {
	
		ShareData10 sd5 = new ShareData10();
		
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

class ShareData10 {
	public int count = 0;
	// JDK1.5之后，采用锁对象来代替同步关键字
	private ReentrantLock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	// synchronized, wait, notifyAll
	// ReentrantLock, await, signalAll
	
	public void produce() {
		
		// 加锁
		lock.lock();
		try {
			while ( count == 1 ) {
				try {
					cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			count++;
			System.out.println( "产品数量[生产者] = " + count );
			cond.signalAll();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
	
	public void consume() {
		// 加锁
		lock.lock();
		try {
			while ( count == 0 ) {
				try {
					cond.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			count--;
			System.out.println( "产品数量[消费者] = " + count );
			cond.signalAll();
		} finally {
			// 释放锁
			lock.unlock();
		}
	}
}

