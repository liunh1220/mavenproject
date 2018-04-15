package com.example.ehcache.base.basedao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.example.ehcache.base.IHibernateEntityDao;
import com.example.ehcache.base.basedao.CritMap.MatchType;
import com.example.ehcache.util.ReflectionUtils;


/**
 * 负责为单个Entity对象提供CRUD操作的Hibernate DAO基类.
 * <p/>
 * 子类只要在类定义时指定所管理Entity的Class, 即拥有对单个Entity对象的CRUD操作.
 * 
 * @see HibernateDaoSupport
 * @see GenericsUtils
 */
public abstract class HibernateEntityDaoImpl<T> extends HibernateDaoSupport  implements IHibernateEntityDao<T>  {

	/**
	 * DAO实体代理的Entity类型.
	 */
	protected Class<T> entityClass;

	/**
	 * 在构造函数中将泛型T.class赋给entityClass.
	 */
	@SuppressWarnings("unchecked")
	public HibernateEntityDaoImpl() {
		entityClass = ReflectionUtils.getSuperClassGenricType(getClass(),0);
	}

	/**
	 * 取得entityClass.JDK1.4不支持泛型的子类可以抛开Class<T> entityClass,重载此函数达到相同效果�?
	 */
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 注入SessionFactory
	 */
	@Resource(name="sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory){
	    super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 保存对象.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public Serializable save(T o) throws Exception {
		getHibernateTemplate().saveOrUpdate(o);
		return getId(o); //返回对象主键值
	}
	
	public T saveObject(T o) throws Exception {
		getHibernateTemplate().saveOrUpdate(o);
		return o;
	}
	
	/**
	 * 删除对象.
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 */
	public Serializable remove(T o) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		getHibernateTemplate().delete(o);
		return getId(o);
	}

	/**
	 * 根据ID移除对象.
	 * @throws Exception 
	 */
	public Serializable removeById(Serializable id) throws Exception {
		return remove(getById(id));
	}
	
	public T merge(T o){
		try {
			return getHibernateTemplate().merge(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据ID获取对象. 实际调用Hibernate的session.load()方法返回实体或其proxy对象. 如果对象不存在，抛出异常.
	 * 
	 * 不带级联查询
	 */
	public T getById(Serializable id) {
		T  obj = (T) getHibernateTemplate().get(entityClass, id);
		return obj;
	}
	
	/**
	 * 根据 critMap 条件获取对象
	 * @param critMap  查询条件集合
	 * @return 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public T getObjectByCritMap(final CritMap critMap,final boolean ... useCache){
		return (T)super.getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) {
				Criteria criteria = buildPropertyFilterCriteria(session.createCriteria(entityClass),critMap);
				if(useCache != null && useCache.length > 0 && useCache[0]){
					criteria.setCacheable(true);
				}
				return (T)criteria.uniqueResult();
			}
		});
	}

	/**
	 * 获取全部对象
	 */
	public List<T> getAll() {
		getHibernateTemplate().setCacheQueries(true);
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 获取全部对象,带排序字段与升降序参数
	 * 
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> getAll(String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return (List<T>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.asc(orderBy)));
		else
			return (List<T>) getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).addOrder(Order.desc(orderBy)));
	}


	/**
	 * 根据属性名和属性值查询对象
	 * 
	 * @return 符合条件的对象集合
	 * @see HibernateGenericDao#findBy(Class,String,Object)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public List<T> findBy(final String propertyName,final Object value,final boolean ... useCache) {
		Assert.hasText(propertyName);
		List<T> list=null;
		try{
			list = (List<T>)super.getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) {
					Criteria criteria = session.createCriteria(entityClass);
					criteria.add(Restrictions.eq(propertyName, value));
					if(useCache != null && useCache.length > 0 && useCache[0]){
						criteria.setCacheable(true);
					}
					return	criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
				}
			});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据critMap 条件集合查询
	 * @param critMap
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	public List<T> findByCritMap(final CritMap critMap,final boolean ... useCache){
		Assert.notNull(critMap);
		List<T>  list = (List<T> )super.getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("static-access")
			public Object doInHibernate(Session session) {
				Criteria criteria = session.createCriteria(entityClass);
				buildPropertyFilterCriteria(criteria,critMap);
				criteria.setResultTransformer(criteria.DISTINCT_ROOT_ENTITY);
				if(useCache != null && useCache.length > 0 && useCache[0]){
					criteria.setCacheable(true);
				}
				return criteria.list();
			}
		});
		return list;
	}

	public CriteriaAdapter createCriteriaAdapter(Criterion... criterions) {
		CriteriaAdapter criteria = new CriteriaAdapter(currentSession(), entityClass, criterions);
		return criteria;
	}

	/**
	 * 不带属性查询对象,带排序参数和数据条数
	 * 
	 * @return 符合条件的对象集合
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public List<T> findBy(final String orderBy,final boolean isAsc,final int limit,final boolean ... useCache) {
		Assert.hasText(orderBy);
		List<T>  list = null;
		list = (List<T> )super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Criteria criteria = session.createCriteria(entityClass);
				if(useCache != null && useCache.length > 0 && useCache[0]){
					criteria.setCacheable(true);
				}
				if (isAsc)
					criteria.addOrder(Order.asc(orderBy));
				else
					criteria.addOrder(Order.desc(orderBy));
				return criteria.setMaxResults(limit).setCacheable(true).list();
			}
		});
		return list;
	}

	/**
	 * 根据属性名和属性值查询单个对象
	 * 
	 * @return 符合条件的唯数据 or null
	 */
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public T findUniqueBy(final String propertyName,final Object value,final boolean ... useCache) {
		Assert.hasText(propertyName);
		T t = (T)super.getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				Criteria criteria = session.createCriteria(entityClass);
				criteria.add(Restrictions.eq(propertyName, value));
				if(useCache != null && useCache.length > 0 && useCache[0]){
					criteria.setCacheable(true);
				}
				return	criteria.uniqueResult();
			}
		});
		return t;
	}

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数,不推荐使用
	 * 
	 * @param values
	 *            可变参数,见{@link #createQuery(String,Object...)}
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> find(String hql, Object... values) {
		Assert.hasText(hql);
		return (List<T>) getHibernateTemplate().find(hql, values);
	}

	/**
	 * 根据外置命名查询
	 * 
	 * @param queryName
	 * @param values
	 *            参数值列
	 * @return
	 */
	public List<T> findByNameQuery(String queryName, Object... values) {
		return findByNameQuery(true, queryName, values);
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	private List<T> findByNameQuery( boolean isCache,String queryName, Object... values) {
		Assert.hasText(queryName);
		getHibernateTemplate().setCacheQueries(isCache);
		return (List<T>) getHibernateTemplate().findByNamedQuery(queryName, values);
	}

	/**
	 * 创建Query对象.
	 * 对于max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下:
	 * 
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 * 
	 * @param values
	 *            可变参数.
	 */
	@SuppressWarnings({ "deprecation", "rawtypes" })
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = (Query) currentSession().createQuery(hql);
		if (null != values && values.length > 0) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	@SuppressWarnings("unchecked")
	public T queryObjectByCriteria(Criteria criteria) {
		return (T)criteria.uniqueResult();
	}

	/**
	 * 按hibernate标准查询器进行分页查询
	 * @param pagination
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageFinder<T> pagedByCriteria(Criteria criteria, int pageNo, int pageSize) {
		Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		criteria.setProjection(null);
		if (totalRows.intValue() < 1) {
			return new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
		} else {
			PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
			List<T> list = criteria.setFirstResult(finder.getStartOfPage()).setMaxResults(
					finder.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			finder.setData(list);
			return finder;
		}
	}
	
	/**
	 * 分页查询
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageFinder<T> pagedByCritMap(final CritMap critMap,final int pageNo,final int pageSize) {
		PageFinder<T> pageFinder;
		pageFinder = (PageFinder<T>)super.getHibernateTemplate().execute(new HibernateCallback() {
			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session) {
				Criteria criteria = session.createCriteria(entityClass);
				buildPropertyFilterCriteria(criteria,critMap);
				Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
				criteria.setProjection(null);
				if (totalRows.intValue() < 1) {
					return new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
				} else {
					PageFinder<T> finder = new PageFinder<T>(pageNo, pageSize, totalRows.intValue());
					List<T> list = criteria.setFirstResult(finder.getStartOfPage()).setMaxResults(
							finder.getPageSize()).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
					finder.setData(list);
					return finder;
				}
			}
		});
		return pageFinder;
	}
	

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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PageFinder<T> pagedByHQL(String hql, int toPage, int pageSize, Object... values) {
		String countQueryString = " select count (*) " + removeSelect(removeOrders(hql));
		List<T> countlist = (List<T>) getHibernateTemplate().find(countQueryString, values);
		if(countlist.size()==0){
			return null;
		}
		Long totalCount = (Long) countlist.get(0);
		if (totalCount.intValue() < 1) {
			return new PageFinder<T>(toPage, pageSize, totalCount.intValue());
		} else {
			PageFinder<T> finder = new PageFinder<T>(toPage, pageSize, totalCount.intValue());
			Query query = createQuery(hql, values);
			List<T> list = query.setFirstResult(finder.getStartOfPage()).setMaxResults(finder.getPageSize())
					.list();
			finder.setData(list);
			return finder;
		}

	}

	/**
	 * 取得对象的主键值,辅助函数.
	 */
	@SuppressWarnings("deprecation")
	private Serializable getId(Object entity) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		return (Serializable) PropertyUtils.getProperty(entity, getIdName());
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	@SuppressWarnings("deprecation")
	private String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		Assert.notNull(meta, "Class " + entityClass + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, entityClass.getSimpleName() + " has no identifier property define.");
		return idName;
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况用于pagedQuery.
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private  String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	private Criteria buildPropertyFilterCriteria(Criteria criteria,CritMap critMap) {
		try {
			Map<String, Object> propertyMap = null;
			//带别名级联
			propertyMap = critMap.getFieldMap(MatchType.ALIASFECH.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					//key 为别名 value 为属性名
					String value = propertyMap.get(key).toString();
					criteria.createAlias(value, key);
					criteria.setFetchMode(key, FetchMode.JOIN);
				}
			}
			//级联
			propertyMap = critMap.getFieldMap(MatchType.FECH.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					//key 为别名 value 为属性名
					String value = propertyMap.get(key).toString();
					criteria.setFetchMode(value, FetchMode.JOIN);
				}
			}
			//判断是NULL
			propertyMap = critMap.getFieldMap(MatchType.IS_NULL.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.isNull(value.toString()));
				}
			}
			//判断是not NULL
			propertyMap = critMap.getFieldMap(MatchType.IS_NONULL.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.isNotNull(value.toString()));
				}
			}
			//相等
			propertyMap = critMap.getFieldMap(MatchType.EQUAL.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.eq(key, value));
				}
			}
			//左模糊查询
			propertyMap = critMap.getFieldMap(MatchType.L_LIKE.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.like(key,value+"%"));
				}
			}
			//右模糊查询
			propertyMap = critMap.getFieldMap(MatchType.R_LIKE.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.like(key,"%"+value));
				}
			}
			//模糊查询
			propertyMap = critMap.getFieldMap(MatchType.LIKE.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.like(key,"%"+value+"%"));
				}
			}
			//大于
			propertyMap = critMap.getFieldMap(MatchType.GREATER.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.gt(key,value));
				}
			}
			//大于等于
			propertyMap = critMap.getFieldMap(MatchType.GREATER_EQUAL.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.ge(key,value));
				}
			}
			//小于
			propertyMap = critMap.getFieldMap(MatchType.LESS.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.lt(key,value));
				}
			}
			//小于等于
			propertyMap = critMap.getFieldMap(MatchType.LESS_EQUAL.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object value = propertyMap.get(key);
					criteria.add(Restrictions.le(key,value));
				}
			}
			//IN
			//增加IN类型查询，用于同一属性多值查询　by dsy 20110421_1639
			propertyMap = critMap.getFieldMap(MatchType.IN.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					Object[] values = (Object[]) propertyMap.get(key);
					criteria.add(Restrictions.in(key, values));
				}
			}
			//升序
			propertyMap = critMap.getFieldMap(MatchType.ORDER_ASC.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					criteria.addOrder(Order.asc(key));
				}
			}
			//降序
			propertyMap = critMap.getFieldMap(MatchType.ORDER_DESC.name());
			if(propertyMap != null){
				for (String key : propertyMap.keySet()) {
					criteria.addOrder(Order.desc(key));
				}
			}
			//获取指定数据数
			int maxsize = critMap.getMaxSize();
			if(maxsize > 0){
				criteria.setMaxResults(maxsize);
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
		return criteria;
	}
	
	/***
	 * 获得总记录条数
	 */
	public int getRowCount(Criteria criteria){
		Integer totalRows = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		return totalRows;
	}
	
	public void evict(Object entity) {
		getHibernateTemplate().evict(entity);
	}
	
	@Override
	public void evict(String collection, Serializable id) {
		// TODO Auto-generated method stub
		
	}
	
	public void flush() {
		getHibernateTemplate().flush();
	}

	public void clear() {
		getHibernateTemplate().clear();
	}

	public Session getHibernateSession() {
		return currentSession();
	}
	
	public void releaseHibernateSession(Session session){
		if(session.isOpen()){
			session.close();
		}
	}
	
	public HibernateHelper getHibernateHelper(){
		HibernateHelper hibernateHelper = new HibernateHelper(getSessionFactory());
		return hibernateHelper;
	}
	
}
