package com.bigdata.juc;

// 线程接力
public class Thread_12_Loop {

	public static void main(String[] args) throws Exception {
	
		ShareData12 sd12 = new ShareData12();
		
		Thread t1 = new Thread(()-> {
			sd12.print5();
		});
		
		Thread t2 = new Thread(()-> {
			try {
				t1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sd12.print10();
		});
		
		Thread t3 = new Thread(()-> {
			try {
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sd12.print15();
		});
		t3.start();
		t1.start();
		t2.start();
		
		
	}
}

class ShareData12 {
	
	public void print5() {
		for ( int i = 1; i <=5; i++ ) {
			System.out.println( "i = " + i );
		}
	}
	
	public void print10() {
		for ( int i = 6; i <=10; i++ ) {
			System.out.println( "i = " + i );
		}
	}
	public void print15() {
		for ( int i = 11; i <=15; i++ ) {
			System.out.println( "i = " + i );
		}
	}
}


