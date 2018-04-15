package com.example.common.util.file;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.common.util.request.HttpClientUtils;
import com.example.common.util.request.HttpClientUtils.NetFile;

public class FileDownload {
	
    private Pattern contentDispositionPattern = Pattern.compile(".*filename=\"(.*)\".*");

    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static FileDownload instance = new FileDownload();


    private static RequestConfig requestConfig =
            RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();

    private FileDownload() {
    }

    public static FileDownload getInstance() {
        return instance;
    }
	    
    /**
     * 下载图片
     *
     * @param url
     *
     * @return
     */
    public NetFile downImage(String url) {
        NetFile netFile = new NetFile();
        HttpGet httpGet = new HttpGet(url);
        byte[] responseContent = null;
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 解析实体
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toByteArray(entity);
            netFile.setContent(responseContent);
            // 解析下载的文件名
            String filename = null;
            Header contentDisposition = response.getFirstHeader("Content-disposition");
            if (contentDisposition != null && contentDisposition.getValue() != null) {
                String dispostion = contentDisposition.getValue();
                Matcher matcher = contentDispositionPattern.matcher(dispostion);
                if (matcher.find()) {
                    filename = matcher.group(1);
                }
            }
            netFile.setFileName(filename);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return netFile;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
