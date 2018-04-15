package com.example.common.util;

import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.web.context.ServletContextAware;

public class FrameworkConfigurer extends PropertyPlaceholderConfigurer 
	implements ServletContextAware
{
    private static Properties properties;
    
    private static boolean encryptSwitch = false;
    
    private ServletContext servletContext;
    
    public static boolean isEncryptSwitch()
    {
        return encryptSwitch;
    }
    
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, 
    		Properties props)
        throws BeansException
    {
        properties = props;
        InitParameter();
        super.processProperties(beanFactory, props);
    }
    
    public static Object getProperties(String key)
    {
        return properties.get(key);
    }
    
    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }
    
    private void InitParameter()
    {
        if (null == this.servletContext)
            return;
        String retention = this.servletContext.getInitParameter("retention");
        if ((!(StringUtils.isNotEmpty(retention))) || (!("PRODUCTION".equals(retention))))
            return;
        retention = this.servletContext.getInitParameter("db.jdbcUrl");
        if (StringUtils.isNotEmpty(retention))
        {
            try
            {
                properties.put("db.jdbcUrl", EncryptUtil.aesDecode(retention));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                properties.put("db.jdbcUrl", null);
            }
        }
        retention = this.servletContext.getInitParameter("db.dbType");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("db.dbType", retention);
        }
        retention = this.servletContext.getInitParameter("db.user");
        if (StringUtils.isNotEmpty(retention))
        {
            try
            {
                properties.put("db.user", EncryptUtil.aesDecode(retention));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                properties.put("db.user", null);
            }
        }
        retention = this.servletContext.getInitParameter("db.password");
        if (StringUtils.isNotEmpty(retention))
        {
            try
            {
                properties.put("db.password", EncryptUtil.aesDecode(retention));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                properties.put("db.password", null);
            }
        }
        retention = this.servletContext.getInitParameter("db.driverClass");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("db.driverClass", retention);
        }
        retention = this.servletContext.getInitParameter("db.dialect");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("db.dialect", retention);
        }
        retention = this.servletContext.getInitParameter("ehcache.file");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("ehcache.file", retention);
        }
        retention = this.servletContext.getInitParameter("quartz.file");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("quartz.file", retention);
        }
        retention = this.servletContext.getInitParameter("platForm_url");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("SITE_INTERFACE_URL", retention);
        }
        retention = this.servletContext.getInitParameter("mail.host");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("mail.host", retention);
        }
        retention = this.servletContext.getInitParameter("mail.port");
        if (StringUtils.isNotEmpty(retention))
        {
            properties.put("mail.port", retention);
        }
        else
        {
            properties.put("mail.port", "25");
        }
        
        retention = this.servletContext.getInitParameter("mail.username");
        if (StringUtils.isNotEmpty(retention))
        {
            try
            {
                properties.put("mail.username", EncryptUtil.aesDecode(retention));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                properties.put("mail.username", null);
            }
        }
        retention = this.servletContext.getInitParameter("mail.password");
        if (!(StringUtils.isNotEmpty(retention)))
            return;
        properties.put("mail.password", retention);
        try
        {
            properties.put("mail.password", EncryptUtil.aesDecode(retention));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args)
    {
    }
}