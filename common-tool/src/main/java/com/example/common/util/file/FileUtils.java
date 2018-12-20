package com.liby.search.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作
 *
 * Created by liunanhua on 2018/12/20.
 */
public class FileUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);


    /**
     * 读取文件内容并返回
     *
     * 用于读面向行的格式化大文件
     * @param inputFile 来源路径
     */
    public static List<String> readFile(String inputFile) {
        List<String> resultList = new ArrayList<>();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        try {
            fis = new FileInputStream(new File(inputFile));
            bis = new BufferedInputStream(fis);
            isr = new InputStreamReader(bis, "utf-8");
            in = new BufferedReader(isr, 10 * 1024 * 1024);//10M
            while (in.ready()) {
                resultList.add(in.readLine());
            }
        } catch (IOException e) {
            logger.error("读取文件异常", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
        }
        return resultList;
    }



    /**
     * 上传文件
     *
     * 用于读面向行的格式化大文件
     * @param inputFile 来源路径
     * @param outputFile 上传路径
     */
    public static void uploadFile(String inputFile, String outputFile) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        InputStreamReader isr = null;
        BufferedReader in = null;
        FileWriter fw = null;
        try {
            fis = new FileInputStream(new File(inputFile));
            bis = new BufferedInputStream(fis);
            isr = new InputStreamReader(bis, "utf-8");
            in = new BufferedReader(isr, 10 * 1024 * 1024);//10M
            fw = new FileWriter(outputFile);
            while (in.ready()) {
                String line = in.readLine();
                fw.append(line + " ");
            }
            fw.flush();
        } catch (IOException e) {
            logger.error("读取文件异常", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("读取文件关闭文件流异常", e);
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e1) {
                    logger.error("读取文件关闭文件流异常", e1);
                }
            }
        }
    }


    /** ==================================== 单例 ===================================================  */

    private FileUtils(){}

    public static FileUtils getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private static enum Singleton{

        INSTANCE;

        private FileUtils singleton;

        private Singleton(){
            singleton = new FileUtils();
        }

        public FileUtils getInstance(){
            return singleton;
        }
    }


}
