package com.ssm.base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ssm.util.Page;

public class BaseController {

	/**
	 * logger.
	 */
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	private static ThreadLocal<Map<String, Object>> outPutMsg = new ThreadLocal<Map<String, Object>>();

	/**
	 * error msg.
	 */
	private static final String ERROR_MSG = "msg";

	/**
	 * error stack.
	 */
	private static final String ERROR_STACK = "errorStack";

	/**
	 * 默认每页展示记录数.
	 */
	private static final int DEFAULT_PAGE_LIMIT = 9999;

	/**
	 * 编码
	 */
	private static final String ENCODING = "UTF-8";

	/**
	 */
	private String domain;

	private String ticket;

	/**
	 * action 成功 true|flase失败.
	 */
	private boolean success = true;

	/**
	 * actionִjson
	 */
	private Map<Object, Object> result = null;

	private Map<String, String> errorReason = new HashMap<String, String>();

	/**
	 * 起始.
	 */
	private int start = 0;

	private int limit = DEFAULT_PAGE_LIMIT;

	private int totalCount = 0;

	private String extInfo = "";

	private String sort;

	private String dir;

	private String exportExcelName;
	
	private Page page;

	public void addActionError(String anErrorMessage) {
		logger.error("ActionError=" + anErrorMessage);
	}

	public void addFieldError(String fieldName, String errorMessage) {
		logger.error("FieldError fieldName=" + errorMessage);
	}

	public String getExportExcelName() {
		return exportExcelName;
	}

	public void setExportExcelName(String exportExcelName) {
		this.exportExcelName = exportExcelName;
	}

	/**
	 * 
	 * @return http session
	 */
	public final HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	/**
	 * 
	 * @return http request
	 */
	public final HttpServletRequest getHttpServletRequest(HttpServletRequest request) {
		return request;
	}

	/**
	 * 
	 * @return http request
	 */
	public final HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession();
	}

	/**
	 * 
	 * 
	 * @return http response
	 */
	public final HttpServletResponse getHttpServletResponse(HttpServletResponse response) {
		return response;
	}

	public Map<String, String> getErrorReason() {
		return errorReason;
	}

	public void setErrorReason(Map<String, String> errorReason) {
		this.errorReason = errorReason;
	}

	public void setErrorReason(String errorMsg) {
		if (errorReason == null) {
			errorReason = new HashMap<String, String>();
		}

		// setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, "");
	}

	public void setErrorReason(String errorMsg, Exception e) {
		if (errorReason == null) {
			errorReason = new HashMap<String, String>();
		}

		// setSuccess(false);
		this.errorReason.put(ERROR_MSG, errorMsg);
		this.errorReason.put(ERROR_STACK, generateStackTrace(e));
	}

	public void setAlertMsg(String alertMsg) {
		this.setResult(ERROR_MSG, alertMsg);
	}

	public void setPagination(BaseDomain domain) {
		domain.setStart(getStart() + 1);
		domain.setEnd(getStart() + getLimit());
		domain.setSort(getSort());
		domain.setOrder(getDir());
	}

	private String generateStackTrace(Exception e) {
		if (e == null) {
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			byteArrayOutputStream = new ByteArrayOutputStream();
			e.printStackTrace(new PrintStream(byteArrayOutputStream));
			stringBuffer.append(byteArrayOutputStream.toString());
		} catch (Exception ex) {
		} finally {
			if (byteArrayOutputStream != null) {
				try {
					byteArrayOutputStream.close();
				} catch (IOException ex2) {
				}
			}
		}
		return stringBuffer.toString();
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public Map<Object, Object> getResult() {
		return result;
	}

	public void setResult(Map<Object, Object> result) {
		this.result = result;
	}

	public void setResult(Object key, Object value) {
		if (result == null) {
			result = new HashMap<Object, Object>();
		}
		result.put(key, value);
	}

	/**
	 * 将对象转化为JSON格式的字符串数组
	 * 
	 * @param object
	 */
	protected void toJsonArray(Object object, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			JSONArray jsonArray = JSONArray.fromObject(object);
			response.setCharacterEncoding(ENCODING);
			out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			logger.error("toJson", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 将对象转化为JSON格式的字符串对象
	 * 
	 * @param object
	 */
	protected void toJsonObject(Object object, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			JSONObject jsonArray = JSONObject.fromObject(object);
			response.setCharacterEncoding(ENCODING);
			out = response.getWriter();
			out.write(jsonArray.toString());
		} catch (Exception e) {
			logger.error("toJson", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 将JSON格式字符转返回
	 * 
	 * @param object
	 */
	protected void toJson(String str, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding(ENCODING);
			out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			logger.error("toJson", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 返回可执行的javascript代码
	 * 
	 * @param js
	 */
	protected void toJavascript(String js, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding(ENCODING);
			js = "<script>" + js + "</script>";
			out = response.getWriter();
			out.write(js);
		} catch (Exception e) {
			logger.error("toJavascript", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 提示并关闭窗口、刷新父窗口
	 * 
	 * @param msg 提示信息
	 */
	protected void alertAndCloseForward(String msg, HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			String js = "";
			response.setCharacterEncoding(ENCODING);
			response.setContentType("text/html; charset=UTF-8");
			js = "<script>alert('"+msg+"');" + 
				 "window.opener.location.reload();" +
				 "window.close(); " + 
				 "</script>";
			out = response.getWriter();
			out.write(js);
		} catch (Exception e) {
			logger.error("toJavascript", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 提示并关闭窗口、刷新父窗口
	 * 
	 * @param msg 提示信息
	 */
	protected void alertAndForward(String msg,String url, HttpServletResponse response)
	{
		PrintWriter out = null;
		try {
			String js = "";
			response.setCharacterEncoding(ENCODING);
			response.setContentType("text/html; charset=UTF-8");
			js = "<script>alert('"+msg+"');" + 
				 "window.location.href='" + url + "';" + 
				 "</script>";
			out = response.getWriter();
			out.write(js);
		} catch (Exception e) {
			logger.error("toJavascript", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	/**
	 * 返回字符串
	 * 
	 * @param str
	 */
	protected void responseText(String str,String charset, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding(charset);
			out = response.getWriter();
			out.write(str);
		} catch (Exception e) {
			logger.error("toJson", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket
	 *            the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getExtInfo() {
		return extInfo;
	}

	public void setExtInfo(String extInfo) {
		this.extInfo = extInfo;
	}
	
	public Page getPage() {
		if(page == null)
		{
			page = new Page();
		}
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public String subStr(String str,int index)
	{
		if(str != null)
		{
			if(index < str.length())
			{
				return str.substring(0, index) + "...";
			}
			else
			{
				return str;
			}
		}
		else
		{
			return "";
		}
	}
	public Map<String, Object> getOutputMsg() {
		Map<String, Object> output = outPutMsg.get();
		if (output == null) {
			output = new HashMap<String, Object>();
			outPutMsg.set(output);
		}
		return output;
	}

	protected void setOutputMsg(String key, String value) {
		getOutputMsg().put(key, value);
	}

	/**
	 * 输出，同时清空outPutMsg
	 * 
	 * @param response
	 * @param result
	 */
	public void outPrint(HttpServletResponse response, Object result) {
		PrintWriter out = null;
		try {
			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			out.print(result.toString());
			getOutputMsg().clear();
		} catch (IOException e) {
			logger.error("",e);
		} finally {
			out.close();
		}
	}
	
}
