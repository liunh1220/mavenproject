package com.example.common.util.json;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class JsonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	/**
	 * 页面传至后台时，json数据在request的参数名称
	 */
	public final static String JSON_ATTRIBUTE = "json";
	public final static String JSON_ATTRIBUTE1 = "json1";
	public final static String JSON_ATTRIBUTE2 = "json2";
	public final static String JSON_ATTRIBUTE3 = "json3";
	public final static String JSON_ATTRIBUTE4 = "json4";

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，形如：
	 * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA_test();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz);
	}

	/**
	 * 从一个JSON 对象字符格式中得到一个java对象，其中beansList是一类的集合，形如：
	 * {"id" : idValue, "name" : nameValue, "aBean" : {"aBeanId" : aBeanIdValue, ...},
	 * beansList:[{}, {}, ...]}
	 *
	 * @param jsonString
	 * @param clazz
	 * @param map        集合属性的类型 (key : 集合属性名, value : 集合属性类型class) eg: ("beansList" : Bean.class)
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz, Map map) {
		JSONObject jsonObject = null;
		try {
			setDataFormat2JAVA();
			jsonObject = JSONObject.fromObject(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toBean(jsonObject, clazz, map);
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如：
	 * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]
	 *
	 * @param jsonString
	 * @param clazz
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class clazz) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象数组，形如：
	 * [{"id" : idValue, "name" : nameValue}, {"id" : idValue, "name" : nameValue}, ...]
	 *
	 * @param clazz
	 * @param map
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		Object[] obj = new Object[array.size()];
		for (int i = 0; i < array.size(); i++) {
			JSONObject jsonObject = array.getJSONObject(i);
			obj[i] = JSONObject.toBean(jsonObject, clazz, map);
		}
		return obj;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合
	 *
	 * @param clazz
	 * @return
	 */
	public static List getDTOList(String jsonString, Class clazz) {
		setDataFormat2JAVA_test();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext(); ) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz));
		}
		return list;
	}

	/**
	 * 从一个JSON数组得到一个java对象集合，其中对象中包含有集合属性
	 *
	 * @param clazz
	 * @param map   集合属性的类型
	 * @return
	 */
	public static List getDTOList(String jsonString, Class clazz, Map map) {
		setDataFormat2JAVA();
		JSONArray array = JSONArray.fromObject(jsonString);
		List list = new ArrayList();
		for (Iterator iter = array.iterator(); iter.hasNext(); ) {
			JSONObject jsonObject = (JSONObject) iter.next();
			list.add(JSONObject.toBean(jsonObject, clazz, map));
		}
		return list;
	}

	/**
	 * 从json HASH表达式中获取一个map，该map支持嵌套功能
	 * 形如：{"id" : "johncon", "name" : "小强"}
	 * 注意commons-collections版本，必须包含org.apache.commons.collections.map.MultiKeyMap
	 *
	 * @return
	 */
	public static Map getMapFromJson(String jsonString) {
		setDataFormat2JAVA();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		Map map = new HashMap();
		for (Iterator iter = jsonObject.keys(); iter.hasNext(); ) {
			String key = (String) iter.next();
			map.put(key, jsonObject.get(key));
		}
		return map;
	}

	/**
	 * 从json数组中得到相应java数组
	 * json形如：["123", "456"]
	 *
	 * @param jsonString
	 * @return
	 */
	public static Object[] getObjectArrayFromJson(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return jsonArray.toArray();
	}


	/**
	 * 把数据对象转换成json字符串
	 * DTO对象形如：{"id" : idValue, "name" : nameValue, ...}
	 * 数组对象形如：[{}, {}, {}, ...]
	 * map对象形如：{key1 : {"id" : idValue, "name" : nameValue, ...}, key2 : {}, ...}
	 *
	 * @param object
	 * @return
	 */
	public static String getJSONString(Object object) {
		String jsonString = null;
		//日期值处理器
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		if (object != null) {
			if (object instanceof Collection || object instanceof Object[]) {
				jsonString = JSONArray.fromObject(object, jsonConfig).toString();
			} else {
				jsonString = JSONObject.fromObject(object, jsonConfig).toString();
			}
		}
		return jsonString == null ? "{}" : jsonString;
	}

	private static void setDataFormat2JAVA() {
		//设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"}));
	}

	//dzm 20150529
	private static void setDataFormat2JAVA_test() {
		//设定日期转换格式
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[]{"yyyy-MM-dd HH:mm:ss"}));
	}

	/**
	 * @param object 任意对象
	 * @return java.lang.String
	 */
	public static String objectToJson(Object object) {
		StringBuilder json = new StringBuilder();
		if (object == null) {
			json.append("\"\"");
		} else if (object instanceof String || object instanceof Integer) {
			json.append("\"").append(object.toString()).append("\"");
		} else {
			setDataFormat2JAVA_test();
			json.append(JSONObject.fromObject(object));
		}
		return json.toString();
	}

	/**
	 * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串
	 *
	 * @param bean bean对象
	 * @return String
	 */
	/**public static String beanToJson(Object bean) {
	 StringBuilder json = new StringBuilder();
	 json.append("{");
	 PropertyDescriptor[] props = null;
	 try {
	 props = Introspector.getBeanInfo(bean.getClass(), Object.class)
	 .getPropertyDescriptors();
	 } catch (IntrospectionException e) {
	 }
	 if (props != null) {
	 for (int i = 0; i < props.length; i++) {
	 try {
	 String name = objectToJson(props[i].getName());
	 String value = objectToJson(props[i].getReadMethod().invoke(bean));
	 json.append(name);
	 json.append(":");
	 json.append(value);
	 json.append(",");
	 } catch (Exception e) {
	 }
	 }
	 json.setCharAt(json.length() - 1, '}');
	 } else {
	 json.append("}");
	 }
	 return json.toString();
	 }*/

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 *
	 * @param list 列表对象
	 * @return java.lang.String
	 */
	public static String listToJson(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(objectToJson(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	/**
	 * @param jsonString
	 * @param key
	 * @return
	 */
	public static String getJsonString(String jsonString, String key) {
		String value = "";
		try {
			JSONObject jsonObject = JSONObject.fromObject(jsonString);
			value = jsonObject.getString(key);
		} catch (JSONException e) {

		}
		return value;

	}

	public static void main(String[] arg) throws Exception {
		/*String ss = "{'trades_sold_increment_get_response':{'trades':{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]},'total_results':'6'}}";
		String thre = ss.substring(ss.indexOf(":") + 1, ss.length() - 1);
		thre = thre.replace("'trades':{", "");
		thre = thre.replace("]}", "]");

		System.out.println(thre);*/

		//String s2 = "{'trades':{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]},'total_results':'6'}";
		//String s1  = "{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}],'total_results':'6'}";
		// String s  =  "{'total_results':'6','trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]}";
		// YitaiOrder order =  (YitaiOrder) JsonUtil.getDTO(s, YitaiOrder.class);
		//YitaiOrder order =  (YitaiOrder) JsonUtil.getDTO(s1, YitaiOrder.class);
		// List list = JsonUtil.getDTOList(s, YitaiOrder.class);
		//String s = "{'buyer_obtain_point_fee':'0','inv_payee':'','buyer_memo':'','iid':'','receiver_zip':'100124','buyer_message':'','receiver_address':'北京','seller_memo':'','buyer_rate':'false','receiver_phone':'','trade_memo':'','pay_status':'Y','buyer_email':'','created':'2012-11-16 12:41:44','buyer_alipay_no':'Y','has_post_fee':'true','inv_content':'','tid':'20121116277367','receiver_state':'北京市','seller_nick':'银泰','discount_fee':'0.00','payment':'43.0000','orderType':null,'status':'WAIT_BUYER_CONFIRM_GOODS','receiver_city':'北京市','shipping_type':'','cod_fee':'0.00','cod_status':'1007','pay_time':'2012-11-16 12:49:24','post_fee':'15.00','receiver_mobile':'13000000000','receiver_name':'测试','modified':'2012-11-16 13:02:06','buyer_nick':'ddl@yintai.com','receiver_district':'崇文区','num':'1','end_time':'','order':[{'adjust_fee':'0.00','buyer_rate':'false','num': 1,'payment': '55.00'},{'adjust_fee':'0.00','buyer_rate':'false','num': 1,'payment': '55.00'}],'sourceNo':null}";
		//System.out.println(" object : " + JsonUtil.getJSONString(s));


		/*User u = new User();
		u.setName("aaaa");
		u.setCreateTime(new Date());
		Object object = new Object();
		object = u;
		StringBuilder json = new StringBuilder();
		if (object == null) {
			json.append("\"\"");
		} else if (object instanceof String || object instanceof Integer) {
			json.append("\"").append(object.toString()).append("\"");
		} else {
			setDataFormat2JAVA_test();
			json.append(JSONObject.fromObject(object));
		}
		System.out.println(" object : " + json.toString());*/
	}
}   
