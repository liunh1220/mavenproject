package com.example.http.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;


public class DateUtil {

	public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式转换为字符串格式
	 * 
	 * @param date
	 *            时间
	 * @param format
	 *            格式 如("yyyy-MM-dd hh:mm:ss")
	 * @return String
	 */
	public static String DateToString(Date date, String format) {
		if (date == null) {
			// 默认值
			date = new Date();
		}
		if (StringUtils.isEmpty(format)) {
			// 默认值
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 字符串格式转换为时间格式
	 * 
	 * @param dateStr
	 *            字符串
	 * @param format
	 *            格式 如("yyyy-MM-dd HH:mm:ss")
	 * @return Date
	 */
	public static Date StringToDate(String dateStr, String format) {
		if (StringUtils.isEmpty(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
			if (date.getTime() > System.currentTimeMillis()) {
				date = new Date();
			}
		} catch (ParseException e1) {
			return null;
		}
		return date;
	}

	/**
	* 格式化日期为指定格式
	*
	* @param date 需要格式化的日期
	* @param pattern 目标格式字符串
	* @return String 格式化的日期
	*/
	public static String formatDate(Date date, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(date);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 解析日期时间字符串，得到Date对象
	 *
	 * @param date 日期时间字符串
	 * @param pattern 格式
	 * @return Date 格式化的日期
	 */
	public static Date parseDate(String date, String pattern) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.parse(date);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * 获取当前日期
	 * 
	 * @param pattern
	 *            格式如:yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate(String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		return sdf.format(new Date());
	}

	/**
	 * get cuurent Date return java.util.Date type
	 */
	public static Date getNowUtilDate() {
		return new Date();
	}

	/**
	 * get current Date return java.sql.Date type
	 * 
	 * @return
	 */
	public static java.sql.Date getNowSqlDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	/**
	 * get the current timestamp return java.sql.Timestamp
	 * 
	 * @return
	 */
	public static Timestamp getNowTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * 判断日期是否有效
	 * 
	 * @param strDateTime
	 * @return
	 */
	@SuppressWarnings("unused")
	public static boolean checkDateFormatAndValite(String strDateTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date ndate = format.parse(strDateTime);
			// String str = format.format(ndate);
			// success
			// if (str.equals(strDateTime))
			// return false;
			// //datetime is not validate
			// else
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * time1>time2 返回正数
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static long getOffsetBetweenTimes(String time1, String time2) {
		return StringToDate(time1, DEFAULT_FORMAT).getTime() - StringToDate(time2, DEFAULT_FORMAT).getTime();
	}

	/**
	 * 对指定日期滚动指定天数,负数时,则往前滚,正数时,则往后滚
	 * 
	 * @param date
	 *            Date
	 * @param days
	 *            int
	 * @return String
	 */
	public static String rollDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return DateUtil.DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 对指定日期滚动指定天数,负数时,则往前滚,正数时,则往后滚
	 * @param date
	 * @param days
	 * @param parttern 返回时间格式
	 * @return
	 */
	public static String rollDays(Date date, int days, String parttern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		if (StringUtils.isBlank(parttern)) {
			parttern = "yyyy-MM-dd HH:mm:ss";
		}
		return DateUtil.DateToString(calendar.getTime(), parttern);
	}

	/**
	 * 对指定日期滚动指定分钟,负数时,则往前滚,正数时,则往后滚
	 * 
	 * @param date
	 *            Date
	 * @param days
	 *            int
	 * @return String
	 */
	public static String rollMinutes(Date date, int minutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return DateUtil.DateToString(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @param date
	 * @param minutes
	 * @param parttern 返回日期格式
	 * @return
	 */
	public static String rollMinutes(Date date, int minutes, String parttern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		if (StringUtils.isBlank(parttern)) {
			parttern = "yyyy-MM-dd HH:mm:ss";
		}
		return DateUtil.DateToString(calendar.getTime(), parttern);
	}

	/**
	 * 对指定日期滚动指定分钟,负数时,则往前滚,正数时,则往后滚
	 * 
	 * @param date
	 *            Date
	 * @param days
	 *            int
	 * @return String
	 */
	public static String rollSeconds(Date date, int seconds, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return DateUtil.DateToString(calendar.getTime(), format);
	}

	/**
	 * 返回 2013-01-16T06:24:26.829Z 时间
	 * 
	 * @param date
	 * @return
	 * @throws Exception
	 */
	public static XMLGregorianCalendar getXmlDatetime(Date date) throws Exception {
		if (null == date) {
			date = new Date();
		}
		GregorianCalendar nowGregorianCalendar = new GregorianCalendar();
		XMLGregorianCalendar xmlDatetime = DatatypeFactory.newInstance().newXMLGregorianCalendar(nowGregorianCalendar);
		// XMLGregorianCalendar ->GregorianCalendar
		nowGregorianCalendar = xmlDatetime.toGregorianCalendar();
		nowGregorianCalendar.setTime(date);
		return xmlDatetime;
	}

	public static boolean checkDateBettwenBoth(Date checkDate, Date date1, Date date2) {
		boolean temp = false;
		if (checkDate == null || date1 == null || date2 == null) {
			temp = false;
			return temp;
		}
		if (checkDate.equals(date1) || checkDate.equals(date2)) {
			temp = true;
		}
		if (checkDate.after(date1) && checkDate.before(date2)) {
			temp = true;
		}
		return temp;
	}

	/**
	 * 检查指定日期距离当前日期是否超出范畴
	 * 
	 * @param checkDate
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static boolean checkHistoricalDate(Date checkDate, int month) {
		// 当前时间
		Date nowdate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(nowdate);
		calendar.add(calendar.MONTH, -(month));
		// 往前推一个月
		Date histDate = calendar.getTime();
		// 检查修改时间是否在指定时间之前
		if (checkDate.before(histDate)) {
			return true;
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		/*Calendar cendar = Calendar.getInstance();
		cendar.setTime(new Date());
		Date date = cendar.getTime();
		long et_query_time = date.getTime() / 1000;
		System.out.println(et_query_time);*/
		//1498116005,"et_query_time":1498635005
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		long temp =  1498116605l *1000;
		//System.out.println(temp);
		Date tempdate = new Date(temp);
		System.out.println(tempdate);
		System.out.println(simpleDateFormat.format(tempdate));
		
		long temp1 =  1498635605l *1000;
		//System.out.println(temp1);
		Date tempdate1 = new Date(temp1);
		System.out.println(tempdate1);
		System.out.println(simpleDateFormat.format(tempdate1));
		
	}
}
