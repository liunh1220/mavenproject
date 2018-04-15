package com.example.common.util.request;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpUtil {

	public static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);

    private static String userName;         //FTP 登录用户名 
    private static String password;         //FTP 登录密码 
    private static String ip;                     //FTP 服务器地址IP地址 
    private static int port;                        //FTP 端口 
    private static Properties property = null;    //属性集 
    private static String configFile = "E:\\test\\comftp\\ftpconfig.properties";    //配置文件的路径名 
    private static FTPClient ftpClient = null; //FTP 客户端代理 
    //时间格式化 
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm"); 
    private static String encoding = System.getProperty("file.encoding");
    //FTP状态码 
    public static int i = 1; 
    private static int timeout = 30;
    
    /** 
     * 上传单个文件，并重命名 
     * 
     * @param localFile--本地文件路径 
     * @param distFolder--新的文件名,可以命名为空"" 
     * @return true 上传成功，false 上传失败 
     */ 
    public static boolean uploadFile(File localFile, final File localRootFile, final String distFolder) { 
            boolean flag = true; 
            try { 
                    connectServer(); 
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
                    ftpClient.enterLocalPassiveMode(); 
                    ftpClient.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); 
                    InputStream input = new FileInputStream(localFile); 
//                    if (input == null) { 
//                            System.out.println("本地文件"+localFile.getPath()+"不存在!"); 
//                    } 
//                    if (newFileName.trim().equals("")) { 
//                            newFileName = localFile.getName(); 
//                    } 

                    String furi1 = localFile.getParentFile().getAbsoluteFile().toURI().toString(); 
                    String furi2 = localRootFile.getParentFile().getAbsoluteFile().toURI().toString(); 

                    String objFolder = distFolder + File.separator + furi1.substring(furi2.length()); 

                    ftpClient.changeWorkingDirectory("/"); 
                    ftpClient.makeDirectory(objFolder); 
                    System.out.println("a>>>>>>> : " + distFolder + File.separator + localFile.getParent());
                    System.out.println("x>>>>>>> : " + objFolder); 
                    ftpClient.changeWorkingDirectory(objFolder); 

                    System.out.println("b>>>>>>> : " + localFile.getPath() + " " + ftpClient.printWorkingDirectory()); 
                    flag = ftpClient.storeFile(localFile.getName(), input); 
                    if (flag) { 
                            System.out.println("上传文件成功！"); 
                    } else { 
                            System.out.println("上传文件失败！"); 
                    } 
                    input.close(); 
            } catch (IOException e) { 
                    e.printStackTrace(); 
                    logger.debug("本地文件上传失败！", e); 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
            return flag; 
    } 

    /** 
     * 上传多个文件 
     * 
     * @param localFile--本地文件夹路径 
     * @return true 上传成功，false 上传失败 
     */ 
    public static String uploadManyFile(String localFile) { 
            boolean flag = true; 
            StringBuffer strBuf = new StringBuffer(); 
            try { 
                    connectServer(); 
                    File file = new File(localFile);        // 在此目录中找文件 
                    File fileList[] = file.listFiles(); 
                    for (File f : fileList) { 
                            if (f.isDirectory()) {            // 文件夹中还有文件夹 
                                    uploadManyFile(f.getAbsolutePath()); 
                            } else { 
                            } 
                            if (!flag) { 
                                    strBuf.append(f.getName() + "\r\n"); 
                            } 
                    } 
                    System.out.println(strBuf.toString()); 
            } catch (NullPointerException e) { 
                    e.printStackTrace(); 
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    logger.debug("本地文件上传失败！", e); 
            } 
            return strBuf.toString(); 
    } 

    /** 
     * 上传多个文件 
     * 
     * @param localFile,--本地文件夹路径 
     * @param distFolder--目标路径 
     * @return true 上传成功，false 上传失败 
     */ 
    public static String uploadManyFile(File localFile, final File localRootFile, final String distFolder) { 
            System.out.println("-------------------------"); 
            boolean flag = true; 
            StringBuffer strBuf = new StringBuffer(); 
            int n = 0; 
            try { 
                    connectServer(); 
                    ftpClient.makeDirectory(distFolder + File.separator + localFile.getParent()); 
                    File fileList[] = localFile.listFiles(); 
                    for (File upfile : fileList) { 
                            if (upfile.isDirectory()) {// 文件夹中还有文件夹 
                                    uploadManyFile(upfile, localRootFile, distFolder); 
                            } else { 
                                    flag = uploadFile(upfile, localRootFile, distFolder); 
                            } 
                            if (!flag) { 
                                    strBuf.append(upfile.getName() + "\r\n"); 
                            } 
                    } 
                    System.out.println(strBuf.toString()); 
            } catch (NullPointerException e) { 
                    e.printStackTrace(); 
                    logger.debug("本地文件上传失败！找不到上传文件！", e); 
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    logger.debug("本地文件上传失败！", e); 
            } 
            return strBuf.toString(); 
    } 

    /** 
     * 下载文件 
     * 
     * @param remoteFileName             --服务器上的文件名 
     * @param localFileName--本地文件名 
     * @return true 下载成功，false 下载失败 
     */ 
    public static boolean loadFile(String remoteFileName, String localFileName) { 
            boolean flag = true; 
            connectServer(); 
            // 下载文件 
            BufferedOutputStream buffOut = null; 
            try { 
                    buffOut = new BufferedOutputStream(new FileOutputStream(localFileName)); 
                    flag = ftpClient.retrieveFile(remoteFileName, buffOut); 
            } catch (Exception e) { 
                    e.printStackTrace(); 
                    logger.debug("本地文件下载失败！", e); 
            } finally { 
                    try { 
                            if (buffOut != null) 
                                    buffOut.close(); 
                    } catch (Exception e) { 
                            e.printStackTrace(); 
                    } 
            } 
            return flag; 
    } 

    /** 
     * 删除一个文件 
     */ 
    public static boolean deleteFile(String filename) { 
            boolean flag = true; 
            try { 
                    connectServer(); 
                    flag = ftpClient.deleteFile(filename); 
                    if (flag) { 
                            System.out.println("删除文件成功！"); 
                    } else { 
                            System.out.println("删除文件失败！"); 
                    } 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
            return flag; 
    } 

    /** 
     * 删除目录 
     */ 
    public static void deleteDirectory(String pathname) { 
            try { 
                    connectServer(); 
                    File file = new File(pathname); 
                    if (file.isDirectory()) { 
                            File file2[] = file.listFiles(); 
                    } else { 
                            deleteFile(pathname); 
                    } 
                    ftpClient.removeDirectory(pathname); 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
    } 

    /** 
     * 删除空目录 
     */ 
    public static void deleteEmptyDirectory(String pathname) { 
            try { 
                    connectServer(); 
                    ftpClient.removeDirectory(pathname); 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
    } 

    /** 
     * 列出服务器上文件和目录 
     * 
     * @param regStr --匹配的正则表达式 
     */ 
    public static void listRemoteFiles(String regStr) { 
            connectServer(); 
            try { 
                    String files[] = ftpClient.listNames(regStr); 
                    if (files == null || files.length == 0) 
                            System.out.println("没有任何文件!"); 
                    else { 
                            for (int i = 0; i < files.length; i++) { 
                                    System.out.println(files[i]); 
                            } 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
    } 

    /** 
     * 列出Ftp服务器上的所有文件和目录 
     */ 
    public static void listRemoteAllFiles() { 
            connectServer(); 
            try { 
                    String[] names = ftpClient.listNames(); 
                    for (int i = 0; i < names.length; i++) { 
                            System.out.println(names[i]); 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
    } 

    /** 
     * 关闭连接 
     */ 
    public static void closeConnect() { 
            try { 
                    if (ftpClient != null) { 
                            ftpClient.logout(); 
                            ftpClient.disconnect(); 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
    } 

    /** 
     * 设置配置文件 
     * 
     * @param configFile 
     */ 
    public static void setConfigFile(String configFile) { 
            FtpUtil.configFile = configFile; 
    } 

    /** 
     * 设置传输文件的类型[文本文件或者二进制文件] 
     * 
     * @param fileType--BINARY_FILE_TYPE、ASCII_FILE_TYPE 
     * 
     */ 
    public static void setFileType(int fileType) { 
            try { 
                    connectServer(); 
                    ftpClient.setFileType(fileType); 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
    } 

    /** 
     * 扩展使用 
     * 
     * @return ftpClient 
     */ 
    protected static FTPClient getFtpClient() { 
            connectServer(); 
            return ftpClient; 
    } 

    /** 
     * 设置参数 
     * 
     * @param configFile --参数的配置文件 
     */ 
    private static void setArg(String configFile) { 
            property = new Properties(); 
            BufferedInputStream inBuff = null; 
            try { 
                    File file = new File(configFile); 
                    inBuff = new BufferedInputStream(new FileInputStream(file)); 
                    property.load(inBuff); 
                    userName = property.getProperty("username"); 
                    password = property.getProperty("password"); 
                    ip = property.getProperty("ip"); 
                    port = Integer.parseInt(property.getProperty("port")); 
            } catch (FileNotFoundException e1) { 
                    System.out.println("配置文件 " + configFile + " 不存在！"); 
            } catch (IOException e) { 
                    System.out.println("配置文件 " + configFile + " 无法读取！"); 
            } 
    } 

    /** 
     * 连接到服务器 
     * 
     * @return true 连接服务器成功，false 连接服务器失败 
     */ 
    public static boolean connectServer() { 
            boolean flag = true; 
            if (ftpClient == null) { 
                    int reply; 
                    try { 
                            setArg(configFile); 
                            ftpClient = new FTPClient(); 
                            ftpClient.setControlEncoding("GBK"); 
                            ftpClient.setDefaultPort(port); 
                            ftpClient.configure(getFtpConfig()); 
                            ftpClient.connect(ip); 
                            ftpClient.login(userName, password); 
                            ftpClient.setDefaultPort(port); 
                            //System.out.print(ftpClient.getReplyString()); 
                            reply = ftpClient.getReplyCode(); 
                            ftpClient.setDataTimeout(120000); 

                            if (!FTPReply.isPositiveCompletion(reply)) { 
                                    ftpClient.disconnect(); 
                                    System.err.println("FTP server refused connection."); 
                                    // logger.debug("FTP 服务拒绝连接！"); 
                                    flag = false; 
                            } 
//                            System.out.println(i); 
                            i++; 
                    } catch (SocketException e) { 
                            flag = false; 
                            e.printStackTrace(); 
                            System.err.println("登录ftp服务器 " + ip + " 失败,连接超时！"); 
                    } catch (IOException e) { 
                            flag = false; 
                            e.printStackTrace(); 
                            System.err.println("登录ftp服务器 " + ip + " 失败，FTP服务器无法打开！"); 
                    } 
            } 
            return flag; 
    } 

    /** 
     * 进入到服务器的某个目录下 
     * 
     * @param directory 
     */ 
    public static void changeWorkingDirectory(String directory) { 
            try { 
                    connectServer(); 
                    ftpClient.changeWorkingDirectory(directory); 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
    } 

    /** 
     * 返回到上一层目录 
     */ 
    public static void changeToParentDirectory() { 
            try { 
                    connectServer(); 
                    ftpClient.changeToParentDirectory(); 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
    } 

    /** 
     * 重命名文件 
     * 
     * @param oldFileName --原文件名 
     * @param newFileName --新文件名 
     */ 
    public static void renameFile(String oldFileName, String newFileName) { 
            try { 
                    connectServer(); 
                    ftpClient.rename(oldFileName, newFileName); 
            } catch (IOException ioe) { 
                    ioe.printStackTrace(); 
            } 
    } 

    /** 
     * 设置FTP客服端的配置--一般可以不设置 
     * 
     * @return ftpConfig 
     */ 
    private static FTPClientConfig getFtpConfig() { 
            FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX); 
            ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING); 
            return ftpConfig; 
    } 

    /** 
     * 转码[ISO-8859-1 -> GBK] 不同的平台需要不同的转码 
     * 
     * @param obj 
     * @return "" 
     */ 
    private static String iso8859togbk(Object obj) { 
            try { 
                    if (obj == null) 
                            return ""; 
                    else 
                            return new String(obj.toString().getBytes("iso-8859-1"), "GBK"); 
            } catch (Exception e) { 
                    return ""; 
            } 
    } 

    /** 
     * 在服务器上创建一个文件夹 
     * 
     * @param dir 文件夹名称，不能含有特殊字符，如 \ 、/ 、: 、* 、?、 "、 <、>... 
     */ 
    public static boolean makeDirectory(String dir) { 
            connectServer(); 
            boolean flag = true; 
            try { 
                    flag = ftpClient.makeDirectory(dir); 
                    if (flag) { 
                            System.out.println("make Directory " + dir + " succeed"); 

                    } else { 

                            System.out.println("make Directory " + dir + " false"); 
                    } 
            } catch (Exception e) { 
                    e.printStackTrace(); 
            } 
            return flag; 
    } 
    
    
    /**
     * Description: 向FTP服务器上传文件
     * 
     * @Version1.0
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param path
     *            FTP服务器保存目录,如果是根目录则为“/”
     * @param filename
     *            上传到FTP服务器上的文件名
     * @param input
     *            本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public boolean uploadFile(String url, String username,
            String password, String path, String filename, InputStream input) throws Exception{
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setDefaultTimeout(timeout * 1000);
        ftpClient.setConnectTimeout(timeout * 1000);
        ftpClient.setDataTimeout(timeout * 1000);
        try {
            int reply;
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.connect(url);
//            ftpClient.connect(url, port);// 连接FTP服务器
            // 登录
            ftpClient.login(username, password);
            ftpClient.setControlEncoding(encoding);
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("连接失败");
                ftpClient.disconnect();
                return result;
            }
            //使用被动模式
            ftpClient.enterLocalPassiveMode();

            // 转移工作目录至指定目录下
            boolean change = ftpClient.changeWorkingDirectory(path);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                result = ftpClient.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
                if (result) {
                    System.out.println("上传成功!");
                }
            }
            input.close();
            ftpClient.logout();
        } catch (Exception e) {
        	logger.error("FtpApche.uploadFile()"+filename, e);
        	return false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception ioe) {
                	logger.error("FtpApche.uploadFile()"+filename, ioe);
                	return false;
                }
            }
            if (input!=null) {
                try {
                	 input.close();
                } catch (Exception ioe) {
                	logger.error("FtpApche.uploadFile()"+filename, ioe);
                	return false;
                }
            }
        }
        return result;
    }


    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param fileName
     *            要下载的文件名
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public boolean downFile(String url, int port, String username,
            String password, String remotePath, String fileName,
            String localPath) {
        boolean result = false;
        FTPClient ftpClient = new FTPClient();
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            
            /*
             *  为了上传和下载中文文件，有些地方建议使用以下两句代替
             *  new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
             *  经过测试，通不过。
             */
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");

            ftpClient.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                	
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    
                    StringBuilder sb = new StringBuilder();
                    InputStream inStream =  ftpClient.retrieveFileStream(ff.getName());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
                    String line = null;      
                    while ((line = reader.readLine()) != null) {      
                      sb.append(line + "\n");      
                    } 
                    
                    is.close();
                }
            }

            ftpClient.logout();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception ioe) {
                }
            }
        }
        return result;
    }
    
    
    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public List<String[]> downFiles(String url, String username,
            String password, String remotePath) throws Exception{
    	List<String[]> result = new ArrayList<String[]>();
    	FTPClient ftpClient = new FTPClient();
    	ftpClient.setDefaultTimeout(timeout * 1000);
        ftpClient.setConnectTimeout(timeout * 1000);
        ftpClient.setDataTimeout(timeout * 1000);
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            
            /*
             *  为了上传和下载中文文件，有些地方建议使用以下两句代替
             *  new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
             *  经过测试，通不过。
             */
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");

            ftpClient.connect(url);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //使用被动模式
            ftpClient.enterLocalPassiveMode();
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                return null;
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            
            for (FTPFile ff : fs) {
            	String[] strs = new String[2];
            	StringBuilder sb = new StringBuilder();
            	InputStream inStream =  ftpClient.retrieveFileStream(ff.getName());
            	BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
            	String line = null;      
            	while ((line = reader.readLine()) != null) {      
            		sb.append(line + "\n");      
            	} 
            	if (inStream != null) { 
            		inStream.close(); 
            		ftpClient.completePendingCommand();
            	} 
            	strs[0] = ff.getName();
            	strs[1] = sb.toString();
            	result.add(strs);
            }
            ftpClient.logout();
        } catch (Exception e) {
        	logger.error("FtpApche.downFiles()", e);
        	return new ArrayList<String[]>();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception ioe) {
                	logger.error("FtpApche.downFiles()", ioe);
                	return new ArrayList<String[]>();
                }
            }
        }
        return result;
    }
    
    public boolean isFileExist(String url, String username,
            String password, String remotePath, String messageId){
    	boolean result = false;
    	FTPClient ftpClient = new FTPClient();
    	ftpClient.setDefaultTimeout(timeout * 1000);
        ftpClient.setConnectTimeout(timeout * 1000);
        ftpClient.setDataTimeout(timeout * 1000);
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            
            /*
             *  为了上传和下载中文文件，有些地方建议使用以下两句代替
             *  new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
             *  经过测试，通不过。
             */
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");

            ftpClient.connect(url);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //使用被动模式
            ftpClient.enterLocalPassiveMode();
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            // 获取文件列表
            FTPFile[] fs = ftpClient.listFiles();
            for (FTPFile ff : fs) {
            	if(ff.getName().indexOf(messageId)!=-1)
            	{
            		result = true;
            		break;
            	}
            }
            ftpClient.logout();
        } catch (Exception e) {
        	logger.error("FtpApche.isFileExist() "+messageId, e);
        	return false;
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception ioe) {
                	logger.error("FtpApche.isFileExist() "+messageId, ioe);
                	return false;
                }
            }
        }
        return result;
    }
    
    
    /**
     * Description: 从FTP服务器下载文件
     * 
     * @Version1.0
     * @param url
     *            FTP服务器hostname
     * @param port
     *            FTP服务器端口
     * @param username
     *            FTP登录账号
     * @param password
     *            FTP登录密码
     * @param remotePath
     *            FTP服务器上的相对路径
     * @param localPath
     *            下载后保存到本地的路径
     * @return
     */
    public void deleteFiles(String url, String username,
            String password, String remotePath,String fileName) {
    	FTPClient ftpClient = new FTPClient();
    	ftpClient.setDefaultTimeout(timeout * 1000);
        ftpClient.setConnectTimeout(timeout * 1000);
        ftpClient.setDataTimeout(timeout * 1000);
        try {
            int reply;
            ftpClient.setControlEncoding(encoding);
            
            /*
             *  为了上传和下载中文文件，有些地方建议使用以下两句代替
             *  new String(remotePath.getBytes(encoding),"iso-8859-1")转码。
             *  经过测试，通不过。
             */
//            FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
//            conf.setServerLanguageCode("zh");

            ftpClient.connect(url);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            ftpClient.login(username, password);// 登录
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //使用被动模式
            ftpClient.enterLocalPassiveMode();
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.err.println("FTP server refused connection.");
            }
            // 转移到FTP服务器目录至指定的目录下
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            // 获取文件列表
            boolean flag = ftpClient.deleteFile(fileName); 
            if (flag) { 
                    System.out.println("删除文件成功！"); 
            } else { 
                    System.out.println("删除文件失败！"); 
            } 
            ftpClient.logout();
        } catch (Exception e) {
        	logger.error("FtpApche.deleteFiles() "+fileName, e);
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (Exception ioe) {
                	logger.error("FtpApche.deleteFiles() "+fileName, ioe);
                }
            }
        }
    }

    public static void main(String[] args) { 
            connectServer(); 
            setFileType(FTP.BINARY_FILE_TYPE);// 设置传输二进制文件 
            uploadManyFile(new File("C:\\ooo\\upx"), new File("C:\\ooo\\upx"), "/admin/ttt"); 
            closeConnect();// 关闭连接 
    } 
    
}
