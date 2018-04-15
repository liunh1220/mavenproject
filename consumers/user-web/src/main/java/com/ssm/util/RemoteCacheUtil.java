package com.ssm.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 远程缓存
 * 
 */
public class RemoteCacheUtil {

	private static final Logger logger = LoggerFactory.getLogger(RemoteCacheUtil.class);

	public static JedisPool jedisPool = (JedisPool) ServiceLocator.getInstance().getBeanFactory().getBean("jedisPool");

	public static Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	public static String getValue(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get("outside_"+key);
		returnResource(jedis);

		return value;
	}

	public static void setValue(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.set("outside_"+key, value);
		returnResource(jedis);
	}

	public static Object getObject(String key) {
		Jedis jedis = jedisPool.getResource();
		byte[] value = jedis.get(("outside_"+key).getBytes());
		if(value == null)
		{
			return null;
		}
		Object obj = SerializeUtil.deserialize(value);
		returnResource(jedis);
		return obj;
	}

	public static void setObject(String key, Object value) {
		Jedis jedis = jedisPool.getResource();
		if(value != null)
		{
			jedis.set(("outside_"+key).getBytes(), SerializeUtil.serialize(value));
		}
		returnResource(jedis);
	}

	public static void remove(String... keys) {
		Jedis jedis = jedisPool.getResource();
		jedis.del("outside_"+keys);
		returnResource(jedis);
	}
	
	public static void remove(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.del("outside_"+key);
		returnResource(jedis);
	}

	public static void returnResource(Jedis resource) {
		jedisPool.returnResource(resource);
	}

	public static String getMd5Key(String key) {
		if (key == null || "".equals(key)) {
			throw new RuntimeException("Key为null 或 空");
		}
		String md5Key = MD5Util.makeMd5Sum(key.getBytes());
		return md5Key;
	}

	static class SerializeUtil {
		public static byte[] serialize(Object value) {
			if (value == null) {
				throw new NullPointerException("Can't serialize null");
			}
			byte[] rv = null;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream os = null;
			try {
				bos = new ByteArrayOutputStream();
				os = new ObjectOutputStream(bos);
				os.writeObject(value);
				os.close();
				bos.close();
				rv = bos.toByteArray();
			} catch (IOException e) {
				throw new IllegalArgumentException("Non-serializable object", e);
			} finally {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return rv;
		}

		public static Object deserialize(byte[] in) {
			Object rv = null;
			ByteArrayInputStream bis = null;
			ObjectInputStream is = null;
			try {
				if (in != null) {
					bis = new ByteArrayInputStream(in);
					is = new ObjectInputStream(bis);
					rv = is.readObject();
					is.close();
					bis.close();
				}
			} catch (IOException e) {
				logger.warn("Caught IOException decoding %d bytes of data", e);
			} catch (ClassNotFoundException e) {
				logger.warn("Caught CNFE decoding %d bytes of data", e);
			} finally {
				try {
					if(null != is){
						is.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					if(null != bis){
						bis.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return rv;
		}
	}

}
