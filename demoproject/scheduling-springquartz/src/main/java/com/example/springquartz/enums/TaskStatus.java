package com.example.springquartz.enums;
/*     
 * 任务运行状态     
 */ 
public enum TaskStatus {
	
	NONE("NONE","未知"),     
	NORMAL("NORMAL", "正常运行"),     
	PAUSED("PAUSED", "暂停状态"),      
	COMPLETE("COMPLETE","完成"),     
	ERROR("ERROR","错误状态"),     
	LOCKED("LOCKED","锁定状态");     
	     
	private TaskStatus(String index,String name) {        
        this.name = name;        
        this.index = index;        
    }       
	private String index;       
	private String name;       
	public String getName() {        
        return name;        
    }        
    public String getIndex() {        
        return index;        
    }     
}
