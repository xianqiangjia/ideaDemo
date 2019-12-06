package com.bigdata.juc;

public class Thread_02_Safe_1 {

	public static void main(String[] args) throws Exception {
		
		// JVM
		// 逃逸分析 & 栈上分配
		StringBuilder s2 = new StringBuilder();
		
		String s1 = "a";
		s2.append(s1);
		String s = s2.append("b").toString();
		
		int i = 10 + 20 + 30 + 50;
		
		System.out.println(s);
		
	}
}

