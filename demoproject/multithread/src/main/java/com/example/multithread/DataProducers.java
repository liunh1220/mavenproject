package com.example.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataProducers {

	public static List<String[]> dataList(int para){
		
		String[] arr = null;
		List<String[]> list = new ArrayList<String[]>();
		if(para <= 0){
			para = 1;
		}
		for(int i=0; i<para*10000; i++){
			arr = new String[para];
			for(int x=0; x<para; x++){
				arr[x] = UUID.randomUUID().toString().replaceAll("-", "");
			}
			list.add(arr);
		}
		
		return list;
	}
	
	/*public static void main(String[] args) {
		dataList();
	}*/
	
}
