package com.example.common.domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.util.JdbcUtil;

/**
 * 
 * 根据优购物流公司编码查询宝宏物流公司编码
 * 
 * @author Administrator
 *
 */
public class BaohongLogistic {

	private static final Logger logger = LoggerFactory.getLogger(BaohongLogistic.class);
	
	private static Map<String, String> baohongLogisticMap = new HashMap<String, String>();

	/**
	 * 根据优购物流公司编码查询宝宏物流公司编码
	 * 
	 * @param logisticCode
	 * @return
	 */
	public static String getBaohongLogistic(String logisticCode) {
		String baohongLogistic = baohongLogisticMap.get(logisticCode);
		if (StringUtils.isEmpty(baohongLogistic)) {
			freshBaohongLogisticMap();
			baohongLogistic = baohongLogisticMap.get(logisticCode);
		}
		return baohongLogistic;
	}

	/**
	 * 重新获取优购宝宏物流公司对应关系
	 * 
	 * @return
	 */
	public static void freshBaohongLogisticMap() {
		Connection conn = null;
		try {
			String sql = "select t.yg_logistics_no,IF(t.chain_logistics_no='','其它',UPPER(t.chain_logistics_no)) from tbl_chain_logistics t where chain_seller_no = 'BAOHONG' ";
			conn = JdbcUtil.getConnection("dataSource_b2c");
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String code = rs.getString(1);
				String baohongCode = rs.getString(2);
				BaohongLogistic.baohongLogisticMap.put(code, baohongCode);
			}
		} catch (Exception e) {
			logger.error("freshBaohongLogisticMap()", e);
		} finally {
			JdbcUtil.close(conn);
		}
	}

}
