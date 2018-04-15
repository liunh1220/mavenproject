package com.example.common.util.request;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IPUtil类是获取真实IP地址配置工具类
 * 
 */
public class IPUtil {

	private static final Logger logger = LoggerFactory.getLogger(IPUtil.class);
	private static final Pattern IP_PATTERN = Pattern
			.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

	private static List<String> realIpList = null;
	
	/**
	 * 通过网络接口获取当前系统设置的真实IP地址
	 * 
	 * @return List<String> 系统设置的真实IP地址列表
	 */
	public static List<String> getRealIpList() {
		
		if (realIpList != null) {
			return realIpList;
		}
		
		realIpList = new ArrayList<String>();
		
		NetworkInterface iface = null;

		try {
			for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces
					.hasMoreElements();) {
				iface = (NetworkInterface) ifaces.nextElement();
				logger.debug("Interface:" + iface.toString());
				InetAddress ia = null;
				for (Enumeration ips = iface.getInetAddresses(); ips
						.hasMoreElements();) {
					ia = (InetAddress) ips.nextElement();

					String str = ia.getHostAddress();
					if (str != null && !str.equals("127.0.0.1")
							&& validateAnIpAddress(str)) {
						realIpList.add(str);
						logger.debug("getRealIpList#" + realIpList.size() + "=" + str);
					}
				}
			}
		} catch (SocketException e1) {
			logger.error(e1.getMessage(), e1);
		}
		logger.info("getRealIpList=" + StringUtils.join(realIpList, ","));

		return realIpList;
	}
	
	/**
	 * 刷新并通过网络接口获取当前系统设置的真实IP地址
	 * 
	 * @return List<String> 系统设置的真实IP地址列表
	 */
	public static List<String> refreshRealIpList() {
		realIpList = null;
		
		return getRealIpList();
	}

	/**
	 * 通过表达式验证有效的真实的IP地址
	 * 
	 * @param IPAddress
	 * @return true | false
	 */
	private static boolean validateAnIpAddress(String IPAddress) {

		return IP_PATTERN.matcher(IPAddress).matches();
	}
	
	/**
	 * 是否本机IP
	 * @param ip
	 * @return
	 */
	public static boolean isLocalIp(String ip)
	{
		List<String> localIps = getRealIpList();
		for (String localIp : localIps) {
			if(localIp.equals(ip))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否本机IP
	 * @param ip
	 * @return
	 */
	public static boolean isLocalIp(List<Object> ipList)
	{
		List<String> localIps = getRealIpList();
		
	//	logger.info("localIps = " + StringUtils.join(localIps, ","));
		
		for (String localIp : localIps) {
			for (Object ip : ipList) {
				String ipStr = (String) ip;
				if(localIp.equals(ipStr))
				{
					return true;
				}
			}
		}
		return false;
	}

}
