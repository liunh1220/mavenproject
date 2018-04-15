package com.ssm.util.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ssm.util.StringUtil;

public class HttpsRequester {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpsRequester.class);

	public static final String DEFAULT_CHARSET = "UTF-8";
	private static final String METHOD_POST = "POST";
	private static final String METHOD_GET = "GET";
	
	private HttpsRequester() {}

	/**
	 * 执行HTTP POST请求。
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> params,int connectTimeout,int readTimeout) throws IOException {
		return doPost(url, params, connectTimeout,readTimeout,null);
	}

	/**
	 * 执行HTTP POST请求。（设置head）
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param charset 字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doPost(String url, Map<String, String> params, int connectTimeout,int readTimeout,Map<String,String> head)
			throws IOException {
		String query = buildQuery(params,  DEFAULT_CHARSET);
		return postByHttps(url,params, query, connectTimeout, readTimeout,head);
	}


	/**
	 * 执行HTTP GET请求。
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doGet(String url, Map<String, String> params) throws IOException {
		int connectTimeout=0;
		int readTimeout=0;
		return doGet(url, params, "gbk",connectTimeout,readTimeout);
	}

	public static String doGet(String url,Map<String, String> params,int connectTimeout,int readTimeout) throws IOException {
		return doGet(url, params,"gbk",connectTimeout,readTimeout);
	}
	/**
	 * 执行HTTP GET请求。
	 * 
	 * @param url 请求地址
	 * @param params 请求参数
	 * @param charset 字符集，如UTF-8, GBK, GB2312
	 * @return 响应字符串
	 * @throws IOException
	 */
	public static String doGet(String url, Map<String, String> params,String charset,int connectTimeout,int readTimeout)
			throws IOException {
		HttpURLConnection conn = null;
		String rsp = null;

		try {
			String ctype = "application/x-www-form-urlencoded;charset=" + charset;
			String query = buildQuery(params, charset);
			try{
				conn = getConnection(buildGetUrl(url, query), METHOD_GET, ctype,null);
				conn.setReadTimeout(readTimeout);
				conn.setConnectTimeout(connectTimeout);//connectTimeout, readTimeout
			}catch(IOException e){
				throw e;
			}
			
			try{
				rsp = getResponseAsString(conn,charset);
			}catch(IOException e){
				throw e;
			}
			
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	private static HttpURLConnection getConnection(URL url, String method, String ctype,Map<String,String> headMap)
			throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html,*/*");
		conn.setRequestProperty("User-Agent", "top-sdk-java");
		conn.setRequestProperty("Content-Type", ctype);

		if (headMap != null && !headMap.isEmpty()) {
			for (Map.Entry<String, String> entry : headMap.entrySet()) {
				String name = entry.getKey();
				String value = entry.getValue();
				// 忽略参数名或参数值为空的参数
				if (StringUtil.areNotEmpty(name, value)) {
					conn.setRequestProperty(name, value);
				}
			}
		}
		return conn;
	}

	private static URL buildGetUrl(String strUrl, String query) throws IOException {
		URL url = new URL(strUrl);
		if (StringUtil.isEmpty(query)) {
			return url;
		}

		if (StringUtil.isEmpty(url.getQuery())) {
			if (strUrl.endsWith("?")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "?" + query;
			}
		} else {
			if (strUrl.endsWith("&")) {
				strUrl = strUrl + query;
			} else {
				strUrl = strUrl + "&" + query;
			}
		}

		return new URL(strUrl);
	}

	public static String buildQuery(Map<String, String> params, String charset) throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (StringUtil.areNotEmpty(name, value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}
				query.append(name).append("=").append(URLEncoder.encode(value, charset));
			}
		}

		return query.toString();
	}

	protected static String getResponseAsString(HttpURLConnection conn,String fromCharset) throws IOException {
		String charset = "";
		if (StringUtil.isEmpty(conn.getContentType())) {
			charset = fromCharset;
		} else {
			charset = getResponseCharset(conn.getContentType());
		}
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), charset);
		} else {
			String msg = getStreamAsString(es, charset);
			if (StringUtil.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}

	private static String getStreamAsString(InputStream stream, String charset) throws IOException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
			StringWriter writer = new StringWriter();

			char[] chars = new char[256];
			int count = 0;
			while ((count = reader.read(chars)) > 0) {
				writer.write(chars, 0, count);
			}

			return writer.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}

	private static String getResponseCharset(String ctype) {
		String charset = DEFAULT_CHARSET;

		if (!StringUtil.isEmpty(ctype)) {
			String[] params = ctype.split(";");
			for (String param : params) {
				param = param.trim();
				if (param.startsWith("charset")) {
					String[] pair = param.split("=", 2);
					if (pair.length == 2) {
						if (!StringUtil.isEmpty(pair[1])) {
							charset = pair[1].trim();
						}
					}
					break;
				}
			}
		}

		return charset;
	}

	/**
	 * 使用默认的UTF-8字符集反编码请求参数值。
	 * 
	 * @param value 参数值
	 * @return 反编码后的参数值
	 */
	public static String decode(String value) {
		return decode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用默认的UTF-8字符集编码请求参数值。
	 * 
	 * @param value 参数值
	 * @return 编码后的参数值
	 */
	public static String encode(String value) {
		return encode(value, DEFAULT_CHARSET);
	}

	/**
	 * 使用指定的字符集反编码请求参数值。
	 * 
	 * @param value 参数值
	 * @param charset 字符集
	 * @return 反编码后的参数值
	 */
	public static String decode(String value, String charset) {
		String result = null;
		if (!StringUtil.isEmpty(value)) {
			try {
				result = URLDecoder.decode(value, charset);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	/**
	 * 使用指定的字符集编码请求参数值。
	 * 
	 * @param value 参数值
	 * @param charset 字符集
	 * @return 编码后的参数值
	 */
	public static String encode(String value, String charset) {
		String result = null;
		if (!StringUtil.isEmpty(value)) {
			try {
				result = URLEncoder.encode(value, charset);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	/**
	 * 从URL中提取所有的参数。
	 * 
	 * @param query URL地址
	 * @return 参数映射
	 */
	public static Map<String, String> splitUrlQuery(String query) {
		Map<String, String> result = new HashMap<String, String>();

		String[] pairs = query.split("&");
		if (pairs != null && pairs.length > 0) {
			for (String pair : pairs) {
				String[] param = pair.split("=", 2);
				if (param != null && param.length == 2) {
					result.put(param[0], param[1]);
				}
			}
		}

		return result;
	}
	
   /** 
    * @param url
    * @return
    */
   public static String getStringByHttps(String url) {
       String result = "";
       Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 8080);
       Protocol.registerProtocol("https", https);
       GetMethod get = new GetMethod(url);
       HttpClient client = new HttpClient();
       try {
           client.executeMethod(get);
           result = get.getResponseBodyAsString();
           result = new String(result.getBytes("ISO-8859-1"), "UTF-8");
           Protocol.unregisterProtocol("https");
           return result;
       } catch (HttpException e) {
           e.printStackTrace();
       } catch (IOException e) {
           e.printStackTrace();
       } catch(Exception e) {
           e.printStackTrace();
       }
        
       return "error";
   }
	
	public static String postByHttps(String url, Map<String, String> params, String query, int connectTimeout,int readTimeout,Map<String,String> headMap) throws UnsupportedEncodingException {
	    String result = "";
	    Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(),443);
	    Protocol.registerProtocol("https", https);
	    PostMethod post = new PostMethod(url);
	    try {
	        if (headMap != null && !headMap.isEmpty()) {
				for (Map.Entry<String, String> entry : headMap.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue();
					if (StringUtil.areNotEmpty(name, value)) {
						post.setRequestHeader(name, value);
					}
				}
			}
	        if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					String name = entry.getKey();
					String value = entry.getValue();
					if (StringUtil.areNotEmpty(name, value)) {
						post.setParameter(name, value);
						//post.setParameter(name, URLEncoder.encode(value, DEFAULT_CHARSET));
					}
				}
			}
	        HttpClient client = new HttpClient();
	        client.executeMethod(post);
	        //result = post.getResponseBodyAsStream();
//	        InputStream is = post.getResponseBody();
	        Header responseHeader = post.getResponseHeader("Content-Encoding");
	        if(responseHeader!=null && responseHeader.getValue()!=null){
	        	String encodingMethod = responseHeader.getValue();
	        	if("gzip".equals(encodingMethod)){
	    	        GZIPInputStream gzip = new GZIPInputStream(post.getResponseBodyAsStream());  
	    	        StringBuffer out = new StringBuffer();  
	    	        byte[] b = new byte[4096];  
	    	        for (int n; (n = gzip.read(b)) != -1;) {  
	    	            out.append(new String(b, 0, n));  
	    	        } 
	    	        gzip.close();
	    	        result = out.toString();
	        	}
//	        	}else if("deflate".equals(encodingMethod)){
//	        		 InflaterInputStream iis = new InflaterInputStream(post.getResponseBodyAsStream());  
//	        		  contentLength = (contentLength <= 0) ? 1024 : contentLength;  
//	        		 ByteArrayOutputStream bos = new ByteArrayOutputStream();  
//	        		 byte[] buffer = new byte[contentLength];  
//	        		 int read = iis.read(buffer);  
//	        		   
//	        		 while (-1 != read) {  
//	        		     bos.write(buffer, 0, read);  
//	        		     read = iis.read(buffer);  
//	        		 }  
//	        		   
//	        		 byte[] _bytes = (contentLength == 0) ? EMPTY_ARRAY : bos.toByteArray();  
//	        		 ByteArrayInputStream _is = new ByteArrayInputStream(_bytes);  
//	        		   
//	        		 StringBuffer out = new StringBuffer();  
//	        		 byte[] b = new byte[4096];  
//	        		 for (int n; (n = _is.read(b)) != -1;) {  
//	        		     out.append(new String(b, 0, n));  
//	        		 }  
	        		
//	        	}
	        }else{
		        BufferedReader rd = new BufferedReader(new InputStreamReader(post.getResponseBodyAsStream()));
		        String line;
		        StringBuffer response = new StringBuffer();
		        while((line = rd.readLine()) != null) {
		          response.append(line);
		          response.append('\r');
		        }
		        rd.close();
		        result = response.toString();
	        }
	        
	        Protocol.unregisterProtocol("https");
	        return result;
	    } catch (Exception e) {
	    	logger.info("https请求异常",e);
	    } 
	    return null;
	}
	
}
