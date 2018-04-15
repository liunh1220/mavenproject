package com.example.ehcache.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.ehcache.base.basedao.CritMap;
import com.example.ehcache.base.basedao.HibernateEntityDaoImpl;
import com.example.ehcache.entity.pojo.User;


@Repository
public class UserDaoImpl extends HibernateEntityDaoImpl<User> implements IUserDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Override
	@Transactional
	public int deleteUser(String id) {
		// TODO Auto-generated method stub
		try {
			//removeById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	@Transactional
	public int insertUser(User record) {
		// TODO Auto-generated method stub
		try {
			//saveObject(record);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	@Transactional
	public User findUser(String id) {
		// TODO Auto-generated method stub
		return findUniqueBy("id", id);
	}

	@Override
	@Transactional
	public int updateUser(User record) {
		// TODO Auto-generated method stub
		//merge(record);
		return 1;
	}

	@Override
	public int countUser(User record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> listUser(User record) {
		// TODO Auto-generated method stub
		CritMap critMap = new CritMap();
		try {
			this.getCritMap(critMap, record);
			//return findByCritMap(critMap, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void getCritMap(CritMap critMap, User req) throws Exception {
		if (null == critMap) {
			critMap = new CritMap();
		}
		if (StringUtils.isNotEmpty(req.getId())) {
			critMap.addEqual("id", req.getId());
		}
		if (StringUtils.isNotEmpty(req.getName())) {
			critMap.addLike("name","%" + req.getName() + "%");
		}
	}

}
