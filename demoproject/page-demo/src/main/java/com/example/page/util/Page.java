package com.example.page.util;

import java.util.Collections;
import java.util.List;

/**
 * 分页类
 */
public class Page<T>
{
    public static int DEFAULT_START = 1;//默认开始行
    
    public static int DEFAULT_LIMIT = 20;//默认每页显示行数
    
    public static int DEFAULT_PAGE = 1;//默认页
    
    private int start;//开始行
    
    private int pageSize;//每页显示纪录数
    
    private int pageNo;//当前页
    
	private int count;//总纪录数
    
    private Object condition;//查询条件
    
	private List<T> result = Collections.emptyList();//查询结果
    
    @SuppressWarnings("static-access")
    public Page()
    {
        this.start = this.DEFAULT_START;//默认开始行
        this.pageSize = this.DEFAULT_LIMIT;//默认每页显示行数
        this.pageNo = this.DEFAULT_PAGE;//默认页
    }
    
    public Page(int start,int pageSize)
    {
        this.start = start;
        this.pageSize = pageSize;
    }

    public List<T> getResult()
    {
        return result;
    }
    
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setResult(List<T> result)
    {
        this.result = result;
    }
    
    public Object getCondition() {
		return condition;
	}

	public void setCondition(Object condition) {
		this.condition = condition;
	}

    public int getStart()
    {
    	//this.start = this.pageSize * (this.pageNo - 1) + 1;
    	this.start = this.pageSize * (this.pageNo - 1);
        return this.start;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }
    
}
