package com.example.common.util.request;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.example.common.util.file.FileItem;

public class HttpServletRequestWrapperGold extends HttpServletRequestWrapper {

	private String charset = "UTF-8";
	List<FileItem> items = new ArrayList<FileItem>();
	
	public HttpServletRequestWrapperGold(HttpServletRequest request) {
		super(request);
	}

	  /**
     * 获得被装饰对象的引用和采用的字符编码
     * 
     * @param request
     * @param charset
     */
    public HttpServletRequestWrapperGold(HttpServletRequest request,String charset) {
        super(request);
        this.charset = charset;
    }

    /**
     * 实际上就是调用被包装的请求对象的getParameter方法获得参数，然后再进行编码转换
     */
    public String getParameter(String name) {
        String value = super.getParameter(name);
        value = value == null ? null : convert(value);
        return value;
    }

    public String convert(String target) {
        //System.out.println("编码转换之前：" + target);
        try {
            return new String(target.trim().getBytes("ISO-8859-1"), charset);
        } catch (UnsupportedEncodingException e) {
            return target;
        }
    }
    
}
