package com.example.common.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 淘宝订单接口 jdbc工具帮助类
 * @author Administrator
 *
 */
public class JdbcUtil {
	@Autowired
	private static ApplicationContextUtil appUtil;
	
	private static final Logger logger = LoggerFactory.getLogger(JdbcUtil.class);
	
	/**
	 * 获取 数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Connection getConnection(String dataSourceBean) throws Exception {
		try {
			DataSource dataSource = (DataSource) appUtil.getBean(dataSourceBean);
			Connection conn = dataSource.getConnection();			
			return conn;
		} catch (Exception e) {
			logger.error("获取数据库连接失败！",e);
			throw new Exception();
		}
	}
	
	
	/**
	 * 关闭数据库相关连接
	 * 
	 * @param connection
	 */
	public static void close(Connection conn) {
		if(conn != null)
		{
			try {
				conn.close();
			} catch (SQLException e) {
				logger.error("close", e);
			}
		}
	}
	
}
