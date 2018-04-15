package com.example.testproject.dao;

import com.example.testproject.entity.table.BankUnion;

public interface BankUnionMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankUnion record);

    int insertSelective(BankUnion record);

    BankUnion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankUnion record);

    int updateByPrimaryKey(BankUnion record);
}