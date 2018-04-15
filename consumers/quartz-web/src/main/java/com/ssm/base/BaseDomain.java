package com.ssm.base;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BaseDomain implements Serializable {

	// 唯一标识
	private String id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3212883624334920341L;


	/**
	 * 动�?字段. 在ibatis文件中可用�?dynamicFields.xxx”方式读取动态字段�?
	 */
	protected Map<String,Object> dynamicFields = new HashMap<String,Object>();
	

	/** �?��行数.	 */
	private static final int MAX_ROWS = 9999999;
	
	/** 起始行数（oracle物理行号�?�?���?*/
	private int start = 0;

	/**
	 * 结束行数（如果不设置结束行，缺省查所有满足条件记录）.
	 */
	private int end = MAX_ROWS;
	
	/**
	 * 排序字段(例sp.spCode).
	 */
	private String sort;

	/**
	 * 正序|反序(例ASC).
	 */
	private String order;
	
	// 创建人
	private String createBy;
	
	// 创建时间
	private Date createTime;
	
	// 修改人
	private String updateBy;
	
	// 修改时间
	private Date updateTime;
	
	// 是否可用,默认可用
	private String enabledFlag="Y";

	/**
	 * 设置动�?字段�?
	 * 
	 * @param fieldName
	 *            字段名称
	 * @param value
	 *            字段�?
	 */
	public void setField(String fieldName, Object value) {
		dynamicFields.put(fieldName, value);
	}

	/**
	 * 返回动�?字段�?
	 * 
	 * @param fieldName
	 *            字段名称
	 * @return 对象
	 */
	public Object getField(String fieldName) {
		if (dynamicFields == null) {
			return null;
		}
		return getDynamicFields().get(fieldName);
	}
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public Map<String,Object> getDynamicFields() {
		if (dynamicFields != null && dynamicFields.size() > 0) {
			Set<String> set = dynamicFields.keySet();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if (dynamicFields.get(key)!=null && dynamicFields.get(key).getClass().isArray()) {
					Object[] objArr = (Object[])dynamicFields.get(key);
					if(objArr.length==1){
					dynamicFields.put(key,	((Object[]) dynamicFields.get(key))[0]);
					}
				}
			}
		}
		return dynamicFields;
	}

	public void setDynamicFields(Map<String,Object> dynamicFields) {
		this.dynamicFields = dynamicFields;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getEnabledFlag() {
		return enabledFlag;
	}

	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
