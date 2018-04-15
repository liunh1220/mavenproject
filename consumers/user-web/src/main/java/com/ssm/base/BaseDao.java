package com.ssm.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import com.ssm.util.DateUtil;
import com.ssm.util.KeyGenerator;
import com.ssm.util.Page;


@Component
public abstract class BaseDao<T extends BaseDomain> extends SqlSessionDaoSupport implements IBaseDao<T>{
	
	
	private static final Logger loggerbase = LoggerFactory.getLogger(BaseDao.class);

	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	
	private SqlSessionTemplate sessionTemplate = null;
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		if (sessionTemplate == null) {
			 sessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
		}
		return sessionTemplate;	
	}
	
	public void save(String classMethod, T entity) throws RuntimeException {
		try {
			entity.setId(KeyGenerator.getUUID());
			
//			if (Context.getCurrentUser() != null) {
//				entity.setCreateBy(Context.getCurrentUser().getId());//创建人
//			}
			
			if(entity.getCreateTime() == null)
			{
				entity.setCreateTime(DateUtil.getNowTimestamp());//创建时间
			}
			
			this.getSqlSessionTemplate().insert(classMethod, entity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(String classMethod, T entity) throws RuntimeException {
		try {
			this.getSqlSessionTemplate().delete(classMethod, entity);
		} catch (Exception e) {
			loggerbase.error("BaseDao.remove(String classMethod, T entity)",e);
			throw new RuntimeException(e);
		}
	}

	public int update(String classMethod, T entity) throws RuntimeException {
		int size = 0;
		try {
//			if(Context.getCurrentUser() != null)
//			{
//				entity.setCreateBy(Context.getCurrentUser().getId());//创建人
//			}
			entity.setUpdateTime(DateUtil.getNowTimestamp());//更新时间
			 size = this.getSqlSessionTemplate().update(classMethod, entity);
		} catch (Exception e) {
			loggerbase.error("BaseDao.update(String classMethod, T entity)",e);
			 throw new RuntimeException(e);
		}
		return size;
	}
	
	/**
	 * 批量插入
	 * @param classMethod
	 * @param entityList
	 * @param batchNum 批量数量
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	public Map<String,Object> batchInsert(String classMethod, List<T> entityList,long batchNum,boolean transacted) throws RuntimeException
	{
		Map<String,Object> map = new HashMap<String,Object>();
		// 受影响的行数
		Long affectLines = 0L;
		
		List<Exception> list = new ArrayList<Exception>();
		try {
			if(entityList != null && entityList.size() >0)
			{
				long index = 0;
				for (T entity : entityList) {
					index ++;
					entity.setId(KeyGenerator.getUUID());
//					if(Context.getCurrentUser() != null)
//					{
//						entity.setCreateBy(Context.getCurrentUser().getId());//创建人
//					}
					entity.setCreateTime(DateUtil.getNowTimestamp());//创建时间
					try
					{
						getBatchSqlSessionTemplate().insert(classMethod, entity);
						affectLines ++;
						if(index % batchNum == 0 || index == entityList.size())
						{
							// 提交
							getBatchSqlSessionTemplate().commit();
						}
					}
					catch(Exception e)
					{
						list.add(e);
						if(transacted)
						{
							// 回滚
							getBatchSqlSessionTemplate().rollback(true);
						}
						else
						{
							// 提交
							getBatchSqlSessionTemplate().commit();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("BaseDao.batchInsert(String classMethod, List<T> entityList,long batchNum)",e);
		}
		map.put(AFFECT_LINE, affectLines);
		map.put(EXCEPTION_LIST, list);
		return map;
	}
	
	/**
	 * 批量插入
	 * @param classMethod
	 * @param entityList
	 * @param batchNum 批量数量
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	public Map<String,Object> batchInsert(String classMethod, List<T> entityList,boolean transacted) throws RuntimeException
	{
		try {
			long batchNum = entityList!=null?entityList.size():0;
			return this.batchInsert(classMethod, entityList, batchNum, transacted);
		} catch (Exception e) {
			throw new RuntimeException("BaseDao.batchInsert(String classMethod, List<T> entityList,long batchNum)",e);
		}
	}
	
	/**
	 * 批量更新
	 * @param classMethod
	 * @param entityList
	 * @param transacted 是否事务
	 * @return 受影响的行数
	 */
	public Map<String,Object> batchUpdate(String classMethod, List<T> entityList,long batchNum,boolean transacted) throws RuntimeException
	{
		Map<String,Object> map = new HashMap<String,Object>();
		// 受影响的行数
		Long affectLines = 0L;
		
		List<Exception> list = new ArrayList<Exception>();
		try {
			if(entityList != null && entityList.size() >0)
			{
				long index = 0;
				for (T entity : entityList) {
					index ++;
//					if(Context.getCurrentUser() != null)
//					{
//						entity.setCreateBy(Context.getCurrentUser().getId());//创建人
//					}
					entity.setUpdateTime(DateUtil.getNowTimestamp());//更新时间
					try
					{
						getBatchSqlSessionTemplate().update(classMethod, entity);
						affectLines ++;
						if(index % batchNum == 0 || index == entityList.size())
						{
							// 提交
							getBatchSqlSessionTemplate().commit();
						}
					}
					catch(Exception e)
					{
						list.add(e);
						if(transacted)
						{
							// 回滚
							getBatchSqlSessionTemplate().rollback(true);
						}
						else
						{
							// 提交
							getBatchSqlSessionTemplate().commit();
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("BaseDao.batchUpdate(String classMethod, List<T> entityList,long batchNum)",e);
		}
		
		map.put(AFFECT_LINE, affectLines);
		map.put(EXCEPTION_LIST, list);
		return map;
	}
	
	/**
	 * 批量更新
	 * @param classMethod
	 * @param entityList
	 * @return 受影响的行数
	 */
	public Map<String,Object> batchUpdate(String classMethod, List<T> entityList,boolean transacted) throws RuntimeException
	{
		try {
			long batchNum = entityList!=null?entityList.size():0;
			return this.batchUpdate(classMethod, entityList, batchNum, transacted);
		} catch (Exception e) {
			throw new RuntimeException("BaseDao.batchUpdate(String classMethod, List<T> entityList,long batchNum)",e);
		}
	}
	
	public T get(String classMethod, String id)  throws RuntimeException {
		T obj = null;
		try {
			obj = (T) this.getSqlSessionTemplate().selectOne(classMethod, id);
		} catch (Exception e) {
			throw new RuntimeException("BaseDao.get(String classMethod, String id)",e);
		}
		return obj;
	}
	
	public Object getObject(String classMethod, Object param) throws RuntimeException {
		Object object = null;
		
		try {
			object = this.getSqlSessionTemplate().selectOne(classMethod, param);
		} catch (Exception e) {
			//throw new RuntimeException("BaseDao.getObject(String classMethod, Object param)",e);
			loggerbase.error("BaseDao.getObject(String classMethod, T entity)",e);
			 throw new RuntimeException(e);
		}
		return object;
	}
	
	public List getObjectList(String classMethod, Object param) throws RuntimeException {
		List result = null;
		try {
			result = getSqlSessionTemplate().selectList(classMethod, param);
		} catch (Exception e) {
			//throw new RuntimeException("BaseDao.getObjectList(String classMethod, Object param)",e);
			loggerbase.error("BaseDao.getObjectList(String classMethod, T entity)",e);
			 throw new RuntimeException(e);
		}
		return result;
	}

	public List<T> getAll(String classMethod) throws DataAccessException {
		List<T> result = null;
		try {
			// TODO
			result = null;//(List<T>) this.getSqlSessionTemplate().selectList(classMethod);
		} catch (Exception e) {
			loggerbase.error("BaseDao.getAll(String classMethod, T entity)",e);
			 throw new RuntimeException(e);
		}
		return result;
	}
	
	public List<T> getList(String classMethod, Object entity) throws RuntimeException {
		List<T> result = null;
		try {
			// TODO 
			result = null;//(List<T>) this.getSqlSessionTemplate().selectList(classMethod, entity);
		} catch (Exception e) {
			//throw new RuntimeException("BaseDao.getList(String classMethod, Object entity)",e);
			loggerbase.error("BaseDao.getList(String classMethod, T entity)",e);
			throw new RuntimeException(e);
		}
		return result;
	}
	
	public void getByPage(String classMethod, Page page) throws RuntimeException
	{
		try {
			// TODO 
			List<T> result = null;
//			List<T> result = (List<T>) this.getSqlSessionTemplate().selectList(
//					classMethod, page.getCondition(),
//					new RowBounds(page.getStart() - 1, page.getLimit()));
			page.setResult(result);
			page.setCount(getCount(classMethod + "Count", page));
		} catch (Exception e) {
			loggerbase.error("BaseDao.getByPage(String classMethod, T entity)",e);
			throw new RuntimeException(e);
		}
	}

	public int getCount(String classMethod, Page page) throws RuntimeException
	{
		Integer count = 0;
		try
		{
			count = (Integer) this.getSqlSessionTemplate().selectOne(classMethod, page.getCondition());
		}
		catch (Exception e) {
			//throw new RuntimeException("BaseDao.getCount(String classMethod, Page page)",e);
			loggerbase.error("BaseDao.getCount(String classMethod, T entity)",e);
			throw new RuntimeException(e);
		}
		return count;
	}
	
	public SqlSessionTemplate getBatchSqlSessionTemplate() throws RuntimeException
	{
		SqlSessionTemplate sessionTemplate = null;
		try
		{
			sessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
		}
		catch (Exception e) {
			throw new RuntimeException("BaseDao.getBatchSqlSessionTemplate()",e);
		}
		return sessionTemplate;
	}
}
