package com.bigdata.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// 线程的读写
public class Thread_14_ReadWrite {

	public static void main(String[] args) throws Exception {
	
		RedSpider redSpider = new RedSpider();
	
		Thread t1 = new Thread(()-> {
			redSpider.write("sssssss");
		});
		t1.start();
		
		for ( int i = 1; i <= 20; i++ ) {
			Thread t = new Thread(()->{
				redSpider.read();
			}, "" + i);
			t.start();
		}
		
		

	}
}

class RedSpider {
	
	private String content;
	
	// 读写锁
	// JUC提供读写锁，将读写操作进行分离
	// 写写互斥
	// 读写互斥
	// 读读并行
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void write( String s ) {
		lock.writeLock().lock();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		content = s;
		System.out.println( "教师写入了新的内容 ：" + s );
		lock.writeLock().unlock();
	}
	
	public String read( ) {
		lock.readLock().lock();
		try {
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			System.out.println( "学生"+Thread.currentThread().getName()+"读取了新的内容 ：" + content );
			
		} finally {
			lock.readLock().unlock();
		}

		return content;
	}
}


