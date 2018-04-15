package com.ssm.dao;

import com.ssm.entity.table.BankUnion;

public interface BankUnionMapper {
    int deleteByPrimaryKey(String id);

    int insert(BankUnion record);

    int insertSelective(BankUnion record);

    BankUnion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BankUnion record);

    int updateByPrimaryKey(BankUnion record);
}