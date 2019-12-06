package com.bigdata.juc;

public class Thread_06_Sleep {

	public static void main(String[] args) throws Exception {
		
		//long a = 1/10000;
		//System.out.println( a );
		
		ShareData6 sd6 = new ShareData6();
		
		new Thread(()-> {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sd6.flg = true;
		}).start();
		
		// 当前循环跳不出去，因为内存可见性问题
		while ( true ) {
			//Thread.sleep(1000*60*60*24);
			//TimeUnit.SECONDS.sleep(3000);
			if ( sd6.flg ) {
				System.out.println( "flg is true....." );
				break;
			}
			//System.out.println("....");
		}
		
		System.out.println( "main方法执行完毕。。。" );
		
		
	}
}

class ShareData6 {
	// volatile关键字可以增加内存可见性
	public volatile boolean flg = false;
	
}