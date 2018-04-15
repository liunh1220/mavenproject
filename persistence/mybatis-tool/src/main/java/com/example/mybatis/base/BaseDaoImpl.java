package com.example.mybatis.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {

	 @Resource
	 private SqlSessionTemplate sqlSession;
	 
	 public <T> int delete(String _mybitsId, T obj) {
	  return sqlSession.delete(_mybitsId, obj);
	 }

	 @Override
	 public <T> int insert(String _mybitsId, T obj) {
	  return sqlSession.insert(_mybitsId, obj);
	 }
	 
	 @Override
	 public <T> int update(String _mybitsId, T obj) {
	  return sqlSession.update(_mybitsId, obj);
	 }

	 @Override
	 public <T> List<T> queryList(String _mybitsId, Map<String, Object> _params) {
	  return sqlSession.selectList(_mybitsId, _params);
	 }

	@Override
	public <T> List<T> queryList(String _mybitsId, List<Object> _params) {
		return sqlSession.selectList(_mybitsId, _params);
	}
		
	 @Override
	 public <T> List<T> queryList(String _mybitsId, Object _params) {
	  return sqlSession.selectList(_mybitsId, _params);
	 }
	 
	 @Override
	 public Object queryOne(String _mybitsId, Object object) {
	  return sqlSession.selectOne(_mybitsId, object);
	 }

	@Override
	public Integer count(String queryString, Object object) {
		return sqlSession.selectOne(queryString, object);
	}
	 
}
