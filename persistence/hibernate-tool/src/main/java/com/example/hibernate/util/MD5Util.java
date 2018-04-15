package com.example.hibernate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	/**
	 * 生成md5校验码
	 * @param srcContent 需要加密的数据
	 * @return 加密后的md5校验码。出错则返回null。
	 */
	public static String makeMd5Sum(byte[] srcContent)
	{
		if (srcContent == null)
		{
			return null;
		}

		String strDes = null;

		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(srcContent);
			strDes = bytes2Hex(md5.digest()); // to HexString
		}
		catch (NoSuchAlgorithmException e)
		{
			return null;
		}
		return strDes;
	}

	private static String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
		{
			if(byteArray[i] >= 0 && byteArray[i] < 16)
			{
				strBuf.append("0");
			}
			strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
		}
		return strBuf.toString();
	}
	
	public static void main(String[] args) throws IOException {
//		long begin = System.currentTimeMillis();
//
//		File big = new File("C:/Documents and Settings/Administrator/桌面/apache-tomcat-6.0.33.tar.gz");
//
//		String md5 = getFileMD5String(big);
//
//		long end = System.currentTimeMillis();
//		System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000)
//				+ "s");
		System.out.println(makeMd5Sum("configuration:taobao.fenxiao.order.prefix".getBytes()));
	}

	/**
	 * 适用于上G大的文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0,
				file.length());
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(byteBuffer);
		return bufferToHex(messagedigest.digest());
	}

	public static String getMD5String(String s) throws NoSuchAlgorithmException {
		String result = null;
		try {
			result = getMD5String(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getMD5String(byte[] bytes) throws NoSuchAlgorithmException {
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(bytes);
		return bufferToHex(messagedigest.digest());
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		/**
		 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
		 */
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6','7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) throws NoSuchAlgorithmException {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}
	
	
	/**
	  * Consume the stream and return its Base-64 encoded MD5 checksum.
	  * 亚马逊使用
	  */
	public static String computeContentMD5Header(InputStream inputStream) {
		// Consume the stream to compute the MD5 as a side effect.
		DigestInputStream s;
		try {
			s = new DigestInputStream(inputStream, MessageDigest.getInstance("MD5"));
			// drain the buffer, as the digest is computed as a side-effect
			byte[] buffer = new byte[8192];
			while (s.read(buffer) > 0);
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(s.getMessageDigest().digest()), "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 请求KEY生成
	 * @param jsonStr
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String produceSecretKey(String jsonStr,String key) throws NoSuchAlgorithmException{
		//"y2@D3kN&4jk@#8QuTyb341vb@tR";
		String mdKey = MD5Util.getMD5String(key+jsonStr);
		return mdKey.substring((mdKey.length()-5), mdKey.length())+mdKey.substring(5, (mdKey.length()-5))+mdKey.substring(0, 5);
	}
}