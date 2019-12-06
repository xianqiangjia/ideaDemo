package com.bigdata.juc;

/*
 * 1. 一个对象，两个静态同步方法(A, B)， 哪个方法先执行？
 * 		休眠4秒后，A和B同时执行
 * 2. 一个对象，一个静态同步方法（A），一个成员同步方法(B)， 哪个方法先执行？
 * 		B先执行，过4秒后，A再执行
 * 3. 两个对象，两个静态同步方法（A，B）, 哪个方法先执行？
 * 		4秒后，A和B同时执行
 * 4. 两个对象，一个静态同步方法（A）, 一个成员同步方法（B）, 哪个方法先执行？
 * 		B先执行，过4秒后，A再执行
 */
public class Thread_08_Synchronized_1 {

	public static void main(String[] args) throws Exception {
		
		
		// 同步关键字的使用方法
		ShareData8 sd8 = new ShareData8();
		ShareData8 sd88 = new ShareData8();
		
		Thread t1 = new Thread(()-> {
			sd8.pringA();
		});
		
		Thread t2 = new Thread(()-> {
			sd88.pringB();
		});
		
		t1.start();
		Thread.sleep(100);
		t2.start();
	}
}

class ShareData8 {

	// synchronized关键字如果加在静态方法的前面声明，表示对当前类对象加锁。
	public synchronized static void pringA() { // Class<ShareData8>
		try {
			Thread.sleep(4000);
			//this.wait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println( "aaaaa" );
	}
	
	public synchronized void pringB() { // Object
		System.out.println( "bbbbb" );
	}
	
	public void pringC() {
		System.out.println( "ccccc" );
	}
}