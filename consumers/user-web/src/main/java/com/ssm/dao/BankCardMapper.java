package com.ssm.dao;

import com.ssm.entity.table.BankCard;

public interface BankCardMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
}