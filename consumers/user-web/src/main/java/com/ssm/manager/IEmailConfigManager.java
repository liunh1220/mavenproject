package com.ssm.manager;

import java.util.List;

import com.ssm.domain.EmailConfig;
import com.ssm.util.Page;

public interface IEmailConfigManager {

    EmailConfig getById(String id);
	
    void getByPage(Page page);
	
    void saveOrUpdate(EmailConfig emailConfig);
	
    void remove(EmailConfig emailConfig);

	List<EmailConfig> list(EmailConfig emailConfig);
	
	EmailConfig getEmailConfigByTypeId(String typeId);
	
	EmailConfig getEmailConfigByStoreId(String storeId);
	
	EmailConfig getEmailConfigByStoreIdAndTypeId(String storeId, String typeId);
	
	void removeEmailBySellerIdAndTypes(String sellerId);
	
	void updateEmailBySellerIdAndTypes(EmailConfig emailConfig);
}
