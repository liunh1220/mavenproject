package com.ssm.dao.impl;

import org.springframework.stereotype.Repository;

import com.ssm.base.BaseDao;
import com.ssm.dao.IEmailConfigDao;
import com.ssm.domain.EmailConfig;

@Repository
public class EmailConfigDaoImpl extends BaseDao<EmailConfig> implements IEmailConfigDao {

	@Override
	public int updateEmailBySellerIdAndTypes(String name, EmailConfig emailConfig)
			throws RuntimeException {
		return this.getSqlSessionTemplate().update(name, emailConfig);
	}

	@Override
	public void removeEmailBySellerIdAndTypes(String name, String sellerId)
			throws RuntimeException {
		this.getSqlSessionTemplate().delete(name, sellerId);
	}
	
}
