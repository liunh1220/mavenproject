package com.ssm.util;

import java.util.Collections;
import java.util.List;

/**
 * 分页类
 *
 */
public class Page
{
    public static int DEFAULT_START = 1;//默认开始行
    
    public static int DEFAULT_LIMIT = 20;//默认每页显示行数
    
    public static int DEFAULT_PAGE = 1;//默认页
    
    private int start;//开始行
    
    private int limit;//每页显示纪录数
    
    private int currentPage;//当前页
    
	private int count;//总纪录数
    
    private Object condition;//查询条件
    
	private List result = Collections.emptyList();//查询结果
    
    @SuppressWarnings("static-access")
    public Page()
    {
        this.start = this.DEFAULT_START;//默认开始行
        this.limit = this.DEFAULT_LIMIT;//默认每页显示行数
        this.currentPage = this.DEFAULT_PAGE;//默认页
    }
    
    public Page(int start,int limit)
    {
        this.start = start;
        this.limit = limit;
    }

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public List getResult()
    {
        return result;
    }
    
    public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

    public void setResult(List result)
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
    	this.start = this.limit * (this.currentPage - 1) + 1;
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
