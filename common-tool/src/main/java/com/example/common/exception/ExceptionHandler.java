package com.example.common.exception;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.example.common.base.BaseResp;
import com.example.common.util.StringUtil;

@Component
public class ExceptionHandler implements HandlerExceptionResolver
{
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
    
    @Resource
    private MessageSource resources;
    
    private static final String BUSNIESS_ERROR = "系统正在维护中，请稍后再试";
    
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object obj,
        Exception exception)
    {
        BaseResp baseResp = new BaseResp();
        if (ServicesException.class.isAssignableFrom(exception.getClass()))
        {
            ServicesException se = (ServicesException)exception;
            baseResp.setCode(se.getCode());
            String desc = se.getExceptionMsg();
            if (StringUtil.isEmpty(desc))
            {
                desc = this.resources.getMessage(se.getCode(), null, "系统正在维护中，请稍后再试", null);
            }
            baseResp.setDescription(desc);
        }
        else if ((UnauthorizedException.class.isAssignableFrom(exception.getClass()))
            || (AuthorizationException.class.isAssignableFrom(exception.getClass())))
        {
            baseResp.setCode("000009");
            baseResp.setDescription("ERROR_AUTHORIZATION");
        }
        else if (MaxUploadSizeExceededException.class.isAssignableFrom(exception.getClass()))
        {
            baseResp.setCode("000006");
            baseResp.setDescription("ERROR_MAX_UPLOAD_SIZE");
        }
        else
        {
            baseResp.setCode("000001");
            baseResp.setDescription("ERROR_UNKNOWN");
        }
        logger.error("ExceptionHandler.resolveException", exception);
        logger.info("ExceptionHandler.resolveException, baseResp = " + baseResp);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            out.print(baseResp.toString());
            response.flushBuffer();
        }
        catch (IOException e)
        {
        	logger.error("ExceptionHandler.resolveException", e);
        }
        finally
        {
            if (null != out)
            {
                out.close();
            }
        }
        return new ModelAndView();
    }
}