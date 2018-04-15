package com.example.ehcache.base;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.example.ehcache.base.basedao.CritMap;
import com.example.ehcache.base.basedao.CriteriaAdapter;
import com.example.ehcache.base.basedao.HibernateHelper;
import com.example.ehcache.base.basedao.PageFinder;

/**
 * 
 *@author yhb
 * 
 * @version 创建时间：2011-3-25 下午02:40:10
 */
public interface IHibernateEntityDao<T> {
	
	/**
	 * 保存对象.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	Serializable save(T o) throws Exception;
	
	T saveObject(T o) throws Exception ;
	
	/**
	 * 删除对象.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	Serializable remove(T o) throws Exception;

	/**
	 * 根据ID移除对象.
	 * @throws Exception 
	 */
	Serializable removeById(Serializable id) throws Exception;
	
	T merge(T o);
	
	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
	 * 
	 * 不带级联查询
	 */
	T getById(Serializable id);
	
	/**
	 * 获取全部对象
	 */
	List<T> getAll();
	
	/**
	 * 根据属性名和属性值查询单个对象
	 * @param useCache  是否使用缓存  true,false
	 * @return 符合条件的唯数据 or null
	 */
	T findUniqueBy(final String propertyName,final Object value,final boolean ... useCache) ;
	
	/**
	 * 获取全部对象,带排序字段与升降序参数
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	List<T> getAll(String orderBy, boolean isAsc);
	
	/**
	 * 根据属性名和属性值查询对象
	 * @param useCache  是否使用缓存  true,false
	 * @return 符合条件的对象集合
	 * @see HibernateGenericDao#findBy(Class,String,Object)
	 */
	List<T> findBy(final String propertyName,final Object value,final boolean ... useCache);
	
	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数,不推荐使用
	 * 
	 * @param values
	 *            可变参数,见{@link #createQuery(String,Object...)}
	 */
	List<T> find(String hql, Object... values) ;
	
	/**
	 * 根据外置命名查询
	 * 
	 * @param queryName
	 * @param values
	 *            参数值列
	 * @return
	 */
	List<T> findByNameQuery(String queryName, Object... values) ;
	
	/**
	 * 根据 critMap 条件获取对象
	 * @param critMap  查询条件集合
	 * @param useCache  是否使用缓存  true false
	 * @return 
	 */
	T getObjectByCritMap(final CritMap critMap ,final boolean ... useCache);
	
	/**
	 * 不带属性查询对象,带排序参数和数据条数
	 * @param useCache  是否使用缓存  true,false
	 * @return 符合条件的对象集合
	 */
	List<T> findBy(final String orderBy,final boolean isAsc,final int limit,final boolean ... useCache);
	
	/**
	 * 根据critMap 条件集合查询
	 * @param critMap
	 * @param useCache  是否使用缓存  true false
	 * @return
	 */
	List<T> findByCritMap(final CritMap critMap,final boolean ... useCache);
	
	/**
	 * 分页查询
	 * 
	 */
	PageFinder<T> pagedByCritMap(final CritMap critMap,final int pageNo,final int pageSize);
	
	/**
	 * 按HQL方式进行分页查询
	 * 
	 * @param toPage
	 *            跳转页号
	 * @param pageSize
	 *            每页数量
	 * @param hql
	 *            查询语句
	 * @param values
	 *            参数
	 * @return
	 */
	PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Object... values) ;
	
	/**
	 * 按hibernate标准查询器进行分页查询
	 * @param pagination
	 * @return
	 */
	PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize);
	
	/**
	 * 根据criteria查询对象
	 * @param criteria
	 * @return
	 */
	T queryObjectByCriteria(Criteria criteria);
	
	/**
	 * 创建Criteria代理。解决Criteria  相关的数据库连接不能关闭问题
	 * @param criterions
	 * @return
	 */
	CriteriaAdapter createCriteriaAdapter(Criterion... criterions);
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	Query createQuery(String hql, Object... values);
	
	/**
	 * 根据Criteria查询获取总记录数
	 * @param criteria
	 * @return
	 */
	int getRowCount(Criteria criteria);
	
	void evict(Object entity);
	
	void evict(String collection, Serializable id) ;
	
	void flush() ;

	void clear() ;
	
	Session getHibernateSession() ;
	
	/**
	 * 释放session
	 * @param session
	 */
	void releaseHibernateSession(Session session);
	
	HibernateHelper getHibernateHelper();
	
}
