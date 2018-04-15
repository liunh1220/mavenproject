package com.example.testproject.dao;

import com.example.testproject.entity.table.UserTransaction;

public interface UserTransactionMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTransaction record);

    int insertSelective(UserTransaction record);

    UserTransaction selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTransaction record);

    int updateByPrimaryKey(UserTransaction record);
}