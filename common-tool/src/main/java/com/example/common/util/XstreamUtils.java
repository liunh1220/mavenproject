package com.example.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.Xpp3DomDriver;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/** 
 * xstream工具封装 
 * 用以处理xml与bean的转换 
 * @see  [相关类/方法] 
 * @since  [产品/模块版本] 
*/  
public class XstreamUtils  {
     
   /** 
     * 在xml中多余的节点生成bean时会抛出异常 
     * 通过该mapperWrapper跳过不存在的属性 
     * @param mapper 
     * @return [参数说明] 
     *  
     * @return MapperWrapper [返回类型说明] 
     * @exception throws [异常类型] [异常说明] 
     * @see [类、类#方法、类#成员] 
    */  
   private static MapperWrapper createSkipOverElementMapperWrapper(  
           Mapper mapper)  
   {  
       MapperWrapper resMapper = new MapperWrapper(mapper)  
       {  
           /** 
            * @param elementName 
            * @return 
            */  
           @SuppressWarnings("rawtypes")  
           @Override  
           public Class realClass(String elementName)  
           {  
               Class res = null;
               try  
               {  
                   res = super.realClass(elementName); 
               }  
               catch (CannotResolveClassException e)  
               {  
            	  // logger.error(e.getMessage(),e);
                 // logger.info(Thread.currentThread().getStackTrace()[1].getMethodName()+"  xstream change xml to object. filed (0) not exsit. "+elementName);  
               }  
              return res;  
           }  
       };  
         
       return resMapper;  
   }  
     
   /** 
    * 获取xstream转换对象 
    * @param classType 
    * @return [参数说明] 
    *  
    * @return XStream [返回类型说明] 
    * @exception throws [异常类型] [异常说明] 
    * @see [类、类#方法、类#成员] 
   */  
   public static XStream getXstream(Class<?> classType)  
   {  
       return getXstream(classType, true);  
   }  
     
   /** 
     * 获取xstream转换对象 
     * @param classType 
     * @param isSkipOverElement 
     * @return [参数说明] 
     *  
     * @return XStream [返回类型说明] 
     * @exception throws [异常类型] [异常说明] 
     * @see [类、类#方法、类#成员] 
    */  
   public static XStream getXstream(Class<?> classType,  
           boolean isSkipOverElement)  
   {  
		NameCoder nameCoder = new NameCoder() {
			public String encodeNode(String arg0) {
				return arg0;
			}

			public String encodeAttribute(String arg0) {
				return arg0;
			}

			public String decodeNode(String arg0) {
				return arg0;
			}

			public String decodeAttribute(String arg0) {
				return arg0;
			}
		};
       XStream res = null;  
       if (isSkipOverElement)  
       {  
           res = new XStream(new Xpp3DomDriver(nameCoder))  
           {  
               /** 
                * @param next 
                * @return 
                */  
               protected MapperWrapper wrapMapper(MapperWrapper next)  
               {  
                   return createSkipOverElementMapperWrapper(next);  
               }  
           };  
       }  
       else  
       {  
           res = new XStream(new Xpp3DomDriver(nameCoder));  
       }  
       res.processAnnotations(classType);  
         
       return res;  
   }  
     
} 