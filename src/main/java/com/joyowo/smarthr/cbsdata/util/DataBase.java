package com.joyowo.smarthr.cbsdata.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Spark
 * @Create 2019/4/23
 */
public class DataBase {
    private static String url;
    private static String driver;
    private static String username;
    private static String password;

    private static String url2;
    private static String driver2;
    private static String username2;
    private static String password2;

    private static String url3;
    private static String driver3;
    private static String username3;
    private static String password3;
    static {
        Properties prop = new Properties();
        InputStream in = DataBase.class.getResourceAsStream("/database.properties");
        try {
            prop.load(in);
            url = prop.getProperty("jdbc.url").trim();
            driver = prop.getProperty("jdbc.driver").trim();
            username = prop.getProperty("jdbc.username").trim();
            password = prop.getProperty("jdbc.password").trim();

            url2 = prop.getProperty("jdbc2.url").trim();
            driver2 = prop.getProperty("jdbc2.driver").trim();
            username2 = prop.getProperty("jdbc2.username").trim();
            password2 = prop.getProperty("jdbc2.password").trim();

            url3 = prop.getProperty("jdbc3.url").trim();
            driver3 = prop.getProperty("jdbc3.driver").trim();
            username3 = prop.getProperty("jdbc3.username").trim();
            password3 = prop.getProperty("jdbc3.password").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DataBase() {
    }

    public static String getUrl() {
        return url;
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl2() {
        return url2;
    }

    public static String getDriver2() {
        return driver2;
    }

    public static String getUsername2() {
        return username2;
    }

    public static String getPassword2() {
        return password2;
    }

    public static String getUrl3() {
        return url3;
    }

    public static String getDriver3() {
        return driver3;
    }

    public static String getUsername3() {
        return username3;
    }

    public static String getPassword3() {
        return password3;
    }
}