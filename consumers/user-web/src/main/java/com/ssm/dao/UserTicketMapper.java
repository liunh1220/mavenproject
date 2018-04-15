package com.ssm.dao;

import com.ssm.entity.table.UserTicket;

public interface UserTicketMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserTicket record);

    int insertSelective(UserTicket record);

    UserTicket selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserTicket record);

    int updateByPrimaryKey(UserTicket record);
}