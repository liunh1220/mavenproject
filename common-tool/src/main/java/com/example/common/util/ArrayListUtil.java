package com.example.common.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ArrayListUtil {

	/**  

	 * 通过HashSet踢除重复元素除去List集合中的重复数据  

	* */    

	public static List<String> removeDuplicate(List<String> list) 
	{        
		if( list==null || list.size()==0 ){
			return null;
		}
		HashSet<String> h = new  HashSet<String>(list);        
	
		List<String> result = new ArrayList<String>(h);      
	
		return result;     

	}  
	
//	public static void main(String[] args) {
//		ArrayListUtil util = new ArrayListUtil();
//		List<String> list =  new ArrayList<String>();
//		list.add("2");
//		list.add("2");
//		System.out.println(list);
//		System.out.println(util.removeDuplicate(list));
//	}

}
