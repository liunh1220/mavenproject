package com.example.hibernate.util;

import java.io.Serializable;

import com.alibaba.fastjson.JSONObject;

/**
 * 操作返回类
 * 
 */
public class ResultMsg implements Serializable{

	private static final long serialVersionUID = 2022089935244043770L;
	// 成功标识
    private boolean success;
    // 返回代码
    private String reCode;
    // 结果ID
    private String idCode;
    // 返回信息
    private String msg;
    // 返回实体
    private JSONObject data;

    public ResultMsg() {
    }

    public ResultMsg(boolean success, String reCode, String idCode, String msg, Object data) {
        this.success = success;
        this.reCode = reCode;
        this.idCode = idCode;
        this.msg = msg;
        this.data = data==null?null:(JSONObject) JSONObject.toJSON(data);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReCode() {
        return reCode;
    }

    public void setReCode(String reCode) {
        this.reCode = reCode;
    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data==null?null:(JSONObject) JSONObject.toJSON(data);
    }

	@Override
	public String toString() {
		return "ResultMsg [success=" + success + ", reCode=" + reCode + ", idCode=" + idCode + ", msg=" + msg
				+ ", data=" + data + "]";
	}

    
}
