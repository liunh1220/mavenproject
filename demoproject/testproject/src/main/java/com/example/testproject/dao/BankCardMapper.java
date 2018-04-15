package com.example.testproject.dao;

import com.example.testproject.entity.table.BankCard;

public interface BankCardMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankCard record);

    int insertSelective(BankCard record);

    BankCard selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankCard record);

    int updateByPrimaryKey(BankCard record);
}