package com.example.hibernate.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class DataSourceSwitcher {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceSwitcher.class);

	private static final ThreadLocal contextHolder = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static void setDataSource(String dataSource) {
		Assert.notNull(dataSource, "dataSource cannot be null");
		contextHolder.set(dataSource);
	}

	public static void setMaster(){
		clearDataSource();
		setDataSource("master");
		logger.info("启用主数据库连接Master");
    }

	public static void setSlave() {
		clearDataSource();
		setDataSource("slave1");
		logger.info("启用从数据库连接"+"slave1");
	}

	public static String getDataSource() {
		Object o = contextHolder.get();
		if(o==null)
			return "slave1";
		return (String) o;
	}

	public static void clearDataSource() {
		contextHolder.remove();
	}
}


