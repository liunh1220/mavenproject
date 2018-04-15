package com.ssm.manager;

import java.util.List;

import com.ssm.domain.Job;
import com.ssm.util.Page;

public interface IJobManager {
	
	// 运行状态
	public final static String ST_RUNNING = "RUNNING";
	
	// 停止状态
	public final static String ST_STOP = "STOP";
	
	// 全局模式,所有服务器都能运行调度
	public final static String MODE_GLOBAL = "GLOBAL";
	
	// 局部模式,只有指定的服务器才能运行调度
	public final static String MODE_LOCAL = "LOCAL";
	
	List<Job> list(Job job);
	
    Job getById(String id);
	
    List<Job> getByPage(Job job, Page page);
	
    void saveOrUpdate(Job job);
	
    void remove(Job job);
    
    /**
     *  初始化调度器
     */
    void start();
    
    /**
     *  停止调度器
     */
    void stop();
    
    /**
     * 启动任务
     * @param jobId
     */
    void start(Job job);
    
    /**
     * 停止任务
     * @param jobId
     */
    void stop(Job job);
    
    /**
     * 获取任务状态
     * @param jobId
     * @return
     */
    String getStatus(String jobId);
    
    /**
     * 立即执行调度
     * @param jobId
     */
    void startImmediately(String jobId);
    
    /**
	 * 启动调度告警
	 */
	void startMonitor();
	
	/**
	 * 停止调度告警
	 */
	void stopMonitor();
    
}
