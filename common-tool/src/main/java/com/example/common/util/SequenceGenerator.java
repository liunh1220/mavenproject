package com.example.common.util;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 生成字符数字序列，放至队列中
 * @author Administrator
 *
 */
public class SequenceGenerator {
	
	private ConcurrentLinkedQueue<String> queue;
	
	public SequenceGenerator(Integer top)
	{
		queue = new ConcurrentLinkedQueue<String>();
		int size = 0;
		for (int i = 0; i <= top; i++) {
			size = (""+top).length() - (""+i).length();
			String zeros = getZero(size);
			queue.add(zeros + String.valueOf(i));
		}
	}
	
	public String getZero(int size)
	{
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			stringBuffer.append("0");
		}
		return stringBuffer.toString();
	}
	
	public String poll()
	{
		return queue.poll();
	}
	
	public static void main(String[] args) {
		SequenceGenerator sequenceGenerator = new SequenceGenerator(9999);
		for (int i = 0; i <10001; i++) {
			System.out.println(sequenceGenerator.poll());
		}
		
	}
	
}
