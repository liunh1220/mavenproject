package com.example.hibernate.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hibernate.entity.pojo.User;
import com.example.hibernate.entity.vo.UserVo;
import com.example.hibernate.service.IUserService;
import com.example.hibernate.util.ResultMsg;
import com.alibaba.fastjson.JSONObject;
import com.example.hibernate.dao.IUserDao;

@Service
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserDao userDao;
	
	@Override
	public ResultMsg saveOrUpdatUser(User record) {
		ResultMsg resultMsg = new ResultMsg();
		if(StringUtils.isBlank(record.getId())){
			resultMsg = this.insertUser(record);
		}else{
			resultMsg = this.updateUser(record);
		}
		return resultMsg;
	}
	
	@Override
	public ResultMsg saveOrUpdateAll(List<User> record) {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setData(record);
		resultMsg.setSuccess(true);
		String msg = "saveOrUpdateAll User success";
		if(CollectionUtils.isEmpty(record)){
			msg = "saveOrUpdateAll User is null";
			resultMsg.setSuccess(false);
			logger.error("saveOrUpdateAll User is null");
		}else{
			try {
				logger.info("saveOrUpdateAll List<User>"+ JSONObject.toJSONString(record));
				userDao.saveOrUpdateAll(record);
			} catch (Exception e) {
				msg = "saveOrUpdateAll User fail";
				resultMsg.setSuccess(false);
				logger.error("deleteUser fail:",e);
			}
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}
	
	@Override
	@Transactional 
	public ResultMsg deleteUser(String id){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setData(id);
		resultMsg.setSuccess(true);
		String msg = "deleteUser success";
		if(id==null){
			msg = "deleteUser id is null";
			resultMsg.setSuccess(false);
			logger.error("deleteUser id is null");
		}else{
			try {
				logger.info("deleteUser id:"+id);
				userDao.deleteUser(id);
			} catch (Exception e) {
				msg = "deleteUser fail";
				resultMsg.setSuccess(false);
				logger.error("deleteUser fail:",e);
			}
		}
		resultMsg.setMsg(msg);
		return resultMsg;
	}
	
	@Override
	@Transactional
	public ResultMsg insertUser(User record){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setData(record);
		resultMsg.setSuccess(true);
		String msg = "insertUser success";
		if(record==null){
			msg = "insertUser null";
			resultMsg.setSuccess(false);
			logger.error("insertUser null");
		}else{
			if(StringUtils.isBlank(record.getName())){
				msg = "insertUser name null";
				resultMsg.setSuccess(false);
				logger.error("insertUser name null");
			}else{
				try {
					logger.info("insertUser name:"+record.getName());
					userDao.insertUser(record);
				} catch (Exception e) {
					msg = "insertUser fail";
					resultMsg.setSuccess(false);
					logger.error("insertUser fail:",e);
				}
			}
		}
		resultMsg.setMsg(msg);
		return resultMsg;
    }
	
	@Override
	@Transactional
	public ResultMsg updateUser(User record){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setData(record);
		resultMsg.setSuccess(true);
		String msg = "updateUser success";
		if(record==null){
			msg = "updateUser null";
			resultMsg.setSuccess(false);
			logger.error("updateUser null");
		}else{
			if(StringUtils.isBlank(record.getId())){
				msg = "updateUser id null";
				resultMsg.setSuccess(false);
				logger.error("updateUser id null");
			}else{
				try {
					logger.info("updateUser name:"+record.getName());
					userDao.updateUser(record);
				} catch (Exception e) {
					msg = "updateUser fail";
					resultMsg.setSuccess(false);
					logger.error("updateUser fail:",e);
				}
			}
		}
		resultMsg.setMsg(msg);
		return resultMsg;
    }
	
	/**
	 * @Cacheable value :ehcache.xml  <cache name="frontCache"
	 */
	@Override
	@Transactional
	@Cacheable(value="frontCache",key="'cacheUserInfo'+#id") 
	public ResultMsg findUser(String id){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setMsg("findUser by id success");
		resultMsg.setSuccess(true);
		User u = null;
		try {
			u = userDao.findUser(id);
		} catch (Exception e) {
			resultMsg.setMsg("findUser by id fail");
			resultMsg.setSuccess(false);
			logger.error("findUser by id fail:",e);
		}
		resultMsg.setData(u);
		return resultMsg;
    }
	
	@Override
	public int countUser(UserVo record){
		return userDao.countUser(record);
    }
	
	@Override
	public ResultMsg listUser(User record){
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setMsg("find listUser success");
		resultMsg.setSuccess(true);
		List<User> list = null;
		try {
			list = userDao.listUser(record);
		} catch (Exception e) {
			resultMsg.setMsg("find listUser fail");
			resultMsg.setSuccess(false);
			logger.error("find listUser fail:",e);
		}
		resultMsg.setData(list);
		return resultMsg;
    }

	@Override
	@Cacheable(cacheNames="isExists", key="#username")
	public boolean isExists(String username) {
		return userDao.isExists(username);
	}

}
