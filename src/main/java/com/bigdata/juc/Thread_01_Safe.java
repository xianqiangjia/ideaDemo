package com.bigdata.juc;

public class Thread_01_Safe {

	public static void main(String[] args) throws Exception {
		
		ShareData1 sd1 = new ShareData1();
		
		Thread t = new Thread(()-> {
			sd1.username = "zhangsan";
			try {
				Thread.sleep(500);
				sd1.main(null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println( sd1.username );
		});
		
		Thread t1 = new Thread(()-> {
			sd1.username = "lisi";
			try {
				Thread.sleep(500);
				sd1.main(null);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println( sd1.username );
		});
		
		t.start();
		t1.start();
		
		t.join();
		t1.join();
		
		System.out.println( "main方法执行完毕" );
		
	}
}

class ShareData1 {
	public String username;
	
	public static void main( String[] args ) {
		
	}
}
