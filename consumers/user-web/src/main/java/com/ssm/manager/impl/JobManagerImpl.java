package com.ssm.manager.impl;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.domain.Job;
import com.ssm.entity.ModelType;
import com.ssm.entity.bo.JobConstant;
import com.ssm.entity.bo.JobLog;
import com.ssm.entity.bo.MailTemplate;
import com.ssm.entity.vo.SmsVo;
import com.ssm.exception.BusinessException;
import com.ssm.manager.IJobManager;
import com.ssm.service.IJobBiz;
import com.ssm.util.ConfigurationUtil;
import com.ssm.util.DateUtil;
import com.ssm.util.IPUtil;
import com.ssm.util.Page;

@Service
public class JobManagerImpl implements IJobManager {

	private static final Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);

	@Autowired
	private IJobBiz jobBiz;
	
	/*@Autowired
	private ISmsApi smsAip;
	
	@Autowired
	private IEmailConfigManager emailConfigManager;*/
	
	public List<Job> list(Job job) {
		List<Job> jobs = jobBiz.list(job);
		return jobs;
	}

	public Job getById(String id) {
		Job job = null;
		try {
			logger.debug("getByJobId" + id);
			job = jobBiz.getById(id);
		} catch (Exception e) {
			logger.error("JobManagerImpl.getById()", e);
			throw new BusinessException("JobManagerImpl.getById()", e);
		}
		return job;
	}

	public List<Job> getByPage(Job job, Page page) {
		try {
			job.setPage(page);
			List<Job> list = jobBiz.getByPage(job);
			return list;
		} catch (Exception e) {
			logger.error("JobManagerImpl.getByPage()", e);
			throw new BusinessException("JobManagerImpl.getByPage()", e);
		}

	}

	public void saveOrUpdate(Job job) {
		try {
			// 保存
			if (job != null && StringUtils.isEmpty(job.getId())) {
				jobBiz.save(job);
			}
			// 修改
			else {
				jobBiz.update(job);
			}

		} catch (Exception e) {
			logger.error("JobManagerImpl.saveOrUpdate()", e);
			throw new BusinessException("JobManagerImpl.saveOrUpdate()", e);
		}
	}

	public void remove(Job job) {
		try {
			jobBiz.remove(job);
		} catch (Exception e) {
			logger.error("JobManagerImpl.remove()", e);
			throw new BusinessException("JobManagerImpl.remove()", e);
		}
	}

	// 启动调度器
	public void start() {
		try {
			logger.info("启动调度器");
			List<Job> jobs = null;
			String mode = ConfigurationUtil.getStringValue("sysmgt.job.mode");
			
			// 非调试模式
			if (MODE_LOCAL.equals(mode)) {
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("enabledFlag", "Y");
				// 当本机IP与配置运行高度IP一至,启动调度
				List<String> ipList = IPUtil.getRealIpList();
				if(null != ipList && ipList.size() > 0){
					//处理一台服务器启动多个WEB服务的问题，在IP上加上WEB服务端口。
					String nodeSystem = System.getProperty("log.work");//获取JVM相关运行参数的变量。使用JVM_OPTS或JAVA_OPTS来设置这些值。
					if(null != nodeSystem && (nodeSystem.indexOf("node1") >= 0 || nodeSystem.indexOf("node2") >= 0)){
						for(int i=0;i<ipList.size();i++){
							if(nodeSystem.indexOf("node1") >= 0){
								ipList.set(i, ipList.get(i)+":8080");
							}else{
								ipList.set(i, ipList.get(i)+":8180");
							}
						}
					}
					
					param.put("ipList", ipList);
				}
				jobs = jobBiz.listByIplist(param);
			}else{
				Job param = new Job();
				// 只查询调用状态的调度器
				param.setEnabledFlag("Y");
				jobs = this.list(param);
			}
			
			if (jobs != null) {
				int i=0;
				for (Job job : jobs) {
					try {
						this.start(job);
						i++;
						if(i == jobs.size())
						{
							logger.info("共启动" + i + "个调度");
						}
					} catch (Exception e) {
						logger.error("启动调度失败", e);
						// 启动失败
						job.setStatus(ST_STOP);
						// 更新
						this.jobBiz.update(job);
					}
				}
			}
			logger.info("启动调度器完成");
			
			this.startMonitor();
		} catch (Exception e) {
			logger.error("启动调度器失败", e);
			throw new BusinessException("", e);
		}
	}

	// 停止调度器
	public void stop() {
		try {
			List<Job> jobs = null;
			String mode = ConfigurationUtil.getStringValue("sysmgt.job.mode");
			
			// 非调试模式
			if (MODE_LOCAL.equals(mode)) {
				
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("enabledFlag", "Y");
				// 当本机IP与配置运行高度IP一至,启动调度
				List<String> ipList = IPUtil.getRealIpList();
				if(null != ipList && ipList.size() > 0){
					//处理一台服务器启动多个WEB服务的问题，在IP上加上WEB服务端口。
					String nodeSystem = System.getProperty("log.work");//获取JVM相关运行参数的变量。使用JVM_OPTS或JAVA_OPTS来设置这些值。
					if(null != nodeSystem && (nodeSystem.indexOf("node1") >= 0 || nodeSystem.indexOf("node2") >= 0)){
						for(int i=0;i<ipList.size();i++){
							if(nodeSystem.indexOf("node1") >= 0){
								ipList.set(i, ipList.get(i)+":8080");
							}else{
								ipList.set(i, ipList.get(i)+":8180");
							}
						}
					}
					
					param.put("ipList", ipList);
				}
				jobs = jobBiz.listByIplist(param);
			}else{
				Job param = new Job();
				// 只查询调用状态的调度器
				param.setEnabledFlag("Y");
				jobs = this.list(param);
			}
			
			if (jobs != null) {
				int i=0;
				for (Job job : jobs) {
					try {
						this.stop(job);
						i++;
						if(i == jobs.size())
						{
							logger.info("共停止" + i + "个调度");
						}
					} catch (Exception e) {
						logger.error("停止调度失败", e);
						// 停止失败
						job.setStatus(ST_RUNNING);
						// 更新
						this.jobBiz.update(job);
					}
				}
			}
//			this.getScheduler().clear();
//			this.getScheduler().shutdown(true);
		} catch (Exception e) {
			logger.error("停止调度器失败", e);
			throw new BusinessException("", e);
		}
	}

	/**
	 * 启动任务
	 * 
	 * @param jobId
	 */
	public void start(Job job) {
		try {
			Scheduler scheduler = getScheduler();
			// 调度ID
			String id = job.getId();
			// 调度类
			String className = job.getJobClass();
			// 调度时间配置表达式
			String cronExpression = job.getCronExpression();
			Class clazz = Class.forName(className).asSubclass(org.quartz.Job.class);
			JobDetail jobDetail = newJob(clazz).withIdentity(id, id).build();
			jobDetail.getJobDataMap().put("param", job.getJobParam());// 传递参数
			
			CronTrigger cronTrigger = newTrigger().withIdentity(id, id).withSchedule(cronSchedule(cronExpression)).build();

			logger.info("启动任务:" + job.getName());
			
			if(!scheduler.checkExists(jobDetail.getKey()))
			{
				scheduler.scheduleJob(jobDetail, cronTrigger);
				// Set up the listener
		        JobListener listener = new BaseJobListener(id);
		        Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
		        scheduler.getListenerManager().addJobListener(listener, matcher);
			}
			
			if (!scheduler.isStarted()) {
				scheduler.start();
			}

			job.setStatus(ST_RUNNING);
			this.jobBiz.update(job);

			JobConstant.setStatus(id, JobConstant.STATUS_RUNNING);
		} catch (Exception e) {
			logger.error("启动任务失败", e);
			throw new BusinessException(e.getMessage());
		}
	}

	/**
	 * 停止任务
	 * 
	 * @param jobId
	 */
	public void stop(Job job) {
		try {
			Scheduler scheduler = getScheduler();
			
			GroupMatcher<JobKey> groupMatcher = GroupMatcher.jobGroupEquals(job.getId());
			Set<JobKey> jobKeySet = scheduler.getJobKeys(groupMatcher);
			for (JobKey jobKey2 : jobKeySet) {
				scheduler.deleteJob(jobKey2);
			}
			
			// 停止状态
			job.setStatus(ST_STOP);
			logger.info("停止任务:" + job.getName());

			this.jobBiz.update(job);
			JobConstant.setStatus(job.getId(), JobConstant.STATUS_STOP);
		} catch (Exception e) {
			logger.error("停止任务失败", e);
			throw new BusinessException("", e);
		}
	}

	/**
	 * 立即执行
	 */
	public void startImmediately(String jobId) {
		try {
			Scheduler scheduler = getScheduler();
			Job job = this.getById(jobId);
			// 调度ID
			String id = job.getId();
			// 调度类
			String className = job.getJobClass();
			logger.info("立即执行调度:" + job.getName());
			Class clazz = Class.forName(className).asSubclass(org.quartz.Job.class);
			String RandomId = id+"A";
			JobDetail jobDetail = newJob(clazz).withIdentity(RandomId, RandomId).build();
			jobDetail.getJobDataMap().put("param", job.getJobParam());// 传递参数
			
			Trigger trigger = newTrigger().withIdentity(RandomId, RandomId).startNow().build();

			scheduler.scheduleJob(jobDetail, trigger);
			
			if (!scheduler.isStarted()) {
				scheduler.start();
			}
			Thread.sleep(1000);
			
			GroupMatcher<JobKey> groupMatcher = GroupMatcher.jobGroupEquals(RandomId);
			Set<JobKey> jobKeySet = scheduler.getJobKeys(groupMatcher);
			for (JobKey jobKey2 : jobKeySet) {
				scheduler.deleteJob(jobKey2);
			}
			
//			SchedulerMetaData metaData = scheduler.getMetaData();
//			System.out.println(metaData.getSummary());
		} catch (Exception e) {
			logger.error("立即执行调度失败", e);
		}
	}

	/**
	 * 获取Scheduler对象
	 * 
	 * @return
	 * @throws SchedulerException 
	 * @throws SchedulerException
	 */
	protected Scheduler getScheduler() throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		logger.debug("获取Scheduler对象");
		return scheduler;
	}

	/**
	 * 获取任务状态
	 * 
	 * @param jobId
	 * @return
	 */
	public String getStatus(String jobId) {
		String status = JobConstant.getStatus(jobId);

		if (status == null) {
			return JobConstant.STATUS_STOP;
		}

		return status;
	}
	
	/**
	 * 启动调度告警
	 */
	public void startMonitor()
	{
		logger.info("启动调度告警");
		if(!JobConstant.isMonitorRunning())
		{
			new Thread(new Runnable(){
				@Override
				public void run() {
					
						JobConstant.setMonitorRunning(true);
						
						while(JobConstant.isMonitorRunning())
						{
							try
							{
								Job job = new Job();
								job.setEnabledFlag("Y");
								List<Job> list  = list(job);
	
								for (Job job2 : list) {
									check(job2);
								}
								
								// 调度扫描时间,精确分钟
								long alertInterval = (long)(ConfigurationUtil.getDoubleValue("sysmgt.job.alert.interval") * 60 * 1000);
								
								Thread.sleep(alertInterval);
							}
							catch (Exception e) {
								logger.error("",e);
							}
						}
				}
				
			}).start();
		}
		else
		{
			throw new BusinessException("调度告警已启动");
		}
		
	}
	
	private void check(Job job)
	{
		LinkedHashMap<String, JobLog> map = JobConstant.getJobLogMap(job.getId());
		if(map != null)
		{
			logger.info("检查调度" + job.getName() + "状态...");
			JobLog jobLog = null;
			int i = 0;
			for (Entry<String, JobLog> entry : map.entrySet()) {
				i++;
				if(i == map.size())
				{
					jobLog = entry.getValue();
				}
				
			}
			long nextFireTime = DateUtil.StringToDate(jobLog.getNextFireTime(), DateUtil.DEFAULT_FORMAT).getTime();
			
			long now = new Date().getTime();
			
			// 调度停止超这个时间后，告警
			long alertInterval = (long)(ConfigurationUtil.getDoubleValue("sysmgt.job.stop.interval") * 60 * 1000);
			
			long unrunningTime = now - nextFireTime;
			
			if(unrunningTime > alertInterval)
			{
//				logger.info("警告：调度" + job.getName()+"已经停止" + unrunningTime/1000 + "s");
				if(JobConstant.GRADE_IMP.equals(job.getGrade()))
				{
					// 发短信、发邮件、记日志
					logger.error("调度" + job.getName()+"已经停止" + unrunningTime/60000 + "分钟,发短信、发邮件、记日志");
					//sendSMS(job.getName(), unrunningTime/60000+"");
					//sendMail(job.getName(), unrunningTime/60000+"");
				}
				else if(JobConstant.GRADE_NOR.equals(job.getGrade()))
				{
					// 发邮件、记日志
					// 发短信、发邮件、记日志
					logger.error("调度" + job.getName()+"已经停止" + unrunningTime/60000 + "分钟,发邮件、记日志");
					//sendMail(job.getName(), unrunningTime/60000+"");
				}
				else
				{
					// 记日志
					// 发短信、发邮件、记日志
					logger.error("调度" + job.getName()+"已经停止" + unrunningTime/60000 + "分钟,记日志");
				}
			}
		}
	}
	
	/**
	 * 停止调度告警
	 */
	public void stopMonitor()
	{
		if(JobConstant.isMonitorRunning())
		{
			logger.info("停止调度告警");
			JobConstant.setMonitorRunning(false);
		}
		else
		{
			throw new BusinessException("调度告警已停止");
		}
	}
	
	private void sendMail(String jobName,String stopTime)
	{
		try
		{
			Map<String,String> map = new HashMap<String,String>();
			map.put("ip", IPUtil.getRealIpList().get(0));
			map.put("jobName", jobName);
			map.put("stopTime", stopTime);
			MailTemplate mialContent = new MailTemplate();
			//String str = ConfigurationUtil.getStringValue("sysmgt.job.alert.maillist");
			//edit by zhugj on sep 2,2014
			/*EmailConfig emailConfig = emailConfigManager.getEmailConfigByTypeId(Constant.SYSMGT_JOB_ALERT_MAILLIST);
			String str = emailConfig.getToEmail(); //调度告警邮件收件人
			String[] address = str.split(",");
			mialContent.setTo(address);
			mialContent.setSubject("调度告警");
			mialContent.setFrom(Constant.SEND_EMAIL_ADDRESS);
			mialContent.setObject(map);
			mialContent.setType("错误通知");
			mialContent.setFreeMarkerTemplate("/email/sysmgt_job_alert.ftl");
			mialContent.setIsRealtime("Y");*/
			
			//发送给相应的E-mail
			//EMailUtil.sendMail(mialContent);
		}
		catch(Exception e)
		{
			logger.error("",e);
		}
		
	}
	
	private void sendSMS(String jobName,String stopTime)
	{
		try {
			SmsVo smsVo = new SmsVo();
			smsVo.setContent("【告警】"+IPUtil.getRealIpList().get(0)+"服务器调度："+jobName+" 已经停止"+stopTime+"分钟");
			smsVo.setModelType(ModelType.MODEL_TYPE_MEMBER_FIND_PASSWORD);
			String mobiles = ConfigurationUtil.getStringValue("sysmgt.job.alert.mobilelist");
			String[] mobilearr = mobiles.split(",");
			for (String mobile : mobilearr) {
				smsVo.setPhone(new String[]{mobile});
				//smsAip.sendNow(smsVo);
			}
		} catch (RuntimeException e) {
			logger.error("",e);
		}

	}
	
}
