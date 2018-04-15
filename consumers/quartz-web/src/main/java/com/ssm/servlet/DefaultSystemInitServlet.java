package com.ssm.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServlet;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.lang.StringUtils;
import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.manager.IJobManager;
import com.ssm.util.ConfigurationHelper;
import com.ssm.util.ConfigurationUtil;
import com.ssm.util.IPUtil;
import com.ssm.util.ServiceLocator;

/**
 * 系统启动初始化操作
 * 
 */
public class DefaultSystemInitServlet extends HttpServlet {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID= 7256917245188838800L;

	/**
	 * logger.
	 */
	private static final Logger	logger= LoggerFactory.getLogger(DefaultSystemInitServlet.class);

	/**
	 * quartz调度器.
	 */
	private static Scheduler scheduler= null;
	
	/**
	 * 初始化
	 */
	public void init() {
		ConvertUtils.register(new IntegerConverter(null), Integer.class);
		ConvertUtils.register(new LongConverter(null), Long.class);

		// 初始化配置文件根目录
		//initRootConfigPath();
		// 初始化日志配置
//		initLog4j();
		//初始化quartz配置(如果子系统有用quartz).
//		initQuartz();
		// 初始化项目路径
		initWebRootPath();
		// 初始化调度
		initJob();
	}

	/**
	 * 销毁.
	 */
	public void destroy() {

	}
	
	/**
	 * 初始化项目路径
	 */
	private void initWebRootPath()
	{
		logger.info("系统路径："+this.getServletContext().getRealPath(""));
		System.setProperty("web.root", this.getServletContext().getRealPath(""));
	}


	/**
	 * 初始化配置文件根目录.
	 */
	/*private void initRootConfigPath() {
		String userDir = AppServerUtil.getUserDir();
		String configRootPath = userDir + File.separator + "config";
		ConfigurationHelper.setBasePath(configRootPath);
		logger.info("初始化配置文件config目录:" + configRootPath);
		String deployConfigRootPath = userDir + File.separator + "deploy_config";
		ConfigurationHelper.setDeployBasePath(deployConfigRootPath);
		logger.info("初始化配置文件deployConfig目录:" + deployConfigRootPath);
	}*/


	/**
	 * 日志.
	 */
	/*public void initLog4j() {
		String userDir = AppServerUtil.getUserDir();
		String logRootPath = userDir + File.separator + "log";
		TimeSizeRollingFileAppender.setLogRootPath(logRootPath);

		String log4jConfig = this.getInitParameter("log4jConfig");
		if (log4jConfig == null || log4jConfig.length() == 0) {
			logger.error("log4j参数不存在");
			return;
		}
		String log4jFileName = ConfigurationHelper.getFullFileName(log4jConfig);
		try {
			DOMConfigurator.configureAndWatch(log4jFileName, 60000);
		}
		catch (Exception e) {
			System.out.println("###加载log4j.xml出错!");
			e.printStackTrace();
		}

		logger.info("加载log4j.xml成功!");
	}*/


	/**
	 * 初始化quartz配置(quartz配置文件目录等).
	 */
	public void initQuartz() {
		// 从init-param中得到quartz.properties配置文件相对路径.
		String quartzConfig = this.getInitParameter("quartzConfig");
		if (quartzConfig == null || quartzConfig.length() == 0) {
			logger.info("没有指定quartz配置文件相对路径！");
			return;
		}
		// 得到quartz配置文件全路径名
		String quartzConfigFileName = ConfigurationHelper.getFullFileName(quartzConfig);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(quartzConfigFileName);
		}
		catch (FileNotFoundException e) {
			logger.error("找不到quartz配置文件,文件名:" + quartzConfigFileName);
			return;
		}
		// 得到quartz配置文件的目录
		String quartzConfigPath = StringUtils.substringBeforeLast(quartzConfigFileName, "/");
		// 加载配置文件
		Properties properties = new Properties();
		try {
			properties.load(fileInputStream);
			// 设置quartz_jobs.xml路径
			properties.setProperty("org.quartz.plugin.jobInitializer.fileName", quartzConfigPath + File.separator
					+ "quartz_jobs.xml");
		}catch (IOException e) {
			logger.error("加载quartz配置文件失败,文件名:" + quartzConfigFileName);
			return;
		}
		// 启动quartz
		StdSchedulerFactory factory = null;
		try {
			factory = new StdSchedulerFactory(properties);
		}catch (Exception e) {
			logger.error("初始化quartz失败,失败原因:" ,e);
			return;
		}
		// 启动quartz任务调度器
		try {
			scheduler = factory.getScheduler();
			scheduler.start();
		}catch (Exception e) {
			logger.error("启动quartz任务调度失败,失败原因:", e);
			return;
		}
		logger.info("启动quartz任务调度成功！");
	}
	
	/**
	 * 初始化调度
	 */
	public void initJob()
	{
		List<Object> runningIps = ConfigurationUtil.getListValue("sysmgt.job.running.ip", ",", String.class);
		for (Object runningIp : runningIps) {
			String runningIpStr = (String)runningIp;
			if(IPUtil.isLocalIp(runningIpStr))
			{
				IJobManager jobManager = (IJobManager)ServiceLocator.getBean("jobManagerImpl");
				jobManager.start();
				break;
			}
		}
	}
	
}
