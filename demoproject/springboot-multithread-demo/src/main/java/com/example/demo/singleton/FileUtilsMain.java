package com.example.demo.singleton;

/**
 * Created by Administrator on 2018/7/20.
 */
public class FileUtilsMain {


    public static void main(String[] args) {
        /*String fileContent = "<html><body><h2>Hello !</h2></body></html>";
        String saveFilePath = "D:" + File.separatorChar + "temp";
        String fileName = System.currentTimeMillis() + ".html";

        boolean htmlFile = createFile(fileContent, saveFilePath, fileName);
        System.out.println(htmlFile);*/

        //fileGenerate("{\"startTime\":\"1529404943053\",\"endTime\":\"1529463839583\"}","D:\\temp\\templates\\file","tfAllClose.txt");

        String readFileComtent = FileUtils.INSTANCE.readFile("D:\\temp\\templates\\file\\tfAllClose.txt");
        System.out.println(readFileComtent);


    }

}
