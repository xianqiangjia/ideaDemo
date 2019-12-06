package com.bigdata.juc;

public class Thread_04_SleepWait {

	public static void main(String[] args) throws Exception {
		
	
		Thread t1 = new Thread(()-> {
			System.out.println( "t1...." );
		});
		Thread t2 = new Thread(()-> {
			System.out.println( "t2...." );
		});
		
		t1.start();
		t2.start();
		
		Thread.sleep(1000);// 静态方法，和对象没有关系，和类型有关系,让当前正在执行的线程休眠
		
		// wait方法可以释放对象锁（Monitor），释放前必须先获取对象锁
		synchronized (t2) {
			t2.wait(1000); // 成员方法，和对象有关系，t2在等待
		}
		
		System.out.println( "main方法执行 完毕" );
		
	}
}