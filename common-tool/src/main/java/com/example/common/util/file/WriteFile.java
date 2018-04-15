package com.example.common.util.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteFile {

    /**
     * A方法追加文件内容：使用RandomAccessFile
     */
    public static void appendFileA(String fileName, String content) {
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * B方法追加文件内容：使用FileWriter
     */
    public static void appendFileB(String fileName, String content) {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "d:/day.log";
        String content = "new append!";
        //按方法A追加文件
        WriteFile.appendFileA(fileName, content);
        WriteFile.appendFileA(fileName, "append end. \n");

        //显示文件内容
        ReadFile.readFileByLines(fileName);
        //按方法B追加文件
        WriteFile.appendFileB(fileName, content);
        WriteFile.appendFileB(fileName, "append end. \n");
        //显示文件内容
        ReadFile.readFileByLines(fileName);
    }

}
