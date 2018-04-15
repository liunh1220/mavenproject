package com.example.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TemplateUtil {
	@Autowired
	private static ApplicationContextUtil appUtil;
	private static final Logger logger = LoggerFactory.getLogger(TemplateUtil.class);
	
	/**
	 * 对象填充到模板并返回模板内容
	 * @param templatePath freemark模板路径
	 * @param obj 待填充对象
	 * @return
	 * @throws TemplateException
	 */
	public static String getContent(String templatePath,Object obj) throws Exception{
		Configuration freemarkerConfiguration = (Configuration)appUtil.getBean("freemarkerConfiguration");
		Template template = null;
		String content = null;
		try {
			template = freemarkerConfiguration.getTemplate(templatePath, "UTF-8");
			content = FreeMarkerTemplateUtils.processTemplateIntoString(template, obj);
		} catch (Exception e) {
			logger.error("getContent",e);
			throw e;
		}
		return content;
	}
}
