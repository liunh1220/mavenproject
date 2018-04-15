package com.ssm.entity.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.entity.SmsState;

/**
 * 短信发送Vo
 *  手机短信
 */
public class SmsVo implements Serializable,Cloneable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	
    /**
     * 手机号码
     */
	private String[] phone;
	
	public String[] getPhone() {
		return phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	private List<String> pLst;
	
	/**
	 * 手机短信内容
	 */
	private String content;
	
	/** 模块类型  */
	private String modelType;
	
	public List<String> getpLst() {
		return pLst;
	}

	public void setpLst(List<String> pLst) {
		this.pLst = pLst;
	}
	/** 发送状态 */
	private SmsState state;
	
	/** 发送渠道(用于切换sms产品发送) */
	private String channle;


	/**
	 * 业务编号
	 */
	private String bizNo;
	
	private Date createTime;
	
	private Map<String,SmsState> phone_state = new HashMap<String,SmsState>();
	public Map<String, SmsState> getPhone_state() {
		return phone_state;
	}

	public void setPhone_state(Map<String, SmsState> phone_state) {
		this.phone_state = phone_state;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getModelType() {
		return modelType;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public SmsState getState() {
		return state;
	}

	public void setState(SmsState state) {
		this.state = state;
	}

	public String getChannle() {
        return channle;
    }

    public void setChannle(String channle) {
        this.channle = channle;
    }

    @Override
    public String toString() {
        return "SmsVo [id=" + id + ", phone=" + Arrays.toString(phone) + ", content=" + content
                + ", modelType=" + modelType + ", state=" + state + ", channle=" + channle + "]";
    }
    @Override
    public SmsVo clone() {
    	SmsVo o = null;  
            try {  
                o = (SmsVo) super.clone();  
                o.setpLst(new ArrayList<String>());
            } catch (CloneNotSupportedException e) {  
                e.printStackTrace();  
            }  
            return o;  
    }
}
