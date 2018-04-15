package com.example.springquartz.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.example.springquartz.entity.MyQuartzJobBean;

public class TaskUtils {
	
	private final static Logger logger = LoggerFactory.getLogger(TaskUtils.class);     
    
	/**     
	 * 通过反射调用scheduleJob中定义的方法     
	 *      
	 * @param scheduleJob     
	 */     
	public static void invokMethod(MyQuartzJobBean scheduleJob) {  
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext(); 
		Object object = null;     
		Class<?> clazz = null; 
        //springId不为空先按springId查找bean     
		if (StringUtils.isNoneBlank(scheduleJob.getSpringId())) {     
			object = context.getBean(scheduleJob.getSpringId());     
		} else if (StringUtils.isNotBlank(scheduleJob.getJobClass())) {//按jobClass查找     
			try {     
				clazz = Class.forName(scheduleJob.getJobClass());     
				object = clazz.newInstance();     
			} catch (Exception e) {     
				e.printStackTrace();     
			}     
		}     
		if (object == null) {     
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类是否配置正确！！！");     
			return;     
		}     
		clazz = object.getClass();     
		Method method = null;     
		try {     
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());     
		} catch (NoSuchMethodException e) {     
			logger.error("任务名称 = [" + scheduleJob.getJobName() + "]---------------未启动成功，请检查执行类的方法名是否设置错误！！！");     
		} catch (SecurityException e) {     
			e.printStackTrace();		     
		}     
		if (method != null) {     
			try {     
				method.invoke(object);     
			} catch (IllegalAccessException e) {     
				e.printStackTrace();     
			} catch (IllegalArgumentException e) {     
				e.printStackTrace();     
			} catch (InvocationTargetException e) {     
				e.printStackTrace();     
			}     
		}		     
	}     
     
	/**     
	 * 判断cron时间表达式正确性     
	 * @param cronExpression     
	 * @return      
	 */     
	public static boolean isValidExpression(final String cronExpression){     
		CronTriggerImpl trigger = new CronTriggerImpl();        
        try {     
			trigger.setCronExpression(cronExpression);     
			Date date = trigger.computeFirstFireTime(null);       
	        return date != null && date.after(new Date());        
		} catch (ParseException e) {     
		}      
        return false;     
	}     
	     
	     
}
