package com.example.http.dao;

import com.example.http.model.Configuration;
import com.github.pagehelper.Page;
import org.springframework.dao.DataAccessException;
import java.util.List;

public interface ConfigurationMapper{

    void save(Configuration entity) throws DataAccessException;
	
	int update(Configuration entity) throws DataAccessException;
	
	Configuration getById(String id) throws DataAccessException;
	
	Object getByKey(Object param) throws DataAccessException;
	
	Object getObByKey(Object param) throws DataAccessException;
	
	List<Configuration> getList(Object entity) throws DataAccessException;
	
	void remove(Configuration entity) throws DataAccessException;
	
	void getByPage(Page<Configuration> page);
	
}
