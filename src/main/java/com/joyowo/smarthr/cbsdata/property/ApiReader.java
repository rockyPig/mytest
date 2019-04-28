package com.joyowo.smarthr.cbsdata.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName ApiReader
 * @Author Spark
 * @Date 2019/4/24 13:18
 * @Description TODO
 * @Version v1.2
 **/
public class ApiReader {

    private static String apiUrl;

    static {
        Properties prop = new Properties();
        InputStream in = SqlReader.class.getResourceAsStream("/http.properties");
        try {
            prop.load(in);
            apiUrl = prop.getProperty("apiUrl").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ApiReader() {
    }

    public static String getApiUrl() {
        return apiUrl;
    }
}
