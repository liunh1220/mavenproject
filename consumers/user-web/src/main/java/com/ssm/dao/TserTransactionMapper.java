package com.ssm.dao;

import com.ssm.entity.table.TserTransaction;

public interface TserTransactionMapper {
    int deleteByPrimaryKey(String id);

    int insert(TserTransaction record);

    int insertSelective(TserTransaction record);

    TserTransaction selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TserTransaction record);

    int updateByPrimaryKey(TserTransaction record);
}