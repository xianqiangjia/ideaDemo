package com.bigdata.juc;

/*
 * 1. 一个对象，两个同步方法(A, B)， 哪个方法先执行？
 * 		不确定，因为取决于操作系统的调度机制
 * 2. 一个对象，两个同步方法(A, B)，在第一个方法(A)中休眠4秒，哪个方法先执行？
 * 		因为第一个方法(A)先执行，虽然休眠4秒钟，但是不释放对象锁
 *      那么其他线程无法获取对象锁，无法执行对应的方法，也需要等待, 直到第一个线程执行完毕释放锁才会继续执行。
 * 3. 一个对象， 一个同步方法（A），一个普通方法(C)，哪个方法先执行？
 * 		普通方法不需要获取对象锁，所以当A休眠时，C可以执行，4秒钟后，A开始执行
 * 4. 两个对象，两个同步方法（A, B），哪个方法先执行？
 * 		两个对象执行同步方法时，对象锁不是同一个，不需要等待，所以B先执行，4秒钟后，A再执行
 */
public class Thread_07_Synchronized {

	public static void main(String[] args) throws Exception {
		
		
		// 同步关键字的使用方法
		ShareData7 sd7 = new ShareData7();
		ShareData7 sd77 = new ShareData7();
		
		Thread t1 = new Thread(()-> {
			sd7.pringA();
		});
		
		Thread t2 = new Thread(()-> {
			sd77.pringB();
		});
		
		t1.start();
		Thread.sleep(100);
		t2.start();
	}
}

class ShareData7 {

	// synchronized关键字如果加在成员方法的前面声明，表示对当前对象加锁。
	public synchronized void pringA() {
		try {
			Thread.sleep(4000);
			//this.wait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println( "aaaaa" );
	}
	
	public synchronized void pringB() {
		System.out.println( "bbbbb" );
	}
	
	public void pringC() {
		System.out.println( "ccccc" );
	}
}