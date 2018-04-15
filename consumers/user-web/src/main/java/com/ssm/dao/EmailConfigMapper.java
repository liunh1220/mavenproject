package com.ssm.dao;

import com.ssm.base.IBaseDao;
import com.ssm.domain.EmailConfig;

public interface EmailConfigMapper extends IBaseDao<EmailConfig>{
	
	int updateEmailBySellerIdAndTypes(String name, EmailConfig emailConfig) throws RuntimeException;
	
	void removeEmailBySellerIdAndTypes(String name, String sellerId) throws RuntimeException;

}
