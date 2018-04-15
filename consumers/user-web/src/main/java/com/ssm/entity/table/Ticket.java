package com.ssm.entity.table;

public class Ticket {
    /**  */
    private String id;

    /** Ʊ���� */
    private Integer ticketCount;

    /** Ʊ���� */
    private Byte type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}