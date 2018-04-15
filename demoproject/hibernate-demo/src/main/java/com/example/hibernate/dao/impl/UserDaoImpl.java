package com.example.hibernate.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.hibernate.base.dao.HibernateCrudDaoImpl;
import com.example.hibernate.dao.IUserDao;
import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;

@Repository
public class UserDaoImpl extends HibernateCrudDaoImpl<User,String> implements IUserDao{

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public void deleteUser(String id) {
		User record = new User();
		record.setId(id);
		try {  
			Transaction tx = super.beginTransaction();  
		    super.delete(record);  
		    tx.commit();  
	    } catch (HibernateException e) {  
		    logger.error("", e);
	    }finally{  
		    super.sessionClose();  
	    }
	}

	@Override
	public void insertUser(User record) {
		try {  
			Transaction tx = super.beginTransaction(); 
		    super.save(record);  
		    tx.commit();  
	    } catch (HibernateException e) {  
		    logger.error("", e);
	    }finally{  
	    	super.sessionClose();
	    }
	}

	@Override
	public User findUser(String id) {
		User user = null;
		try {
			user= super.get(id);
		} catch (Exception e) {
			logger.error("", e);
		}
        return user;
	}

	@Override
	public void updateUser(User record) {
		try {  
		    Transaction tx=super.beginTransaction();  
		    super.update(record);  
	        tx.commit();
	    } catch (HibernateException e) {  
		    logger.error("", e);
	    }finally{  
		    super.sessionClose();
	    }
	}

	@Override
	public int countUser(UserVo record) {
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
		     logger.error("", e);
	     }/*finally{  
		     super.sessionClose();
	     } */ 
	     return Integer.parseInt(String.valueOf(count));  
	}

	@Override
	public List<User> listUser(User record) {
		List<User> userList = new ArrayList<User>();
        String hql="from User where 1=1 ";
        if(record != null){
			 if(StringUtils.isNotBlank(record.getId())){
				 hql = hql + "and id= ? ";
			 }
			 if(StringUtils.isNotBlank(record.getName())){
				 hql = hql + "and name like ? ";
			 }
		 }
        try {
        	 Query<User> query = getCurrentSession().createQuery(hql);
             query.setParameter(0, record.getId());
             query.setParameter(1, "%"+record.getName()+"%");
             userList = query.list();
		} catch (Exception e) {
			logger.error("", e);
		}finally{  
			super.sessionClose();
	    } 
        return userList;
	}

	@Override
	public boolean isExists(String name) {
		try {
			 Query<User> query = getCurrentSession().createQuery("from User u where u.name = :name").setParameter("name", name);
			 return query.list().size()>0?true:false;
		} catch (Exception e) {
			logger.error("", e);
		}finally{
			super.sessionClose();
		}
		return false;
	}

}
