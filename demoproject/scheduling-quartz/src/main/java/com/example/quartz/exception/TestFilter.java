package com.example.quartz.exception;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TestFilter implements Filter
{
    
    @Override
    public void init(FilterConfig config)
        throws ServletException
    {
    }
    
    @Override
    public void destroy()
    {
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)req;
        String uri = request.getRequestURI();
        long start = System.currentTimeMillis();
        		
        chain.doFilter(req, res);
        long end = System.currentTimeMillis();
        System.out.println(uri+":\t"+(end-start)+"ms");
    }
}

