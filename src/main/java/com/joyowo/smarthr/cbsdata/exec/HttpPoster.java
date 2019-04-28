package com.joyowo.smarthr.cbsdata.exec;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName HttpPoster
 * @Author Spark
 * @Date 2019/4/23 20:03
 * @Description TODO
 * @Version v1.2
 **/
public class HttpPoster {

    private static Logger log  =  Logger.getLogger(HttpPoster.class );

    private final static String CHARSET = "utf-8";

    public static void doPost(String httpUrl, Map<String, String> header, String jsonBody) throws Exception {
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：60000毫秒
            connection.setConnectTimeout(60000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);
            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            //设定 请求格式 json
            connection.setRequestProperty("Content-Type", " application/json");
            //设置编码语言
            connection.setRequestProperty("Accept-Charset", "utf-8");
            if (header != null && !header.isEmpty()) {
                Iterator<String> it = header.keySet().iterator();
                String headerKey, headerValue;
                while (it.hasNext()) {
                    headerKey = it.next();
                    headerValue = header.get(headerKey);
                    connection.setRequestProperty(headerKey, headerValue);
                }
            }

            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            os.write(jsonBody.getBytes());
            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, CHARSET));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            log.info("----http post error = " + e.getMessage());
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } catch (IOException e) {
            log.info("----http post error = " + e.getMessage());
            log.error(e.getMessage(), e);
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.info("----http post error = " + e.getMessage());
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.info("----http post error = " + e.getMessage());
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.info("----http post error = " + e.getMessage());
                    log.error(e.getMessage(), e);
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        log.info(" ---- http post response ==" + result);
    }
}
