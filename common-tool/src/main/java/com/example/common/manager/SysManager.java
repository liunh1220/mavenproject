package com.example.common.manager;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.common.util.ApplicationContextUtil;

@Repository
public class SysManager {
	
	@Autowired
	private static ApplicationContextUtil appUtil;
	
	private String dbSysdate(){
		SqlSessionTemplate session = (SqlSessionTemplate) appUtil.getBean("sqlSession");
		return (String) session.selectOne("com.example.mybatis.dao.sys.SysManagerMapper.dbCurrentSysdate", new String());
	}
}
