package com.bigdata.juc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 抢车位
public class Thread_20_Unsafe {
	static int sum = 0;
	public static void main(String[] args) throws Exception {
		
		List list = new ArrayList();
		Map map = new HashMap();
		
		// 包装类:Collections工具类
		//Collections.sys
		// 写时复制（redis）， fork
		//CopyOnWriteArrayList<E>
		//CopyOnWriteArraySet<E>
//		ConcurrentHashMap<K, V>
		
	}
}


