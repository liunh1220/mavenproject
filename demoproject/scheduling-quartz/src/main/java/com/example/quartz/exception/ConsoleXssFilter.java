package com.example.quartz.exception;


import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.example.common.util.request.HttpServletRequestWrapperGold;

public class ConsoleXssFilter implements Filter
{
    private String encoding;
    
    private String[] illegalChars;
    
    private String[] unCatch;
    
    @Override
    public void init(FilterConfig config)
        throws ServletException
    {
        this.encoding = config.getInitParameter("encoding");
        this.illegalChars = config.getInitParameter("illegalChars").split(",");
        this.unCatch = config.getInitParameter("unCatch").split(",");
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
        req.setCharacterEncoding(this.encoding);
        res.setContentType("text/html;charset=" + this.encoding);
        //是否为Ajax标志
        String xrq = request.getHeader("X-Requested-With");
        boolean result = false;
        boolean flag = true;
        for (int i = 0; i < this.unCatch.length; ++i)
        {
            if (!(request.getRequestURI().contains(this.unCatch[i])))
                continue;
            flag = false;
            break;
        }
        
        Map<String, String[]> submitParams = req.getParameterMap();
        
        if (flag)
        {
            
            if (StringUtils.isEmpty(req.getContentType())
                && req.getContentType().split(";")[0].equals("multipart/form-data"))
            {
                try
                {
                    HttpServletRequestWrapperGold wrapperRequest =
                        new HttpServletRequestWrapperGold((HttpServletRequest)req);
                    DiskFileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    upload.setHeaderEncoding("UTF-8");
                    List<FileItem> items = upload.parseRequest(wrapperRequest);
                    for (FileItem fileItem : items)
                    {
                        if (fileItem.isFormField())
                        {
                            for (int j = 0; j < this.illegalChars.length; ++j)
                            {
                                String illegalChar = this.illegalChars[j].replaceAll("&lt;", "<");
                                if (fileItem.getString("utf-8").indexOf(illegalChar) == -1)
                                {
                                    continue;
                                }
                                writerMessage((HttpServletRequest)req, res);
                                return;
                            }
                        }
                    }
                    chain.doFilter(wrapperRequest, res);
                    return;
                }
                catch (FileUploadException e)
                {
                    e.printStackTrace();
                }
                
            }
            
            if (null != submitParams)
            {
                Set<String> submitNames = submitParams.keySet();
                for (String submitName : submitNames)
                {
                    for (int j = 0; j < this.illegalChars.length; ++j)
                    {
                        String illegalChar = this.illegalChars[j].replaceAll("&lt;", "<");
                        
                        if (request.getParameter(submitName).indexOf(illegalChar) == -1)
                            continue;
                        result = true;
                        break;
                    }
                    
                }
                
            }
            else
            {
                result = false;
            }
            
        }
        
        if (result)
        {
            //是否是ajax请求
            if (StringUtils.isEmpty(xrq))
            {
                HttpServletResponse response = (HttpServletResponse)res;
                String webPath = request.getContextPath();
                response.sendRedirect(webPath + "/home/errorcharactor.do");
                
            }
            else
            {
                writerMessage((HttpServletRequest)req, res);
            }
            return;
        }
        chain.doFilter(req, res);
    }
    
    public void writerMessage(HttpServletRequest req, ServletResponse res)
    {
        try
        {
            res.setContentType("application/json;charset=UTF-8");
            OutputStream out = res.getOutputStream();
            Map<String, String> map = new HashMap<String, String>();
            map.put("description", "当前链接中存在非法字符");
            out.write(("'','xss':" + JSONObject.toJSONString(map) + "}").getBytes("UTF-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
