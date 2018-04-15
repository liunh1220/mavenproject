package com.ssm.dao;

import com.ssm.entity.table.Bank;

public interface BankMapper {
    int deleteByPrimaryKey(String id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);
}