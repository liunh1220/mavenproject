package com.example.common.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtil {

	/**
	 * list 分页
	 * @param key
	 * @param objsList
	 * @param count 每页记录数
	 * @return
	 */
	
	public static List<Map<String, List>>   listPage(String key, List objsList, Integer count)  {
		List<Map<String, List>> listMap = new ArrayList<Map<String, List>>();
		// 总记录数
		Integer rowCount = 0;
		if (null != objsList && objsList.size() > 0) {
			rowCount = objsList.size();
		}
		// 记录分页数
		Integer listPage = 0;
		if (rowCount % count == 0) {
			listPage = rowCount / count;
		} else {
			listPage = rowCount / count + 1;
		}
		Integer pageNo = 0;
		// 数据分页
		for (int n = 0; n < listPage; n++) {
			pageNo = n + 1;
			// 每页开始行
			Integer startIndex = pageNo * count - count;
			// 每页结束行
			Integer endIndex = 0;
			if (startIndex + count > rowCount) {
				endIndex = rowCount;
			} else {
				endIndex = startIndex + count;
			}
			// 子Map
			Map<String, List> tempMap = new HashMap<String, List>();
			List tmpObjsList = new ArrayList();
			// 划分list
			for (int i = startIndex; i < endIndex; i++) {
				tmpObjsList.add(objsList.get(i));
			}
			tempMap.put(key, tmpObjsList);
			listMap.add(tempMap);
		}
		return listMap;
	}
	
	/**
	 * list 分页
	 * @param key
	 * @param objsList
	 * @param count
	 * @return
	 */
	public static List<Map<String, List<Object>>>   listPageByObject (String key, List<Object> objsList, Integer count)  {
		List<Map<String, List<Object>>> listMap = new ArrayList<Map<String, List<Object>>>();
		// 总记录数
		Integer rowCount = 0;
		if (null != objsList && objsList.size() > 0) {
			rowCount = objsList.size();
		}
		// 记录分页数
		Integer listPage = 0;
		if (rowCount % count == 0) {
			listPage = rowCount / count;
		} else {
			listPage = rowCount / count + 1;
		}
		Integer pageNo = 0;
		// 数据分页
		for (int n = 0; n < listPage; n++) {
			pageNo = n + 1;
			// 每页开始行
			Integer startIndex = pageNo * count - count;
			// 每页结束行
			Integer endIndex = 0;
			if (startIndex + count > rowCount) {
				endIndex = rowCount;
			} else {
				endIndex = startIndex + count;
			}
			// 子Map
			Map<String, List<Object>> tempMap = new HashMap<String, List<Object>>();
			List<Object> tmpObjsList = new ArrayList<Object>();
			// 划分list
			for (int i = startIndex; i < endIndex; i++) {
				tmpObjsList.add(objsList.get(i));
			}
			tempMap.put(key, tmpObjsList);
			listMap.add(tempMap);
		}
		return listMap;
	}
	
	/**
	 * 将list分成 pageNo页
	 * @param key
	 * @param objsList
	 * @param pageNo
	 * @return
	 */
	public static List<Map<String, List>> listPageByPage(String key, List objsList, Integer pageNo) {
		if(pageNo>objsList.size()){
			pageNo=objsList.size();
		}
		List<Map<String, List>> listMap = new ArrayList<Map<String, List>>();
		long endSzie = 0L;
		// 总共处理的数量
		Integer totalNum = CollectionUtils.isNotEmpty(objsList) ? objsList.size() : 0; // 总数量
		// 每个线程处理的数量
		long oneThreadNum = (long) Math.ceil(((double) totalNum / (double) pageNo));
		

		for (int i = 0; i < pageNo; i++) {
			long startSize = i * oneThreadNum;
			Map<String, List> tempMap = new HashMap<String, List>();
			if (i == pageNo - 1) {
				endSzie = totalNum;
			} else {
				endSzie = startSize + oneThreadNum;
			}
			// 如果循环结束比集合还大，此时取集合最大数
			if (endSzie > totalNum) {
				endSzie = totalNum;
			}
			
            if (startSize > totalNum) {
				break;
			}

			List tmpObjsList = new ArrayList(objsList.subList((int) startSize, (int) endSzie));
			if (CollectionUtils.isNotEmpty(tmpObjsList)) {
				tempMap.put(key, tmpObjsList);
				listMap.add(tempMap);
			}
		}
		return listMap;
	}
	
	/**
	 * list转换成，每个值加上单引号后，再用逗号隔开的string
	 * @param stringList
	 * @return
	 */
    public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append("'").append(string).append("'");
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
    	List<String> list=new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        System.out.println(listToString(list));//aaa,bbb,ccc
	}
	
}
