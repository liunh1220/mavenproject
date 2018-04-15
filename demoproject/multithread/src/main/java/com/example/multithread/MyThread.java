package com.example.multithread;

import java.io.Serializable;
import java.util.List;

public class MyThread implements Runnable , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7057145135945410614L;
	
	/**
	 * 模拟传参数
	 */
	private int para;
	 
	public MyThread(int para) {
		super();
		this.para = para;
	}

	@Override
	public void run() {
		System.out.println("线程调用DataProducers.dataList()");
		
		List<String[]> list = DataProducers.dataList(para);
		
		System.out.println("list.size:"+list.size());
		
	}

	
}
