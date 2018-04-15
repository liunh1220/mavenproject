package com.ssm.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssm.base.BaseController;
import com.ssm.domain.Job;
import com.ssm.entity.bo.JobConstant;
import com.ssm.entity.bo.JobLog;
import com.ssm.manager.IJobManager;
import com.ssm.util.ConfigurationUtil;
import com.ssm.util.Page;

@Controller
@RequestMapping("/job")
public class JobController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	
    @Autowired
    private IJobManager jobManager;

    @RequestMapping("getList")
    public String list(Job job, Page p, ModelMap map) throws Exception
    {
        try 
        {
        	if(job == null)
        	{
        		job = new Job();
        	}
        	String mode = ConfigurationUtil.getStringValue("sysmgt.job.mode");
			// 非调试模式
//			if (IJobManager.MODE_LOCAL.equals(mode)) {
//				job.setRunningIp(Context.getIp());
//			}
        	List<Job> list = jobManager.getByPage(job, p);
        	for(int i = 0; i < list.size(); i++){
        		list.get(i).setStatusDesc(jobManager.getStatus(list.get(i).getId()));
        	}
        	map.put("list", list);
        } 
        catch (Exception e) 
        {
            logger.error("JobAction.list",e);
            throw e;
        }
        
        return "job/list";
    }

    @RequestMapping("edit")
    public String edit(Job job, ModelMap map)
    {
        if(job != null && job.getId() != null)
        {
            String id = job.getId();
            job = jobManager.getById(id);
        }
        map.put("obj", job);
        return "job/form";
    }

    @RequestMapping("getHistoryList")
    public String historyList(Job job, ModelMap modelMap) throws Exception
    {
        try 
        {
        	String jobId = job.getId();
        	this.jobManager.getById(jobId);
        	LinkedHashMap<String, JobLog> map = JobConstant.getJobLogMap(jobId);
        	
        	List<JobLog> result = new ArrayList<JobLog>();
        	List<JobLog> descResult = new ArrayList<JobLog>();
        	if(map != null)
        	{
        		for(Map.Entry<String, JobLog> entry : map.entrySet()) {
        			JobLog jobLog = entry.getValue();
        			result.add(jobLog);
				}
        	}
        	
        	for (int i = result.size()-1; i >= 0; i--) {
        		descResult.add(result.get(i));
			}
        	
        	Page page = new Page();
        	page.setResult(descResult);
        	page.setStart(Page.DEFAULT_START);
        	page.setCount(result.size());
        	page.setLimit(result.size());
        	page.setCurrentPage(Page.DEFAULT_PAGE);
        	setPage(page);
        } 
        catch (Exception e) 
        {
            logger.error("JobAction.historyList",e);
            throw e;
        }
        modelMap.put("obj", job);
        return "job/history_list";
    }
    
    @RequestMapping("save")
    @ResponseBody
    public String save(Job job)
    {
        jobManager.saveOrUpdate(job);
        return "保存成功";
    }
    
    @RequestMapping("remove")
    @ResponseBody
	public String remove(Job job)
	{
		jobManager.remove(job);
		 return "删除成功";
	}
    
    @RequestMapping("startJob")
    @ResponseBody
    public String startJob(Job job)
    {
    	try
    	{
    		Job job1 = jobManager.getById(job.getId());
    		jobManager.start(job1);
        	return "启动成功";
    	}
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    		return "启动失败";
		}
    }
    
    @RequestMapping("stopJob")
    @ResponseBody
    public String stopJob(Job job)
    {
    	try
    	{
    		Job job1 = jobManager.getById(job.getId());
	    	jobManager.stop(job1);
	    	return "停止任务成功";
    	}
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    		return "停止任务失败";
		}
    }
    
    @RequestMapping("start")
    @ResponseBody
    public String start(Job job)
    {
    	try
    	{
	    	jobManager.start();
	    	return "启动调度成功";
    	}
    	catch (Exception e) {
    		return "启动调度失败";
		}
    }
    
    @RequestMapping("stop")
    @ResponseBody
    public String stop(Job job)
    {
    	try
    	{
    		jobManager.stop();
    		return "停止调度成功";
    	}
    	catch (Exception e) {
    		return "停止调度失败";
		}
    }
    
    @RequestMapping("startImmediately")
    @ResponseBody
    public String startImmediately(Job job)
    {
    	try
    	{
//    		for (int i = 0; i < 70; i++) {
//    			jobManager.startImmediately(getJob().getId());
//    		}
    		jobManager.startImmediately(job.getId());
    		return "执行成功";
    	}
    	catch (Exception e) {
    		logger.error(e.getMessage(),e);
    		return "执行失败";
		}
    }
    
    @RequestMapping("startMonitor")
    @ResponseBody
    public String startMonitor(Job job)
    {
    	try
    	{
	    	jobManager.startMonitor();
	    	return "启动调度告警成功";
    	}
    	catch (Exception e) {
    		return "启动调度告警失败";
		}
    }
    
    @RequestMapping("stopMonitor")
    @ResponseBody
    public String stopMonitor(Job job)
    {
    	try
    	{
    		jobManager.stopMonitor();
    		return "停止调度告警成功";
    	}
    	catch (Exception e) {
    		return "停止调度告警失败";
		}
    }
    
    @RequestMapping("getJob")
    @ResponseBody
    public String getJob(String jobId)
    {
    	try 
    	{
			return jobManager.getStatus(jobId);
		} 
    	catch (Exception e) 
    	{
    		return JobConstant.STATUS_STOP;
		}
    }
    
}
