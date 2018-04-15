/**
package com.example.common.util.request;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.net.ftp.FTPClient;

import sun.net.TelnetInputStream;
import sun.net.TelnetOutputStream;
import sun.net.ftp.FtpClient;

*//**
 * Java自带的API对FTP的操作
 * 
 * @Title:Ftp.java
 * @author: 周玲斌
 *//*
public class Ftp {
	*//**
	 * 本地文件名
	 *//*
	private String localfilename;
	*//**
	 * 远程文件名
	 *//*
	private String remotefilename;
	*//**
	 * FTP客户端
	 *//*
	private FtpClient ftpClient;

	*//**
	 * 服务器连接
	 * 
	 * @param ip
	 *            服务器IP
	 * @param port
	 *            服务器端口
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @param path
	 *            服务器路径
	 * @author 周玲斌
	 * @date 2012-7-11
	 *//*
	public void connectServer(String ip, int port, String user,
			String password, String path) {
		try {
			 ******连接服务器的两种方法****** 
			// 第一种方法
			// ftpClient = new FtpClient();
			// ftpClient.openServer(ip, port);
			// 第二种方法
			ftpClient = new FtpClient(ip);

			ftpClient.login(user, password);
			// 设置成2进制传输
			ftpClient.binary();
			System.out.println("login success!");
			if (path.length() != 0) {
				// 把远程系统上的目录切换到参数path所指定的目录
				ftpClient.cd(path);
			}
			ftpClient.binary();
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	*//**
	 * 关闭连接
	 * 
	 * @author 周玲斌
	 * @date 2012-7-11
	 *//*
	public void closeConnect() {
		try {
			ftpClient.closeServer();
			System.out.println("disconnect success");
		} catch (IOException ex) {
			System.out.println("not disconnect");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	*//**
	 * 上传文件
	 * 
	 * @param localFile
	 *            本地文件
	 * @param remoteFile
	 *            远程文件
	 * @author 周玲斌
	 * @date 2012-7-11
	 *//*
	public void upload(String localFile, String remoteFile) {
		this.localfilename = localFile;
		this.remotefilename = remoteFile;
		TelnetOutputStream os = null;
		FileInputStream is = null;
		try {
			// 将远程文件加入输出流中
			os = ftpClient.put(this.remotefilename);
			// 获取本地文件的输入流
			File file_in = new File(this.localfilename);
			is = new FileInputStream(file_in);
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void upload2(String xmlStr, String remoteFile) {
		//this.localfilename = localFile;
		this.remotefilename = remoteFile;
		TelnetOutputStream os = null;
		InputStream is = null;
		try {
			// 将远程文件加入输出流中
			os = ftpClient.put(this.remotefilename);
			// 获取本地文件的输入流
			//File file_in = new File(this.localfilename);
			is = new ByteArrayInputStream(xmlStr.getBytes("UTF-8"));
			// 创建一个缓冲区
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("upload success");
		} catch (IOException ex) {
			System.out.println("not upload");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	*//**
	 * 下载文件
	 * 
	 * @param remoteFile
	 *            远程文件路径(服务器端)
	 * @param localFile
	 *            本地文件路径(客户端)
	 * @author 周玲斌
	 * @date 2012-7-11
	 *//*
	public void download(String remoteFile, String localFile) {
		TelnetInputStream is = null;
		FileOutputStream os = null;
		try {
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
			is = ftpClient.get(remoteFile);
			File file_in = new File(localFile);
			os = new FileOutputStream(file_in);
			byte[] bytes = new byte[1024];
			int c;
			while ((c = is.read(bytes)) != -1) {
				os.write(bytes, 0, c);
			}
			System.out.println("download success");
		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (os != null) {
						os.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	public String download2(String remoteFile) {
		
		TelnetInputStream is = null;
		
		StringBuffer sb = new StringBuffer();
		try {
			// 获取远程机器上的文件filename，借助TelnetInputStream把该文件传送到本地。
			is = ftpClient.get(remoteFile);
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			String line = null;  
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println("download success");
			
		} catch (IOException ex) {
			System.out.println("not download");
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				
				return sb.toString();
			}
		}
	}
	
	

	public static void main(String agrs[]) {

		String filepath[] = { "/temp/aa.txt", "/temp/regist.log" };
		String localfilepath[] = { "C:\\tmp\\1.txt", "C:\\tmp\\2.log" };

		Ftp fu = new Ftp();
		
		 * 使用默认的端口号、用户名、密码以及根目录连接FTP服务器
		 
		fu.connectServer("127.0.0.1", 22, "anonymous", "IEUser@", "/temp");

		// 下载
		for (int i = 0; i < filepath.length; i++) {
			fu.download(filepath[i], localfilepath[i]);
		}

		String localfile = "E:\\号码.txt";
		String remotefile = "/temp/哈哈.txt";
		// 上传
		fu.upload(localfile, remotefile);
		fu.closeConnect();
	}
}
*/