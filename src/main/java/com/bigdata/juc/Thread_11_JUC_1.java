package com.bigdata.juc;

import java.util.concurrent.locks.ReentrantLock;

// 1. synchronized可能会出现死锁的现象
// 2. synchronized锁定规则： 内部锁释放后，才能释放外部锁
// JUC
public class Thread_11_JUC_1 {

	public static void main(String[] args) throws Exception {
	
		ShareData11 sd11 = new ShareData11();
		
		for ( int i = 1; i <= 2; i++ ) {
			new Thread(()-> {
				sd11.test();
			}, "" + i).start();
		}
		
	}
}

class ShareData11 {
	// 公平锁 ： true
	// 默认的锁为不公平锁 : false
	ReentrantLock lock = new ReentrantLock(true);
	public void test() {
		while (true) {
			lock.lock();
			
			System.out.println( Thread.currentThread().getName() + "获取了对象锁" );
			
			lock.unlock();
		}
	}
}


