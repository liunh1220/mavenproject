package com.example.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.DigestInputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.hash.Hashing;

/**
 * 加密工具类
 *
 */
public class EncryptUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptUtil.class);

	private static final byte[] ROW_BYTES = "80e36e39f34e678c".getBytes();

	private static SecretKeySpec keySpec = new SecretKeySpec(ROW_BYTES, "AES");

	public static String md5(String input) {
		return DigestUtils.md5Hex(input.getBytes());
	}

	public static String md5(String input, String key) {
		return DigestUtils.md5Hex(new StringBuilder().append(input).append(key).toString().getBytes());
	}

	public static String sha(String input) {
		return DigestUtils.sha1Hex(input.getBytes());
	}

	public static String sha256(String input) {
		return DigestUtils.sha256Hex(input.getBytes());
	}

	public static String sha384(String input) {
		return DigestUtils.sha384Hex(input.getBytes());
	}

	public static String sha512(String input) {
		return DigestUtils.sha512Hex(input.getBytes());
	}

	public static String base64Encode(String input) {
		return Base64.encodeBase64String(input.getBytes()).trim();
	}

	public static String base64Decode(String input) {
		return new String(Base64.decodeBase64(input));
	}

	public static String encode(String input) {
		long millis = System.currentTimeMillis();
		input = base64Encode(new StringBuilder().append("hello").append(input).append(millis).toString());
		byte[] bytes = input.getBytes();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; ++i) {
			String s = Integer.toHexString(bytes[i] + 18);
			sb.append(new StringBuilder().append(s.charAt(1)).append("").append(s.charAt(0)).toString());
		}
		return reverse(sb.toString());
	}

	private static String reverse(String input) {
		byte[] bytes1 = input.getBytes();
		byte[] bytes2 = new byte[bytes1.length];
		for (int i = 0; i < bytes1.length; ++i)
			bytes2[i] = bytes1[(bytes1.length - 1 - i)];
		byte byte0 = bytes2[0];
		byte byte1 = bytes2[1];
		bytes2[0] = bytes2[(bytes2.length - 1)];
		bytes2[1] = bytes2[(bytes2.length - 2)];
		bytes2[(bytes2.length - 1)] = byte0;
		bytes2[(bytes2.length - 2)] = byte1;
		return new String(bytes2);
	}

	public static String[] decode(String input) {
		try {
			if (input.length() % 2 != 0) {
				return null;
			}
			input = reverse(input);
			byte[] bytes = new byte[input.length() / 2];
			for (int i = 0; i <= input.length() - 2; i += 2) {
				String hexString = new StringBuilder().append(input.substring(i + 1, i + 2))
						.append(input.substring(i, i + 1)).toString();
				if (!(hexString.matches("[0-9a-fA-F]+")))
					return null;
				bytes[(i / 2)] = (byte) (Integer.parseInt(hexString, 16) - 18);
			}
			String s = new String(bytes);

			s = base64Decode(s);
			String s1 = s.substring(0, "hello".length());
			String s3 = s.substring(s.length() - 13);
			if ((!(s1.equals("hello"))) || (!(s3.matches("\\d+"))))
				return null;
			return new String[] { s.substring("hello".length(), s.length() - 13), s3 };
		} catch (Exception e) {
		}
		return null;
	}

	public static String aesEencode(String content) throws Exception {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(1, keySpec);
		return Base64.encodeBase64String(cipher.doFinal(content.getBytes()));
	}

	public static String aesDecode(String content) throws Exception {
		if (StringUtils.isEmpty(content)) {
			return content;
		}
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(2, keySpec);
		return new String(cipher.doFinal(Base64.decodeBase64(content)));
	}

	/** ************************************************************** */

	/**
	 * 获取AES加密的工具
	 *
	 */
	public static Cipher AES(byte[] keys, int model) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(model, new SecretKeySpec(keys, "AES"));
			return cipher;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
			logger.error("AES加密工具初始化失败", e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 */
	public static String bytes2Hex(byte bytes[]) {
		StringBuilder sb = new StringBuilder();
		String temp;
		for (byte b : bytes) {
			// 将每个字节与0xFF进行与运算，然后转化为10进制，然后借助于Integer再转化为16进制
			temp = Integer.toHexString(0xFF & b);
			// 每个字节8为，转为16进制标志，2个16进制位
			if (temp.length() == 1) {
				temp = "0" + temp;
			}
			sb.append(temp);
		}
		return sb.toString();
	}

	/**
	 * 十六进制转二进制字符串
	 *
	 */
	public static byte[] hex2Bytes(String hexStr) {
		if (hexStr.length() < 1) {
			return null;
		}
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 进行MD5加密
	 *
	 */
	public static String passwordMd5(String password) {
		try {
			return Hashing.goodFastHash(32).hashString(password, Charset.forName("utf-8")).toString().toUpperCase();
		} catch (Exception e) {
			logger.error("进行MD5加密发生异常.", e);
		}
		return null;
	}

	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/** ********************************************************************* */

	/**
	 * 生成md5校验码
	 * 
	 * @param srcContent
	 *            需要加密的数据
	 * @return 加密后的md5校验码。出错则返回null。
	 */
	public static String makeMd5Sum(byte[] srcContent) {
		if (srcContent == null)
			return null;
		String strDes = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(srcContent);
			strDes = bytes2Hex(md5.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return strDes;
	}

	/**
	 * 适用于上G大的文件
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("resource")
	public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
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
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
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
	 * Consume the stream and return its Base-64 encoded MD5 checksum. 亚马逊使用
	 */
	public static String computeContentMD5Header(InputStream inputStream) {
		// Consume the stream to compute the MD5 as a side effect.
		DigestInputStream s;
		try {
			s = new DigestInputStream(inputStream, MessageDigest.getInstance("MD5"));
			// drain the buffer, as the digest is computed as a side-effect
			byte[] buffer = new byte[8192];
			while (s.read(buffer) > 0)
				;
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(s.getMessageDigest().digest()),
					"UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 请求KEY生成
	 * 
	 * @param jsonStr
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String produceSecretKey(String jsonStr, String key) throws NoSuchAlgorithmException {
		String mdKey = EncryptUtil.getMD5String(key + jsonStr);
		return mdKey.substring((mdKey.length() - 5), mdKey.length()) + mdKey.substring(5, (mdKey.length() - 5))
				+ mdKey.substring(0, 5);
	}

	public static void main(String[] args) {
		System.out.println(new Date(9999999999999L));
		System.out.println(base64Decode("Ac/6L9OYytU2H1drQ9KIGy9sjP236g=="));
		System.out.println(encode("1"));
		System.out.println(decode(
				"c64f89608a677c5f465f56608a7d565f8c63665f858a6976614b5968425c6a6c444744747c437c75887e7f767a7a696c556869748264686c42585964885c6a6c486c656c1c1f435c5a764b67596c886069738768686c8c684562426b5a758b53455e44538c5f8c8156608a46555f87797c608a467b5f47578c5e888156748a606a7786828c6b7b647f738047696c867e59f4")[0]);
	}
}