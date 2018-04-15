package com.example.hibernate.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.example.hibernate.dao.ITestDao;
import com.example.hibernate.dao.base.HibernateDaoImpl;
import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;

@Repository
public class TestDaoImpl extends HibernateDaoImpl<User,String> implements ITestDao {

	@Override
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		try {
			deleteByKey(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int insertUser(User record) {
		// TODO Auto-generated method stub
		try {
			save(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public User findUser(String id) {
		// TODO Auto-generated method stub
		User user = null;
		try {
			user= super.get(id);
		} catch (Exception e) {
			//logger.error("", e);
		}
        return user;
	}

	@Override
	public int updateUser(User record) {
		// TODO Auto-generated method stub
		update(record);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int countUser(UserVo record) {
		// TODO Auto-generated method stub
		Object count=0;  
		 String hql="select count(*) from User where 1=1 "; 
		 if(record != null){
			 if(StringUtils.isNotBlank(record.getName())){
				 hql = hql + "and name like ?";
			 }
			 if(record.getStartTime() != null){
				 hql = hql + "and createTime > ?";
			 }
			 if(record.getEndTime() != null){
				 hql = hql + "and createTime < ?";
			 }
		 }
	     try {  
	    	 Query<Integer> query=getCurrentSession().createQuery(hql);  
	    	 if(record != null){
				 if(StringUtils.isNotBlank(record.getName())){
					 query.setParameter(0, "%"+record.getName()+"%");
				 }
				 if(record.getStartTime() != null){
					 query.setParameter(1, record.getStartTime());
				 }
				 if(record.getEndTime() != null){
					 query.setParameter(2, record.getEndTime());
				 }
			 }
	         count=(Object) query.uniqueResult();  
	     } catch (HibernateException e) {
		     //logger.error("", e);
	     }/*finally{  
		     super.sessionClose();
	     } */ 
	     return Integer.parseInt(String.valueOf(count));  
	}

	@Override
	public List<User> listUser(User record) {
		// TODO Auto-generated method stub
		try {
			return findEqualByEntity(record, new String[]{});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
