package com.example.common.util.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JacksonUtil {

	private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

	/**
	 * 页面传至后台时，json数据在request的参数名称
	 */
	public final static String JSON_ATTRIBUTE = "json";
	public final static String JSON_ATTRIBUTE1 = "json1";
	public final static String JSON_ATTRIBUTE2 = "json2";
	public final static String JSON_ATTRIBUTE3 = "json3";
	public final static String JSON_ATTRIBUTE4 = "json4";

	private final static ObjectMapper mapper = new ObjectMapper();

	static {
		// 美化输出
		//mapper.enable(SerializationFeature.INDENT_OUTPUT);
		// 设置时间格式
		//mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		// bean转json时，不转null字段
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 当发现不存在的字段时不报错
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	private JacksonUtil() {
	}

	/**
	 * 将json string反序列化成非集合对象
	 *
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T jsonToBean(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("将json字符串：{}转换成{}时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为list对象
	 *
	 * @param json
	 * @param clazz 泛型类型
	 * @return
	 */
	public static <T> List<T> jsonToList(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(ArrayList.class, clazz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("将json数组:{}转换成List<{}>时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为array对象
	 *
	 * @param json
	 * @param clazz 数组类型
	 * @return
	 */
	public static <T> T[] jsonToArray(String json, Class<T[]> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			logger.error("将json数组: {} 转换成{}数组对象时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将json array反序列化为map对象
	 *
	 * @param json
	 * @param clazz value类型
	 * @return
	 */
	public static <T> Map<String, T> jsonToMap(String json, Class<T> clazz) {
		JavaType javaType = getCollectionType(Map.class, String.class, clazz);
		try {
			return mapper.readValue(json, javaType);
		} catch (IOException e) {
			logger.error("将json字符串: {} 转换成Map<String,{}>时发生异常。", json, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 将对象序列化为json字符串
	 *
	 * @param obj
	 * @return
	 */
	public static String beanToJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error("将对象：{} 转换成json时发生异常", obj, e);
		}
		return null;
	}

	/**
	 * 将json字符串转换为jsonNode对象
	 *
	 * @param json
	 * @return
	 */
	public static JsonNode jsonToNode(String json) {
		try {
			return mapper.readTree(json);
		} catch (IOException e) {
			logger.error("将json字符串：{} 转换成jsonNode发生异常。", json, e);
		}
		return null;
	}

	/**
	 * 将jsonNode字符串转换为JavaBean对象
	 *
	 * @param node
	 * @return
	 */
	public static <T> T nodeToBean(JsonNode node, Class<T> clazz) {
		try {
			return mapper.treeToValue(node, clazz);
		} catch (IOException e) {
			logger.error("JsonNode：{}转换成{}时发生异常。", node, String.valueOf(clazz), e);
		}
		return null;
	}

	/**
	 * 获取collection带泛型的对象
	 *
	 * @param collectionClass
	 * @param elementClasses  不定参，按顺序输入泛型类型
	 * @return
	 */
	public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
	}



	public static void main(String[] arg) throws Exception {
		String ss = "{'trades_sold_increment_get_response':{'trades':{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]},'total_results':'6'}}";
		String thre = ss.substring(ss.indexOf(":") + 1, ss.length() - 1);
		thre = thre.replace("'trades':{", "");
		thre = thre.replace("]}", "]");

		System.out.println(thre);

		//String s2 = "{'trades':{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]},'total_results':'6'}";
		//String s1  = "{'trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}],'total_results':'6'}";
		// String s  =  "{'total_results':'6','trade':[{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116277367','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:02:06','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116739568','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:20:04','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116397850','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 14:02:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116335736','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 13:55:59','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116731190','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:23:08','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'},{'buyer_obtain_point_fee':'0','adjust_fee':'0.00','type':'fixed','buyer_rate':'false','title':'','created':'','total_fee':'0.00','tid':'20121116757139','seller_nick':'','sid':'','discount_fee':'0.00','payment':'','status':'WAIT_BUYER_CONFIRM_GOODS','shipping_type':'express','cod_fee':'0.00','received_payment':'0.00','post_fee':'0.00','modified':'2012-11-16 16:47:12','buyer_nick':'','seller_rate':'false','point_fee':'0','num':'1','price':'0.00','end_time':'','real_point_fee':'0'}]}";
		// YitaiOrder order =  (YitaiOrder) JsonUtil.getDTO(s, YitaiOrder.class);
		//YitaiOrder order =  (YitaiOrder) JsonUtil.getDTO(s1, YitaiOrder.class);
		// List list = JsonUtil.getDTOList(s, YitaiOrder.class);
		String s = "{'buyer_obtain_point_fee':'0','inv_payee':'','buyer_memo':'','iid':'','receiver_zip':'100124','buyer_message':'','receiver_address':'北京','seller_memo':'','buyer_rate':'false','receiver_phone':'','trade_memo':'','pay_status':'Y','buyer_email':'','created':'2012-11-16 12:41:44','buyer_alipay_no':'Y','has_post_fee':'true','inv_content':'','tid':'20121116277367','receiver_state':'北京市','seller_nick':'银泰','discount_fee':'0.00','payment':'43.0000','orderType':null,'status':'WAIT_BUYER_CONFIRM_GOODS','receiver_city':'北京市','shipping_type':'','cod_fee':'0.00','cod_status':'1007','pay_time':'2012-11-16 12:49:24','post_fee':'15.00','receiver_mobile':'13000000000','receiver_name':'测试','modified':'2012-11-16 13:02:06','buyer_nick':'ddl@yintai.com','receiver_district':'崇文区','num':'1','end_time':'','order':[{'adjust_fee':'0.00','buyer_rate':'false','num': 1,'payment': '55.00'},{'adjust_fee':'0.00','buyer_rate':'false','num': 1,'payment': '55.00'}],'sourceNo':null}";
		//System.out.println(" object : " + JsonUtil.getJSONString(s));
	}
}   
