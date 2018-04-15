package com.ssm.entity.bo;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JobConstant
{
	
	public static final String STATUS_RUNNING = "运行中";
	
	public static final String STATUS_STOP = "停止";
	
	// 调度级别重要
	public static final String GRADE_IMP = "1";
	
	// 调度级别普通
	public static final String GRADE_NOR = "2";
	
	// 调度级别次要
	public static final String GRADE_SEC = "3";
	
	// 运行日志保存次数 满100则删除前50条
	public static final int DEFAULT_STORE_LOG_NUM = 30;
	
	// 调度监控是否运行
	private static boolean monitorRunning = false;
	
	private static Map<String,String> statusMap = new HashMap<String, String>();
	
	private static Map<String,LinkedHashMap<String,JobLog>> jobLogMap = new ConcurrentHashMap<String,LinkedHashMap<String,JobLog>>();

	/**
	 * 根据调度ID获取调度所有运行日志(默认100执行日志)
	 * Map<String, JobLog> 
	 * String:fireInstanceId
	 */ 
	public static LinkedHashMap<String, JobLog> getJobLogMap(String jobId) {
		return jobLogMap.get(jobId);
	}

	public static void setJobLogMap(String jobId,LinkedHashMap<String, JobLog> map) {
		jobLogMap.put(jobId, map);
	}

	public static String getStatus(String jobId) 
	{
		return statusMap.get(jobId);
	}

	public static void setStatus(String jobId,String status) 
	{
		statusMap.put(jobId, status);
	}

	public static boolean isMonitorRunning() {
		return monitorRunning;
	}

	public static void setMonitorRunning(boolean monitorRunning) {
		JobConstant.monitorRunning = monitorRunning;
	}
	
}
