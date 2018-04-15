package com.example.testproject.dao;

import com.example.testproject.entity.table.Ticket;

public interface TicketMapper {
    int deleteByPrimaryKey(String id);

    int insert(Ticket record);

    int insertSelective(Ticket record);

    Ticket selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Ticket record);

    int updateByPrimaryKey(Ticket record);
}