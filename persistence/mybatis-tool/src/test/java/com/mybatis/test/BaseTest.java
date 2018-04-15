package com.mybatis.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:spring-base.xml","classpath*:mybatis-config.xml","classpath*:spring-mybatis.xml"}) 
public class BaseTest  extends AbstractJUnit4SpringContextTests  {

}
