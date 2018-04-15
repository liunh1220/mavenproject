package com.example.testproject.dao;

import com.example.testproject.entity.table.Bank;

public interface BankMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}