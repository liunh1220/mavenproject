package com.ssm.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.ssm.base.BaseDao;
import com.ssm.dao.IJobDao;
import com.ssm.domain.Job;

@Repository
public class JobDaoImpl extends BaseDao<Job> implements IJobDao {

	@Override
	public List<Job> listByIplist(Map<String, Object> param) throws DataAccessException {
		return null;//(List<Job>) this.getSqlSessionTemplate().selectList("sysmgt.JobMapper.getListByIplist", param);
	}
	
}
