package com.example.common.annotation;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
/** 
 * 用于Excel导出时给每个pojo对象的字段添加字段名称，作为excel的表头 
 *  
 * */  
@Retention(RetentionPolicy.RUNTIME)    
@Target(ElementType.FIELD)    
public @interface ExcelAnnotation {    
    // excel导出时标题显示的名字，如果没有设置Annotation属性，将不会被导出和导入    
    public String exportName();    
}  
