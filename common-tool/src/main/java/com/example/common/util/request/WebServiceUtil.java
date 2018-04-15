package com.example.common.util.request;

import java.net.URL;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;

import com.example.common.util.MySoapHeader;

public class WebServiceUtil {

	/**
	 * 调用 webService WEB 服务
	 * 
	 * @param serviceDefinition
	 * @param params
	 * @param map 验证信息
	 * @return Object
	 */
	//
	public static Object postWebService(String url, String method, Object[] params,Map<String,String> map ) throws Exception {
		try {
			Client client = new Client(new URL(url));
			// 设置连接超时参数
			 client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,"16000");
			// 请求完成后关闭连接释放资源
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
			// 禁用HTTP请求头部参数EXPECT
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
			// 启用响应数据传输压缩
			client.setProperty(CommonsHttpMessageSender.GZIP_RESPONSE_ENABLED, Boolean.TRUE);
			// 启用请求数据传输压缩
			// client.setProperty(CommonsHttpMessageSender.GZIP_REQUEST_ENABLED,
			// Boolean.TRUE);
			// 启用GZIP传输压缩
			// client.setProperty(CommonsHttpMessageSender.GZIP_ENABLED, Boolean.TRUE);
			// 添加身份认证
			// client.addOutHandler(new AuthenticationOutHandler(serviceDefinition.getServiceToken(), serviceDefinition.getServiceIdentifier()));
			if (null!=map) {
				client.addOutHandler(new MySoapHeader(map.get("uname"),map.get("password"),map.get("namespaceUrl")));
			}
			Object[] results = client.invoke(method, params);
			
			if (results == null || ArrayUtils.contains(results, Boolean.FALSE)) {
				throw new Exception("返回数据为空");
			}

			return results[0];

		} catch (Exception e) {
			System.out.println(e);
			throw e;
		}
	}
	
}
