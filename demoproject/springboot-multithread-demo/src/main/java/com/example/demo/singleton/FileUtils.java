package com.example.demo.singleton;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

/**
 * Created by liulanhua on 2018/6/22.
 */
public enum FileUtils {

    INSTANCE;

    private FileUtils(){
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 生成文件
     *
     * @param fileContent  生成文件中的内容
     * @param saveFilePath 生成文件保存路径
     * @param fileName     生成的文件名
     * @return 生成文件是否成功
     */
    public Boolean createFile(String fileContent, String saveFilePath, String fileName) {
        logger.info("生成文件操作,fileContent:{},saveFilePath:{},fileName:{}", fileContent, saveFilePath, fileName);
        long dateTimeStart = System.currentTimeMillis();
        Boolean result = Boolean.FALSE;

        //生成文件保存路径
        String urlPath = saveFilePath + File.separatorChar + fileName;
        InputStream is = null;
        BufferedReader br = null;
        BufferedWriter bufferedWriter = null;
        InputStreamReader isr = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            File file = new File(saveFilePath);
            //如果文件夹不存在则创建
            if (!file.exists()) {
                logger.info("文件保存路径不存在,创建保存路径：{}", saveFilePath);
                boolean mkdirs = file.mkdirs();
                if (mkdirs) {
                    logger.info("文件保存路径生成成功:{}", saveFilePath);
                }
            }
            logger.info("文件访问路径:{}", urlPath);
            StringBuilder buffer = new StringBuilder();
            is = new ByteArrayInputStream(fileContent.getBytes("UTF-8"));
            isr = new InputStreamReader(is, "UTF-8");
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                buffer.append(line).append("\n");
            }

            fos = new FileOutputStream(urlPath);
            osw = new OutputStreamWriter(fos,"UTF-8");
            bufferedWriter = new BufferedWriter(osw);
            bufferedWriter.write(buffer.toString());
            bufferedWriter.newLine();
            bufferedWriter.flush();
            result = Boolean.TRUE;
        } catch (Exception e) {
            logger.error("生成文件异常", e);
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
                if (null != isr) {
                    isr.close();
                }
                if (null != fos) {
                    fos.close();
                }
                if (null != osw) {
                    osw.close();
                }
                if (null != br) {
                    br.close();
                }
                if (null != bufferedWriter) {
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                logger.error("释放文件流异常", e);
            }
        }
        logger.info("生成文件耗时:{}", System.currentTimeMillis() - dateTimeStart);

        return result;
    }

    /**
     * 读文件
     *
     * @param fileName
     */
    public String readFile(String fileName) {
        logger.info("读出文件内容操作,文件路径：{}", fileName);
        long dateTimeStart = System.currentTimeMillis();
        try {
            File file = new File(fileName);
            //如果文件夹不存在则创建
            if (!file.exists()) {
                logger.info("读出文件不存在,文件路径：{}", fileName);
                return null;
            }
            Scanner scanner = new Scanner(file);
            StringBuilder buffer = new StringBuilder();
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
            logger.info("读出文件内容耗时:{}，内容为：{}", System.currentTimeMillis() - dateTimeStart, buffer.toString());
            return buffer.toString();
        } catch (FileNotFoundException e) {
            logger.error("读出文件内容异常", e);
        }
        return null;
    }



}
