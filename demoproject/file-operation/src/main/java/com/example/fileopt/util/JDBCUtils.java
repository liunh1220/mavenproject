package com.example.fileopt.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * 类JDBCUtils.java的实现描述：TODO JDBC操作简单工具类
 */
public class JDBCUtils {

	private static final Logger logger = Logger.getLogger(JDBCUtils.class);

	private static JDBCUtils jdbcUtils = null;

	// private String jdbc_driver = "com.mysql.jdbc.Driver"; // jdbc驱动
	//
	// private String jdbc_url;// =
	// //
	// null;//"jdbc:mysql://172.20.1.93:3306/yitian_b2c_db?useUnicode=true&characterEncoding=utf-8";
	// // //jdbc连接Url
	//
	// private String user_name;// = "root"; //jdbc连接用户名
	//
	// private String user_password;// ="hzc"; //jdbc连接密码

	private String batch_size = "200"; // 批量提交数

	private JDBCUtils() {
	}

	// private void initProperty() throws Exception {
	// jdbc_url = getConfigValueByKey(Constant.JDBC_URL);
	// user_name = getConfigValueByKey(Constant.JDBC_USERNAME);
	// user_password = getConfigValueByKey(Constant.USER_PASSWORD);
	// batch_size = getConfigValueByKey(Constant.BATCH_SIZE);
	// }

	// private String getConfigValueByKey(String key) throws Exception {
	// ServiceLocator serviceLocator = ServiceLocator.getInstance();
	// ISystemConfigService configService =
	// serviceLocator.getBeanFactory().getBean(SystemConfigServiceImpl.class);
	// try {
	// SystemConfig config = configService.getCacheSystemConfigByKey(key);
	// return config.getValue();
	// } catch (Exception e) {
	// logger.error("getConfigValueByKey失败！",e);
	// throw new Exception();
	// }
	// }

	/**
	 * 创建JDBC工具类实例
	 * 
	 * @return
	 */
	public static synchronized JDBCUtils getInstance() {

		if (jdbcUtils == null) {
			jdbcUtils = new JDBCUtils();
		}
		return jdbcUtils;
	}

	/**
	 * 获取 数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConnection() throws Exception {
		try {
			// initProperty();
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSource");
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			logger.error("获取数据库连接失败！", e);
			throw new Exception();
		}
	}

	/**
	 * 获取主库数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionForMaster() throws SQLException {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSource1");
		return dataSource.getConnection();
	}

	
	/**
	 * 获取 数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getConnectionForSys() throws Exception {
		try {
			// initProperty();
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSourceSys");
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			logger.error("获取yitian数据库连接失败！", e);
			throw new Exception();
		}
	}
	
	/**
	 * 获取从库数据库连接(amoeba)
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionForSalve() throws SQLException {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSource");
		return dataSource.getConnection();
	}

	/**
	 * 获取从Oracle库数据库连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionForOracle(String dataSourceName) throws SQLException {
		ServiceLocator serviceLocator = ServiceLocator.getInstance();
		DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean(dataSourceName);
		return dataSource.getConnection();
	}

	public Connection WmsConnectionInitproperty(String driverkey, String urlkey, String usernamekey, String passwordkey) throws Exception {
		Connection conn;
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSource1");
			conn = dataSource.getConnection();
		} catch (Exception e) {
			logger.error("获取数据库连接失败！", e);
			throw new Exception();
		}
		return conn;

	}

	/**
	 * 关闭数据库相关连接
	 * 
	 */
	public void close(ResultSet rs, Statement st, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
				st = null;
			} catch (SQLException e) {
				logger.error("close失败！", e);
			} finally {
				try {
					if (conn != null)
						conn.close();
					conn = null;
				} catch (SQLException e) {
					logger.error("close失败！", e);
				}
			}
		}
	}

	/**
	 * 关闭数据库相关连接
	 * 
	 */
	private void close(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
		} catch (SQLException e) {
			logger.error("close失败！", e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				logger.error("close失败！", e);
			}
		}
	}

	/**
	 * 增加单条数据
	 * 
	 * @param sql
	 *            sql语句
	 * @param values
	 *            参数值
	 * @return 是否增加成功
	 * @throws Exception
	 */
	public boolean saveOrUpdate(String sql, Object... values) throws Exception {
		Connection conn = getConnection(); // 获取数据库连接
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false); // 设置手动提交事务
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			// 赋值
			if (null != values) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			pstmt.execute(); // 执行操作
			conn.commit(); // 提交事务
			close(pstmt, conn); // 关闭相关连接
		} catch (SQLException e) {
			logger.error("saveOrUpdate失败！", e);
			throw new Exception();
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return true;
	}

	/**
	 * 删除
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public boolean batchDelete(String sql) {
		Connection conn = null; // 获取数据库连接
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // 设置手动提交事务
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象

			pstmt.execute(); // 执行操作
			conn.commit(); // 提交事务
			close(pstmt, conn); // 关闭相关连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("batchDelete失败！", e);
			return false;
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return true;

	}

	/**
	 * 批量增加与修改
	 * 
	 * @param sql
	 *            insert or update 语句
	 * @return
	 * @throws Exception
	 * @throws Exception
	 * @throws SQLException
	 */
	public boolean batchSaveOrUpdate(String sql, List<Object[]> paramList) {
		PreparedStatement pstmt = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = getConnection(); // 获取数据库连接
			count = Integer.parseInt(batch_size);
			conn.setAutoCommit(false); // 设置手动提交事务
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			// 赋值
			for (int i = 0; i < paramList.size(); i++) {

				Object[] values = paramList.get(i);
				for (int j = 0; j < values.length; j++) {
					pstmt.setObject(j + 1, values[j]);
				}
				pstmt.addBatch();

				// 批量数等于 batch_size 时 提交数据
				if (i != 0 && ((i + 1) % count == 0)) {
					int ids[] = pstmt.executeBatch(); // 执行操作
					if (ids.length == count) {
						conn.commit(); // 提交事务
					} else {
						conn.rollback(); // 事务回滚
					}
					pstmt.clearBatch();
				}
			}

			int ids[] = pstmt.executeBatch(); // 执行操作
			if (ids.length == paramList.size() % (count)) {
				conn.commit(); // 提交事务
			} else {
				conn.rollback(); // 事务回滚
			}
		} catch (Exception e) {
			logger.error("batchSaveOrUpdate失败！", e);
			return false; // 如果异常就返回false
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return true;
	}

	/**
	 * 批量更新 or 保存
	 * 
	 * @author huangbin
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean batchAllSaveOrUpdate(Map<String, List<Object[]>> map) throws Exception {
		if (map == null || map.size() == 0)
			return false;
		// int count = Integer.parseInt(batch_size)-1;
		Connection conn = getConnection(); // 获取数据库连接
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			for (Iterator<Entry<String, List<Object[]>>> iter = map.entrySet().iterator(); iter.hasNext();) {
				Entry<String, List<Object[]>> entry = iter.next();
				// 动态获取preparedstatement对象
				ps = conn.prepareStatement(entry.getKey());
				for (int i = 0; i < entry.getValue().size(); i++) {
					Object[] obj = entry.getValue().get(i);
					for (int j = 0; j < obj.length; j++) {
						ps.setObject(j + 1, obj[j]);
					}

					ps.addBatch();

					if (i == entry.getValue().size() - 1) {

						int[] num = ps.executeBatch();

						if (num.length != entry.getValue().size()) {
							throw new Exception("执行的数据记录不相等");
						}

						ps.clearBatch();

					}
				}
			}

			conn.commit();
		} catch (SQLException e) {
			logger.error("batchAllSaveOrUpdate失败！", e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				return false;
			}

			return false;
		} finally {
			close(ps, conn);
		}
		return true;
	}

	/**
	 * 批量更新 or 保存 执行结果不比较
	 * 
	 * @author huangbin
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean batchAllSaveOrUpdateAndNoCompare(Map<String, List<Object[]>> map) throws Exception {
		if (map == null || map.size() == 0)
			return false;
		Connection conn = getConnection(); // 获取数据库连接
		PreparedStatement ps = null;
		try {
			for (Iterator<Entry<String, List<Object[]>>> iter = map.entrySet().iterator(); iter.hasNext();) {
				Entry<String, List<Object[]>> entry = iter.next();
				// 动态获取preparedstatement对象
				ps = conn.prepareStatement(entry.getKey());
				Object[] obj =null;
				try {
					for (int i = 0; i < entry.getValue().size(); i++) {
						obj = entry.getValue().get(i);
						for (int j = 0; j < obj.length; j++) {
							ps.setObject(j + 1, obj[j]);
						}
						ps.addBatch();
						if (i == entry.getValue().size() - 1) {
							int[] num = ps.executeBatch();
							if (num.length != entry.getValue().size()) {
								throw new Exception("执行的数据记录不相等");
							}
							ps.clearBatch();
						}
					}
				}catch (SQLException e) {
					logger.error("系统过滤时发现异常的订单  ", e);
				}
			}
		} catch (SQLException e) {
			logger.error("batchAllSaveOrUpdate失败！", e);
			return false;
		} finally {
			close(ps, conn);
		}
		return true;
	}

	/**
	 * 
	 * 方法描述：在调用处提交事务
	 * 
	 * @author xiongjingang
	 * @date 2011-7-8 下午01:37:50
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public boolean batchAllSaveOrUpdate(Connection conn, Map<String, List<Object[]>> map) throws SQLException {
		logger.info("批量JDBC开始---------------------------------------");
		boolean success = false;
		if (map == null || map.size() == 0) {
			return false;
		}
		PreparedStatement ps = null;
		try {
			conn.setAutoCommit(false);
			for (Iterator<Entry<String, List<Object[]>>> iter = map.entrySet().iterator(); iter.hasNext();) {
				Entry<String, List<Object[]>> entry = iter.next();
				// 动态获取preparedstatement对象
				ps = conn.prepareStatement(entry.getKey());
				for (int i = 0; i < entry.getValue().size(); i++) {
					Object[] obj = entry.getValue().get(i);
					for (int j = 0; j < obj.length; j++) {
						ps.setObject(j + 1, obj[j]);
					}
					ps.addBatch();
					if (i == entry.getValue().size() - 1) {

						int[] num = ps.executeBatch();

						if (num.length != entry.getValue().size()) {
							throw new SQLException("执行的数据记录不相等");
						}
						ps.clearBatch();
					}
				}
			}
			// conn.commit();
			logger.info("批量JDBC" + success + "--");
			success = true;
			logger.info("批量JDBC" + success + "--");
		} catch (SQLException e) {
			logger.error("batchAllSaveOrUpdate批量JDBC", e);
			e.printStackTrace();
		} finally {
			if (null != ps) {
				ps.close();
			}
			logger.info("状态为--" + success);
		}
		logger.info("批量JDBC结束---------------------------------------");
		return success;
	}

	/**
	 * 批量增加与修改
	 * 
	 * @param sql
	 *            insert or update 语句
	 * @return
	 * @author huangbin
	 * @throws Exception
	 * @throws Exception
	 * @throws Exception
	 * @throws SQLException
	 */
	public boolean batchOffPayUpdate(String sql, List<Object[]> paramList) throws Exception {
		PreparedStatement pstmt = null;
		int count = 0;
		Connection conn = null;
		try {
			conn = getConnection(); // 获取数据库连接
			count = Integer.parseInt(batch_size);
			conn.setAutoCommit(false); // 设置手动提交事务
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			// 赋值
			for (int i = 0; i < paramList.size(); i++) {

				Object[] values = paramList.get(i);
				for (int j = 0; j < values.length; j++) {
					pstmt.setObject(j + 1, values[j]);
				}
				pstmt.addBatch();

				// 批量数等于 batch_size 时 提交数据
				if (i != 0 && ((i + 1) % count == 0)) {
					int ids[] = pstmt.executeBatch(); // 执行操作
					if (ids.length == count) {
						conn.commit(); // 提交事务
					} else {
						conn.rollback(); // 事务回滚
					}
					pstmt.clearBatch();
				}
			}

			int ids[] = pstmt.executeBatch(); // 执行操作
			if (ids.length == paramList.size() % (count)) {
				conn.commit(); // 提交事务
			} else {
				conn.rollback(); // 事务回滚
			}

		} catch (Exception e) {
			logger.error("batchOffPayUpdate失败！", e);
			conn.rollback();
			throw new Exception();
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return true;
	}

	/**
	 * CSV批量导入 方法描述：TODO 方法实现描述
	 * 
	 * @date 2011-6-14 下午04:39:46
	 * @param sql
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public boolean batchSaveOrUpdatelist(String sql, List<Object> list) throws Exception {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int count = 0;
//		try {
//			// conn=WmsConnectionInitproperty(BaseDataManagerConstant.AREA_JDBC_DRIVER,BaseDataManagerConstant.AREA_JDBC_URL,BaseDataManagerConstant.AREA_JDBC_USERNAME,BaseDataManagerConstant.AREA_JDBC_PASSWORD);
//			// //获取数据库连接
//			conn = getConnection();
//			count = Integer.parseInt(BaseDataManagerConstant.AREA_JDBC_BATCH_SIZE);
//			conn.setAutoCommit(false); // 设置手动提交事务
//			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
//			// 赋值
//			for (int i = 0; i < list.size(); i++) {
//
//				WarehouseInventoryVo obj = (WarehouseInventoryVo) list.get(i);
//				pstmt.setObject(1, obj.getCkaccno());
//				pstmt.setObject(2, replaceString(obj.getColthno()));
//				pstmt.setObject(3, obj.getColthname());
//				pstmt.setObject(4, replaceString(obj.getBnno()));
//				pstmt.setObject(5, obj.getColor());
//				pstmt.setObject(6, obj.getBrand());
//				pstmt.setObject(7, obj.getHj());
//				pstmt.setObject(8, obj.getRnb());
//				pstmt.setObject(9, obj.getType());
//				pstmt.addBatch();
//
//				// 批量数等于 batch_size 时 提交数据
//				if (i != 0 && ((i + 1) % count == 0)) {
//					int ids[] = pstmt.executeBatch(); // 执行操作
//					if (ids.length == count) {
//						conn.commit(); // 提交事务
//					} else {
//						conn.rollback(); // 事务回滚
//					}
//					pstmt.clearBatch();
//				}
//			}
//
//			int ids[] = pstmt.executeBatch(); // 执行操作
//			if (ids.length == list.size() % (count)) {
//				conn.commit(); // 提交事务
//			} else {
//				conn.rollback(); // 事务回滚
//			}
//
//		} catch (SQLException e) {
//			logger.error("batchSaveOrUpdatelist失败！", e);
//			return false;
//		} finally {
//			close(pstmt, conn); // 关闭相关连接
//		}
		return true;
	}

	public String replaceString(String s) {
		return s.replace("[", "").replace("]", "");
	}

	/**
	 * 执行存储过程 方法描述：TODO 方法实现描述
	 * 
	 * @date 2011-6-14 下午04:21:27
	 * 
	 * @throws Exception
	 */
	public boolean executecallprocedure(String supplyid) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// conn=WmsConnectionInitproperty(BaseDataManagerConstant.AREA_JDBC_DRIVER,BaseDataManagerConstant.AREA_JDBC_URL,BaseDataManagerConstant.AREA_JDBC_USERNAME,BaseDataManagerConstant.AREA_JDBC_PASSWORD);
			// //获取数据库连接
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareCall("{call sp_update_area_warehouse_inventory (?)}");// 执行存储过程
			pstmt.setString(1, supplyid);
			pstmt.executeUpdate();
			// String sql = "delete from area_warehouse_inventory";
			// deletewarehouseinventory(sql, pstmt, conn);
			conn.commit();
		} catch (Exception e) {
			logger.error("executecallprocedure失败！", e);
			conn.rollback();
			throw e;
		} finally {
			close(pstmt, conn);
		}
		return true;
	}

	public void deletewarehouseinventory(String sql, PreparedStatement pstmt, Connection conn) throws Exception {
		try {
			pstmt = conn.prepareStatement(sql);// 将库存记录表数据清空
			pstmt.executeUpdate();
		} catch (Exception e) {

			logger.error("deletewarehouseinventory失败！", e);
		}
	}

//	/**
//	 * 查询b2c商品档案中不存在的商品 方法描述：TODO 方法实现描述
//	 * 
//	 * @date 2011-6-14 下午05:53:22
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public List<WarehouseInvetoryErrorVO> queryupdateinventoryEroor(String supplyID) throws Exception {
//		List<WarehouseInvetoryErrorVO> list = new ArrayList<WarehouseInvetoryErrorVO>();
//		String sql = "select * from area_warehouse_inventory_error where supplyid='" + supplyID + "'";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			// conn=WmsConnectionInitproperty(BaseDataManagerConstant.AREA_JDBC_DRIVER,BaseDataManagerConstant.AREA_JDBC_URL,BaseDataManagerConstant.AREA_JDBC_USERNAME,BaseDataManagerConstant.AREA_JDBC_PASSWORD);
//			// //获取数据库连接
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			ResultSet result = pstmt.executeQuery();
//			WarehouseInvetoryErrorVO inventoryerror;
//			while (result.next()) {
//				inventoryerror = new WarehouseInvetoryErrorVO();
//				inventoryerror.setBnno(result.getString("bnno"));
//				inventoryerror.setBrand(result.getString("brand"));
//				inventoryerror.setCkaccno(result.getString("ckaccno"));
//				inventoryerror.setColor(result.getString("colors"));
//				inventoryerror.setColthname(result.getString("colthname"));
//				inventoryerror.setColthno(result.getString("colthno"));
//				inventoryerror.setHj(result.getString("rack"));
//				inventoryerror.setRnb(result.getInt("rnb"));
//				inventoryerror.setType(result.getString("types"));
//				list.add(inventoryerror);
//			}
//			if (list.size() > 0) {
//				conn.setAutoCommit(false);
//				String delsql = "delete from area_warehouse_inventory_error where supplyid='" + supplyID + "'";
//				deletewarehouseinventory(delsql, pstmt, conn);
//				conn.commit();
//			}
//		} catch (Exception e) {
//			logger.error("queryupdateinventoryEroor失败！", e);
//			conn.rollback();
//			throw e;
//		} finally {
//			close(pstmt, conn);
//		}
//		return list;
//	}

//	/**
//	 * 根据商品编号查询相关货品信息
//	 * 
//	 * @author WangYong
//	 * @return
//	 */
//	public List<FullFieldSearchPojo> queryUpdatedProdByNo(String no) throws Exception {
//		if (no == null || no.equals(""))
//			return null;
//		List<FullFieldSearchPojo> searchPojoList = new ArrayList<FullFieldSearchPojo>();
//		String sql = "select pro.id as id,pro.commodity_id as commodityId," + "sty.no as commodityNo,pro.product_no as prodNo,"
//				+ "sty.commodity_name as prodName,sty.brand_name as brandName," + "sty.d_sno as dSno,sty.prod_briff as prodRemark,"
//				+ "sty.sale_price as prodPrice,sty.public_price as publicPrice," + "pro.size_name as size,sty.create_date as addedTime,"
//				+ "sty.default_pic as image,pro.cat_struct_name as catStruct," + "sty.price_scpe as priceScpe " + "from tbl_commodity_product pro , tbl_commodity_style sty "
//				+ "where pro.commodity_id=sty.id and pro.delete_flag = 1 " + "	and sty.delete_flag = 1 and sty.commodity_status = 2 and sty.no ='" + no + "'";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			FullFieldSearchPojo searchPojo = null;
//			while (rs.next()) {
//				searchPojo = new FullFieldSearchPojo();
//				searchPojo.setId(rs.getString("id"));
//				searchPojo.setCommodityId(rs.getString("commodityId"));
//				searchPojo.setCommodityNo(rs.getString("commodityNo"));
//				searchPojo.setProdNo(rs.getString("prodNo"));
//				searchPojo.setProdName(rs.getString("prodName"));
//				searchPojo.setBrandName(rs.getString("brandName"));
//				searchPojo.setDSno(rs.getInt("dSno"));
//				searchPojo.setProdRemark(rs.getString("prodRemark"));
//				searchPojo.setProdPrice(rs.getDouble("prodPrice"));
//				searchPojo.setPublicPrice(rs.getDouble("publicPrice"));
//				searchPojo.setSize(rs.getString("size"));
//				searchPojo.setAddedTime(rs.getDate("addedTime"));
//				searchPojo.setImage(rs.getString("image"));
//				searchPojo.setCatStruct(rs.getString("catStruct"));
//				searchPojo.setPriceScpe(rs.getString("priceScpe"));
//				searchPojoList.add(searchPojo);
//			}
//		} catch (Exception e) {
//			logger.error("出错", e);
//			throw e;
//		} finally {
//			close(pstmt, conn);
//		}
//		return searchPojoList;
//	}

//	/**
//	 * 查询商品分类信息
//	 * 
//	 * @author WangYong
//	 * @param level
//	 * @param catStruct
//	 * @return
//	 * @throws Exception
//	 */
//	public LevelVo queryCatInfo(Integer level, String catStruct) throws Exception {
//		if (level == null || catStruct == null || catStruct.equals(""))
//			return null;
//		String sql = "select cat.id as levelId ,cat.cat_name as levelName ," + "cat.no as levelNo,cat.struct_name as levelStruts "
//				+ "from tbl_commodity_catb2c cat where cat.level = " + level + " and cat.delete_flag=1 " + "and '" + catStruct + "' like concat(cat.struct_name,'%')";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				LevelVo levelVo = new LevelVo();
//				levelVo.setLevelId(rs.getString("levelId"));
//				levelVo.setLevelName(rs.getString("levelName"));
//				levelVo.setLevelNo(rs.getString("levelNo"));
//				levelVo.setLevelStruts(rs.getString("levelStruts"));
//				return levelVo;
//			}
//		} catch (Exception e) {
//			logger.error("出错", e);
//			throw e;
//		} finally {
//			close(pstmt, conn);
//		}
//		return null;
//	}

//	/**
//	 * 查询商品拓展属性信息
//	 * 
//	 * @author WangYong
//	 * @param commodityId
//	 * @param noPreff
//	 * @return
//	 * @throws Exception
//	 */
//	public PrototypeVo queryPrototype(String commodityId, String noPreff) throws Exception {
//		String sql = "select exprop.prop_value_no as protNo,exprop.prop_value as protValue from tbl_commodity_extend_prop exprop " + "where exprop.prop_value_no like concat('"
//				+ noPreff + "','%')  and  " + "exprop.commodity_id = '" + commodityId + "' and exprop.delete_flag=1";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		try {
//			conn = getConnection();
//			pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			if (rs.next()) {
//				PrototypeVo vo = new PrototypeVo();
//				vo.setProtNo(rs.getString("protNo"));
//				vo.setProtValue(rs.getString("protValue"));
//				return vo;
//			}
//		} catch (Exception e) {
//			logger.error("出错", e);
//			throw e;
//		} finally {
//			close(pstmt, conn);
//		}
//		return null;
//	}



	/**
	 * jdbc查询
	 * 
	 * @param sql
	 *            sql语句
	 * @return
	 * @throws Exception
	 */
	public ResultSet queryList(String sql) {
		Connection conn = null; // 获取数据库连接
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			resultSet = pstmt.executeQuery();
		} catch (Exception e) {
			logger.error("出错", e);
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return resultSet;
	}

	/**
	 * 批量增加与修改
	 * 
	 * @param sql
	 *            insert or update 语句
	 * @return
	 * @throws Exception
	 * @throws SQLException
	 */
	public boolean batchSaveOrUpdate(String sql, List<Object[]> paramList, long timestamp) throws Exception {
		boolean flag = false;
		int count = Integer.parseInt(batch_size);
		Connection conn = getConnection(); // 获取数据库连接
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false); // 设置手动提交事务
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			// 赋值
			for (int i = 0; i < paramList.size(); i++) {

				Object[] values = paramList.get(i);
				for (int j = 0; j < values.length; j++) {
					pstmt.setObject(j + 1, values[j]);
				}
				pstmt.setObject(values.length + 1, timestamp++);
				pstmt.addBatch();

				// 批量数等于 batch_size 时 提交数据
				if (i != 0 && ((i + 1) % count == 0)) {
					int ids[] = pstmt.executeBatch(); // 执行操作
					if (ids.length == count) {
						conn.commit(); // 提交事务
					} else {
						conn.rollback(); // 事务回滚
					}
					pstmt.clearBatch();
				}
			}

			int ids[] = pstmt.executeBatch(); // 执行操作
			if (ids.length == paramList.size() % (count)) {
				conn.commit(); // 提交事务
				flag = true;
			} else {
				conn.rollback(); // 事务回滚
			}
		} catch (SQLException e) {
			logger.error("出错", e);
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return flag;
	}

	/**
	 * 查询系统配置
	 * 
	 * @return
	 * @throws Exception
	 */
	/*public List<SystemConfig> getSystemConfigLsit() throws Exception {
		List<SystemConfig> systemConfigList = new ArrayList<SystemConfig>();
		Connection conn = getConnectionForSys(); // 获取数据库连接
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		try {
			String sql = "select * from tbl_systemmgt_config where delete_flag='1' ";
			pstmt = conn.prepareStatement(sql); // 创建PreparedStatement对象
			resultSet = pstmt.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					SystemConfig systemConfig = new SystemConfig();
					systemConfig.setId(resultSet.getString("id"));
					systemConfig.setKey(resultSet.getString("config_key"));
					systemConfig.setValue(resultSet.getString("config_value"));
					systemConfig.setConfigName(resultSet.getString("config_name"));
					systemConfig.setRemark(resultSet.getString("remark"));
					systemConfig.setDeleteFlag(resultSet.getString("delete_flag"));
					systemConfigList.add(systemConfig);
				}
			}
		} catch (Exception e) {
			logger.error("出错", e);
		} finally {
			close(pstmt, conn); // 关闭相关连接
		}
		return systemConfigList;
	}*/

	/**
	 * 统计查询结果集行数
	 * 
	 * @param sql
	 * @return int
	 */
	public int count(String sql) {

		return count(sql, null);
	}
	

	public int countType(String sql,String type) {

		return countType(sql, null,type);
	}
	/**
	 * 统计查询结果集行数
	 * 
	 * @param sql
	 * @param values
	 * @return int
	 */
	public int count(String sql, Object[] values) {

		int result = 0;

		try {
			result = ((Number) ObjectUtils.defaultIfNull(uniqueResult(sql, values), NumberUtils.INTEGER_ZERO)).intValue();

		} catch (Exception e) {

			logger.error("出错", e);
		}

		return result;
	}
	
	public int countType(String sql, Object[] values,String type) {

		int result = 0;

		try {
			result = ((Number) ObjectUtils.defaultIfNull(uniqueResult(sql, values,type), NumberUtils.INTEGER_ZERO)).intValue();

		} catch (Exception e) {

			logger.error("出错", e);
		}

		return result;
	}

	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @return Object
	 */
	public Object uniqueResult(String sql) {

		return uniqueResult(sql, null);
	}

	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @param values
	 * @return Object
	 */
	public Object uniqueResult(String sql, Object[] values) {

		Object result = null;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);

			if (ArrayUtils.isNotEmpty(values)) {

				for (int j = 0; j < values.length; j++) {

					pstmt.setObject(j + 1, values[j]);
				}
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getObject(1);
			}
		} catch (Exception e) {

			logger.error("出错", e);
			logger.error("出错 sql: " + sql);
			logger.error("出错 sql values: " + Arrays.toString(values));

		} finally {

			close(conn, pstmt, rs);
		}

		return result;
	}

	public Object uniqueResult(String sql, Object[] values,String type) {

		Object result = null;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			if ("ygis".equals(type)) {
				conn = getConnectionForSys();
			} else {
				conn = getConnection();
			}
			pstmt = conn.prepareStatement(sql);

			if (ArrayUtils.isNotEmpty(values)) {

				for (int j = 0; j < values.length; j++) {

					pstmt.setObject(j + 1, values[j]);
				}
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getObject(1);
			}
		} catch (Exception e) {

			logger.error("出错", e);
			logger.error("出错 sql: " + sql);
			logger.error("出错 sql values: " + Arrays.toString(values));

		} finally {

			close(conn, pstmt, rs);
		}

		return result;
	}
	
	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @return Object[]
	 */
	public Object[] uniqueResultArray(String sql) {

		return uniqueResultArray(sql, null);
	}

	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @param values
	 * @return Object[]
	 */
	public Object[] uniqueResultArray(String sql, Object[] values) {

		List<Object[]> list = listResultArray(sql, values);

		switch (list.size()) {

		case 0:

			return null;

		case 1:

			return list.get(0);

		default:

			throw new IllegalArgumentException("Result more than one row : SQL [ " + sql + " ]");
		}
	}

	/**
	 * 查询返回多行结果
	 * 
	 * @param sql
	 * @return List
	 */
	public List<Object[]> listResultArray(String sql) {

		return listResultArray(sql, null);
	}

	/**
	 * 查询返回多行结果
	 * 
	 * @param sql
	 * @param values
	 * @return List
	 */
	public List<Object[]> listResultArray(String sql, Object[] values) {

		List<Object[]> arrays = new ArrayList<Object[]>();

		Object[] array = null;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);

			if (ArrayUtils.isNotEmpty(values)) {

				for (int j = 0; j < values.length; j++) {

					pstmt.setObject(j + 1, values[j]);
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				array = new Object[rs.getMetaData().getColumnCount()];

				for (int i = 0; i < array.length; i++) {

					array[i] = rs.getObject(i + 1);
				}

				arrays.add(array);
			}
		} catch (Exception e) {

			logger.error("出错", e);
			logger.error("出错 sql: " + sql);
			logger.error("出错 sql values: " + Arrays.toString(values));

		} finally {

			close(conn, pstmt, rs);
		}

		return arrays;
	}

	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @return Map
	 */
	public Map<String, Object> uniqueResultMap(String sql) {

		return uniqueResultMap(sql, null);
	}

	/**
	 * 查询返回单一结果
	 * 
	 * @param sql
	 * @param values
	 * @return Map
	 */
	public Map<String, Object> uniqueResultMap(String sql, Object[] values) {

		List<Map<String, Object>> list = listResultMap(sql, values);

		switch (list.size()) {

		case 0:

			return null;

		case 1:

			return list.get(0);

		default:

			throw new IllegalArgumentException("Result more than one row : SQL [ " + sql + " ]");
		}
	}

	/**
	 * 查询返回多行结果
	 * 
	 * @param sql
	 * @return List
	 */
	public List<Map<String, Object>> listResultMap(String sql) {

		return listResultMap(sql, null);
	}

	/**
	 * 查询返回多行结果
	 * 
	 * @param sql
	 * @param values
	 * @return List
	 */
	public List<Map<String, Object>> listResultMap(String sql, Object[] values) {

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = null;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement(sql);

			if (ArrayUtils.isNotEmpty(values)) {

				for (int j = 0; j < values.length; j++) {

					pstmt.setObject(j + 1, values[j]);
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				map = new HashMap<String, Object>();

				ResultSetMetaData rsmd = rs.getMetaData();

				for (int i = 0; i < rsmd.getColumnCount();) {

					++i;

					String key = rsmd.getColumnLabel(i).toLowerCase();

					if (map.containsKey(key)) {

						throw new IllegalArgumentException("List entry [ " + maps.size() + " ] already exists key [ " + key + " ] ");
					}

					map.put(key, rs.getObject(i));
				}

				maps.add(map);
			}
		} catch (Exception e) {

			logger.error("出错", e);
			logger.error("出错 sql: " + sql);
			logger.error("出错 sql values: " + Arrays.toString(values));

		} finally {

			close(conn, pstmt, rs);
		}

		return maps;
	}

	/**
	 * 批理执行更新
	 * 
	 * @param sqlBatchs
	 * @return boolean
	 */
	public boolean executeBatch(Map<String, List<Object[]>> sqlBatchs) {
		boolean result = false;
		if (MapUtils.isEmpty(sqlBatchs)) {
			return result;
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			int rowCount = 0;
			int batchCount = Integer.parseInt(batch_size);
			for (Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {
				pstmt = conn.prepareStatement(entry.getKey());
				rowCount = 0;
				for (Object[] objects : entry.getValue()) {
					for (int i = 0; i < objects.length; i++) {
						pstmt.setObject(i + 1, objects[i]);
					}
					pstmt.addBatch();
					if (++rowCount % batchCount == 0) {
						pstmt.executeBatch();
					}
				}
				if (rowCount % batchCount != 0) {
					pstmt.executeBatch();
				}
				pstmt.close();
			}
			conn.commit();
			result = true;
		} catch (Exception e) {
			logger.error("出错", e);
			logger.error("出错 sql batchs: " + serializeSQLBatchs(sqlBatchs));
			try {
				conn.rollback();
			} catch (SQLException ex) {
				logger.error("出错", e);
			}
		} finally {
			logger.info(serializeSQLBatchs(sqlBatchs));
			close(conn, pstmt);
		}
		return result;
	}

	/**
	 * 批理执行更新(商品导入）
	 *
	 * @param sqlBatchs
	 * @return int
	 */
	public int executeCommodityBatch(Map<String, List<Object[]>> sqlBatchs) throws Exception {

		int rowCount = 0;
		int errorIndex = 0;
		if (MapUtils.isEmpty(sqlBatchs)) {

			return rowCount;
		}

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			conn.setAutoCommit(false);

			int batchCount = Integer.parseInt(batch_size);

			for (Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {

				System.out.println("key" + entry.getKey());

				pstmt = conn.prepareStatement(entry.getKey());

				rowCount = 0;

				for (Object[] objects : entry.getValue()) {

					for (int i = 0; i < objects.length; i++) {
						pstmt.setObject(i + 1, objects[i]);
					}

					pstmt.addBatch();

					if (++rowCount % batchCount == 0) {

						pstmt.executeBatch();
					}
				}

				if (rowCount % batchCount != 0) {

					pstmt.executeBatch();
				}

				pstmt.close();
			}

			conn.commit();

		} catch (Exception e) {

			logger.error("出错", e);
			errorIndex = 1;
			try {
				conn.rollback();

			} catch (SQLException ex) {
				logger.error("出错", e);
				errorIndex = 1;

			}
		} finally {

			logger.info(serializeSQLBatchs(sqlBatchs));

			close(conn, pstmt);
		}
		logger.info("商品导入判断数据库是否异常(默认为0):" + errorIndex);

		if (errorIndex == 1) {
			throw new Exception("database exception");
		}
		return rowCount;
	}

	/**
	 * 批理执行更新
	 *
	 * @return boolean
	 */
	public boolean executeBatch(SQLBatch sqlBatch) {

		return executeBatch(Arrays.asList(sqlBatch));
	}

	/**
	 * 批理执行更新
	 *
	 * @param sqlBatchs
	 * @return boolean
	 */
	public boolean executeBatch(List<SQLBatch> sqlBatchs) {

		boolean result = false;

		if (CollectionUtils.isEmpty(sqlBatchs)) {

			return result;
		}

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			conn.setAutoCommit(false);

			int rowCount = 0;

			int batchCount = Integer.parseInt(batch_size);

			Collections.sort(sqlBatchs);

			for (SQLBatch sqlBatch : sqlBatchs) {

				pstmt = conn.prepareStatement(sqlBatch.getSql());

				rowCount = 0;

				for (Object[] objects : sqlBatch.getSqlParams()) {

					for (int i = 0; i < objects.length; i++) {

						pstmt.setObject(i + 1, objects[i]);
					}

					pstmt.addBatch();

					if (++rowCount % batchCount == 0) {

						pstmt.executeBatch();
					}
				}

				if (rowCount % batchCount != 0) {

					pstmt.executeBatch();
				}

				pstmt.close();
			}

			conn.commit();

			result = true;

		} catch (Exception e) {

			logger.error("出错", e);
			logger.error("出错 sql batchs: " + serializeSQLBatchs(sqlBatchs));

			try {
				conn.rollback();

			} catch (SQLException ex) {

				logger.error("出错", e);
			}
		} finally {

			logger.info(serializeSQLBatchs(sqlBatchs));

			close(conn, pstmt);
		}

		return result;
	}

	/**
	 * 商品价格段批量更新
	 *
	 * @author JASON
	 * @param sqlBatchs
	 * @return
	 */
	public boolean executeBatchOfCommodity(List<SQLBatch> sqlBatchs) {

		boolean result = false;

		if (sqlBatchs == null || sqlBatchs.isEmpty()) {

			return result;
		}

		Connection conn = null;

		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			conn.setAutoCommit(false);

			Collections.sort(sqlBatchs);

			for (SQLBatch sqlBatch : sqlBatchs) {
				pstmt = conn.prepareStatement(sqlBatch.getSql());
				pstmt.addBatch();
				pstmt.executeBatch();
			}

			conn.commit();

			result = true;

		} catch (Exception e) {

			logger.error("出错", e);

			try {
				conn.rollback();

			} catch (SQLException ex) {

				logger.error("出错", e);
			}
		} finally {

			logger.info(sqlBatchs);

			try {
				if (pstmt != null) {

					pstmt.close();

					pstmt = null;
				}
			} catch (SQLException e) {

				logger.error("出错", e);
			}

			try {
				if (conn != null) {

					if (!conn.isClosed()) {

						conn.close();
					}

					conn = null;
				}
			} catch (SQLException e) {

				logger.error("出错", e);
			}
		}

		return result;
	}

	/**
	 * 释放数据库连接
	 *
	 * @param conn
	 * @param pstmt
	 */
	private void close(Connection conn, Statement pstmt) {

		close(conn, pstmt, null);
	}

	/**
	 * 释放数据库连接
	 *
	 * @param conn
	 * @param pstmt
	 * @param rs
	 */
	private void close(Connection conn, Statement pstmt, ResultSet rs) {

		try {
			if (rs != null) {

				rs.close();

				rs = null;
			}
		} catch (SQLException e) {

			logger.error("出错", e);
		}

		try {
			if (pstmt != null) {

				pstmt.close();

				pstmt = null;
			}
		} catch (SQLException e) {

			logger.error("出错", e);
		}

		try {
			if (conn != null) {

				if (!conn.isClosed()) {

					conn.close();
				}

				conn = null;
			}
		} catch (SQLException e) {

			logger.error("出错", e);
		}
	}

	/**
	 * Map SQLBatch 序列化字符串
	 *
	 * @param sqlBatchs
	 * @return String
	 */
	private static String serializeSQLBatchs(Map<String, List<Object[]>> sqlBatchs) {

		StringBuilder sb = new StringBuilder();

		sb.append("Map SQLBatch { \n");

		for (Entry<String, List<Object[]>> entry : sqlBatchs.entrySet()) {

			sb.append("\t sql = ").append(entry.getKey()).append("\n\t [");

			int index = 0;

			for (Object[] objects : entry.getValue()) {

				sb.append("\n\t\t sqlParams[").append(index++).append("] = ").append(Arrays.toString(objects));
			}

			sb.append("\n\t ] \n");
		}

		return sb.append("}").toString();
	}

	/**
	 * List SQLBatch 序列化字符串
	 * 
	 * @param sqlBatchs
	 * @return String
	 */
	private static String serializeSQLBatchs(List<SQLBatch> sqlBatchs) {

		StringBuilder sb = new StringBuilder();

		sb.append("List SQLBatch { \n");

		for (SQLBatch sqlBatch : sqlBatchs) {

			sb.append("\t priority = ").append(sqlBatch.getPriority());

			sb.append("\n\t sql = ").append(sqlBatch.getSql());

			sb.append("\n\t [");

			int index = 0;

			for (Object[] objects : sqlBatch.getSqlParams()) {

				sb.append("\n\t\t sqlParams[").append(index++).append("] = ").append(Arrays.toString(objects));
			}

			sb.append("\n\t ] \n");
		}

		return sb.append("}").toString();
	}

	/**
	 * SQL语句对象
	 * 
	 * @author Administrator
	 * 
	 */
	public static final class SQLBatch implements Comparable<SQLBatch> {

		private Integer priority;// 优先级
		private String sql;// SQL语句
		private List<Object[]> sqlParams;// SQL语句参数

		public SQLBatch(String sql, List<Object[]> sqlParams) {
			this(0, sql, sqlParams);
		}

		public SQLBatch(Integer priority, String sql, List<Object[]> sqlParams) {
			this.priority = priority;
			this.sql = sql;
			this.sqlParams = sqlParams;
		}

		public Integer getPriority() {
			return priority;
		}

		public String getSql() {
			return sql;
		}

		public List<Object[]> getSqlParams() {
			return sqlParams;
		}

		@Override
		public int compareTo(SQLBatch o) {
			return this.priority.compareTo(o.priority);
		}

		@Override
		public String toString() {

			StringBuilder sb = new StringBuilder();

			sb.append("Class SQLBatch { \n\t priority = ").append(priority).append("\n\t sql = ").append(sql);

			sb.append("\n\t [");

			for (int i = sqlParams.size() - 1; i >= 0; i--) {

				sb.append("\n\t\t sqlParams[").append(i).append("] = ").append(Arrays.toString(sqlParams.get(i)));
			}

			return sb.append("\n\t ] \n} ").toString();
		}
	}

	/**
	 *查询运动鞋对应的分类:女鞋,男鞋,中性
	 * 
	 * @param sql
	 * @return String
	 * @throws Exception
	 *             lishikun 2011-09-08
	 */
	public String querySexType(String sql) throws Exception {
		String sexType = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			// //获取数据库连接
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			while (result.next()) {
				sexType = result.getString("prop_value");
			}
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			close(pstmt, conn);
		}
		return sexType;
	}

	/**
	 * 获取订单号
	 * 
	 * @param prefix
	 * @return
	 * @throws Exception
	 */
	public String getOrderNo(String prefix) throws Exception {
		String orderNo = "";
		JDBCUtils jdbcUtils = JDBCUtils.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = jdbcUtils.getConnectionForMaster();
			pstmt = conn.prepareStatement("select func_create_order_no(?)");
			pstmt.setString(1, prefix);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				orderNo = rs.getString(1);
			}
		} catch (Exception e) {
			throw new Exception("生成订单号出错！", e);
		} finally {
			jdbcUtils.close(rs, pstmt, conn);
		}
		return orderNo;
	}

	/**
	 * 获取大查询数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getQueryConnection() throws Exception {
		try {
			ServiceLocator serviceLocator = ServiceLocator.getInstance();
			DataSource dataSource = (DataSource) serviceLocator.getBeanFactory().getBean("dataSource_query");
			Connection conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			logger.error("获取查询数据库连接失败！", e);
			throw new Exception();
		}
	}

	/**
	 * 查询返回多行结果 获取大查询数据库连接
	 * 
	 * @param sql
	 * @param values
	 * @return List
	 */
	public Map<String, Object> listResultArrayForQueryConnection(String sql, Object[] values) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object[]> arrays = new ArrayList<Object[]>();
		Object[] array = null;
		List<String> column = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getQueryConnection();
			pstmt = conn.prepareStatement(sql);
			if (ArrayUtils.isNotEmpty(values)) {
				for (int j = 0; j < values.length; j++) {
					pstmt.setObject(j + 1, values[j]);
				}
			}
			rs = pstmt.executeQuery();
			// 记录列名
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			for (int i = 0; i < count; i++) {
				column.add(rsmd.getColumnName(i + 1));
			}
			// 填充查询到的数据
			while (rs.next()) {
				array = new Object[count];
				for (int i = 0; i < array.length; i++) {
					array[i] = rs.getObject(i + 1);
				}
				arrays.add(array);
			}
		} catch (Exception e) {
			// logger.error("出错",e);
		} finally {
			close(conn, pstmt, rs);
		}
		map.put("column", column);
		map.put("data", arrays);
		return map;
	}

}
