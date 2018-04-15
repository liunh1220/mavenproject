package com.example.common.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlUtil {

	private static final Logger logger=LoggerFactory.getLogger(MySqlUtil.class);
	/**
	 * mysql分页查询
	 * @author ren.zj
	 * @param sqlParams
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public static Page findPageBySqlForParams(SQLParams sqlParams, int pageNo, int pageSize) {
		List<Object[]> list = new ArrayList<Object[]>();
		Integer startIndex = pageNo * pageSize - pageSize;
		Integer totalCount = 0;
		Page page = new Page();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;

		try {
			totalCount = getTotalCountForSalve(sqlParams);
			if (totalCount != 0) {
				conn = JdbcUtil.getConnection("dataSource_b2c");
				// 创建PreparedStatement对象
				pstmt = conn.prepareStatement(sqlParams.getSql() + " LIMIT ?,?");

				List<Object> params = sqlParams.getParams();

				int j = 0;
				if (CollectionUtils.isNotEmpty(params)) {
					int paramsSize = params.size();

					for (j = 0; j < paramsSize; j++) {
						pstmt.setObject(j + 1, params.get(j));
					}
				}
				pstmt.setObject(j + 1, startIndex);
				pstmt.setObject(j + 2, pageSize);

				resultSet = pstmt.executeQuery();

				ResultSetMetaData rsmd = resultSet.getMetaData();
				int colCount = rsmd.getColumnCount();
				while (resultSet.next()) {
					Object[] objs = new Object[colCount];
					for (int i = 0; i < colCount; i++) {
						objs[i] = resultSet.getObject(i + 1);
					}
					list.add(objs);
				}
				page.setPageSize(pageSize);
				page.setPageNo(pageNo);
				page.setResult(list);
			}
			page.setCount(totalCount);
		} catch (Exception e) {
			logger.error("分页查询dataSource_b2c数据库失败", e);
		} finally {
			JdbcUtil.close(conn); // 关闭相关连接
		}

		return page;
	}
	
	/**
	 * 
	 * @description 总记录数
	 * @author rzj
	 * @date 2013-9-6 
	 * @param sqlParams
	 * @return
	 */
	public static Integer getTotalCountForSalve(SQLParams sqlParams) {
		Integer totalCount = 0;

		String sql = sqlParams.getSql();

		if (StringUtils.isNotBlank(sql)) {

			String tempSql = sql.toLowerCase();

			String countSql = "SELECT COUNT(*) FROM (" + tempSql + ") ct";

			SQLParams countSQLParams = new SQLParams();
			countSQLParams.setSql(countSql);
			countSQLParams.setParams(sqlParams.getParams());

			Object[] objs = findUniqueBySQLForParams(countSQLParams);
			if (!ArrayUtils.isEmpty(objs)) {
				totalCount = null == objs[0] ? 0 : Integer.parseInt(objs[0].toString().trim());
			}
		}
		return totalCount;
	}
	
	
	
	/**
	 * 获取单条记录
	 * @param sqlParams
	 * @return
	 */
	public static Object[] findUniqueBySQLForParams(SQLParams sqlParams) {
		Object[] objs = null;
		List<Object[]> list = queryBySQLForParams(sqlParams);
		if (CollectionUtils.isNotEmpty(list) && !ArrayUtils.isEmpty(list.get(0))) {
			objs = list.get(0);
		}
		return objs;
	}
	
	/** 获取list集合
	 * @author ren.zj
	 * @param sqlParams
	 * @return
	 */
	public static List<Object[]> queryBySQLForParams(SQLParams sqlParams) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		List<Object[]> objList = new ArrayList<Object[]>();
		try {
			conn = JdbcUtil.getConnection("dataSource_b2c");
			// 创建PreparedStatement对象
			pstmt = conn.prepareStatement(sqlParams.getSql());

			List<Object> params = sqlParams.getParams();
			if (CollectionUtils.isNotEmpty(params)) {
				int paramsSize = params.size();
				for (int j = 0; j < paramsSize; j++) {

					pstmt.setObject(j + 1, params.get(j));

				}
			}
			resultSet = pstmt.executeQuery();
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int colCount = rsmd.getColumnCount();
			while (resultSet.next()) {
				Object[] objs = new Object[colCount];
				for (int i = 0; i < colCount; i++) {
					objs[i] = resultSet.getObject(i + 1);
				}
				objList.add(objs);
			}
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			JdbcUtil.close(conn); // 关闭相关连接
		}
		return objList;
	}
}
