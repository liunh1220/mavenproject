package com.example.springquartz.manager;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description: 任务执行类
 *
 * @date 2015-12-22
 * @version V1.0
 */
public class QuartzManager {

	private final static Logger logger = LoggerFactory.getLogger(QuartzManager.class);

	private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

	private static String JOB_GROUP_NAME = "EXTJWEB_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "EXTJWEB_TRIGGERGROUP_NAME";

	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 * 
	 * @param jobName
	 *            任务名
	 * @param cls
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static boolean addJob(String jobName, Class cls, String time) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();// 创建一个触发器表
			JobDetail jobDetail = JobBuilder.newJob()// 创建一个jobbuilder用来定义一个任务明细。
					.ofType(cls)// 设置类，将被实例化
					.withIdentity(new JobKey(jobName, JOB_GROUP_NAME))// 使用给定的名称和默认组jobkey识别任务明细
					.build();// 产品定义的JobDetail实例这jobbuilder。
			Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(time))// 设置schedulebuilder将用于定义触发器的表。
					.withIdentity(new TriggerKey(jobName, TRIGGER_GROUP_NAME)).build();// 创建Trigger
			scheduler.scheduleJob(jobDetail, trigger);// 绑定
			// 启动
			if (!scheduler.isShutdown()) {
				// scheduler.start();
			}
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 */
	@SuppressWarnings("rawtypes")
	public static boolean modifyJobTime(String jobName, String time) {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			CronTrigger trigger = (CronTrigger) sched.getTrigger(triggerKey);
			if (trigger == null) {
				return false;
			}
			String oldTime = ((CronTrigger) trigger).getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME));
				Class objJobClass = jobDetail.getJobClass();
				removeJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME), new JobKey(jobName, JOB_GROUP_NAME));
				addJob(jobName, objJobClass, time);
			}
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param jobName
	 * @param time
	 * @throws SchedulerException
	 */
	public static boolean updateJobTime(String jobName, String time) {
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(jobName, TRIGGER_GROUP_NAME);
			Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(time))// 设置schedulebuilder将用于定义触发器的表。
					.withIdentity(triggerKey).build();// 创建Trigger
			pauseJob(jobName);// 先暂停执行
			scheduler.rescheduleJob(triggerKey, trigger);// 按新的trigger重新设置job执行
			resumeJob(jobName);// 重新执行
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 * 
	 * @param triggerKey
	 * @param jobKey
	 */
	public static boolean removeJob(TriggerKey triggerKey, JobKey jobKey) {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			sched.pauseTrigger(triggerKey);// 停止触发器
			sched.unscheduleJob(triggerKey);// 移除触发器
			sched.deleteJob(jobKey);
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 启动所有定时任务
	 */
	public static boolean startJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			sched.start();
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 暂停任务
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public static boolean pauseJob(String jobName) {
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.pauseJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 恢复任务
	 * 
	 * @param scheduleJob
	 * @return
	 */
	public static boolean resumeJob(String jobName) {
		JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME);
		try {
			Scheduler scheduler = schedulerFactory.getScheduler();
			scheduler.resumeJob(jobKey);
			return true;
		} catch (SchedulerException e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 关闭所有定时任务
	 */
	public static boolean shutdownJobs() {
		try {
			Scheduler sched = schedulerFactory.getScheduler();
			if (!sched.isShutdown()) {
				sched.shutdown();
			}
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}
}