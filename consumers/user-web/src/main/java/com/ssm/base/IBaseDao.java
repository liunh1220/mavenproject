package com.ssm.base;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

import com.ssm.util.Page;

public interface IBaseDao<T> {
	
	/**
	 *  影响行数
	 */
	public final static String AFFECT_LINE = "AFFECT_LINE";
	
	/**
	 *  错误列表
	 */
	public final static String EXCEPTION_LIST = "EXCEPTION_LIST";
	
	void save(String classMethod, T entity) throws DataAccessException;
	
	int update(String classMethod, T entity) throws DataAccessException;
	
	T get(String classMethod, String id) throws DataAccessException;
	
	Object getObject(String classMethod, Object param) throws DataAccessException;
	
	List getObjectList(String classMethod, Object param) throws DataAccessException;
	
	List<T> getAll(String classMethod) throws DataAccessException;
	
	List<T> getList(String classMethod, Object entity) throws DataAccessException;
	
	void remove(String classMethod, T entity) throws DataAccessException;
	
	SqlSessionTemplate getSqlSessionTemplate();
	
	void getByPage(String classMethod, Page page);
	
	/**
	 * 批量插入
	 * @param classMethod
	 * @param entityList
	 * @param batchNum 批量数量
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	Map<String,Object> batchInsert(String classMethod, List<T> entityList,long batchNum,boolean transacted) throws RuntimeException;
	
	/**
	 * 批量插入
	 * @param classMethod
	 * @param entityList
	 * @param batchNum 批量数量
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	Map<String,Object> batchInsert(String classMethod, List<T> entityList,boolean transacted) throws RuntimeException;
	
	/**
	 * 批量更新
	 * @param classMethod
	 * @param entityList
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	Map<String,Object> batchUpdate(String classMethod, List<T> entityList,long batchNum,boolean transacted) throws RuntimeException;
	
	/**
	 * 批量更新
	 * @param classMethod
	 * @param entityList
	 * @return 受影响的行数
	 */
	Map<String,Object> batchUpdate(String classMethod, List<T> entityList,boolean transacted) throws RuntimeException;
}
