package com.ssm.entity.table;

public class BankUnion {
    /**  */
    private String id;

    /** ������ */
    private String bankName;

    /** ���п�ǰ׺ */
    private String cardPrefix;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix == null ? null : cardPrefix.trim();
    }
}