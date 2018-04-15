package com.example.jdbc.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.example.jdbc.base.BaseJdbcDao;
import com.example.jdbc.dao.ITestDao;

@Repository
public class TestDaoImpl extends BaseJdbcDao implements ITestDao{

	//private static final Logger logger = LoggerFactory.getLogger(TestDaoImpl.class);
	
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
	@Override
	public Object deleteUser(String[] ids) {
		return delete("t_user", ids);
	}
	
	@Override
	public Object deleteUser(JSONObject data) {
		return delete("t_user", data);
	}

	@Override
	public Object insertUser(JSONObject data) {
		return insert("t_user", data);
	}

	@Override
	public Object findUser(String id) {
		return queryForJsonObject("select * from t_user where id=?", id);
	}

	@Override
	public Object updateUser(JSONObject data) {
		return update("t_user", data);
	}
	
	@Override
	public Object updateUserBatch(List<Object[]> batchArgs) {
		return updateBatch("update t_user set create_time=? where id=?", batchArgs);
	}

	@Override
	public Object countUser(JSONObject data) {
		return queryForJsonObject("select count(*) as count from t_user", null);
	}

	@Override
	public Object listUser(JSONObject data) {
		return queryForJsonList("select * from t_user", null);
	}

	@Override
	public boolean isExists(String username) {
		String countStr = queryForString("select count(*) from t_user where name=?", username);
		if(StringUtils.isNotBlank(countStr) && Integer.valueOf(countStr) > 0){
			return true;
		}
		return false;
	}
	

}
