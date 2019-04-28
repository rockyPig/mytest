package com.joyowo.smarthr.cbsdata.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName SqlReader
 * @Author Spark
 * @Date 2019/4/23 18:40
 * @Description TODO
 * @Version v1.2
 **/
public class SqlReader {

    private static String searchSql;
    private static String searchHisSql;
    private static String existedInfoSql;
    private static String saveSql;
    private static String updateSql;
    private static int time;
    private static int hisDataSearchDayNum;



    static {
        Properties prop = new Properties();
        InputStream in = SqlReader.class.getResourceAsStream("/config.properties");
        try {
            prop.load(in);
            searchSql = prop.getProperty("search.sql").trim();
            searchHisSql = prop.getProperty("search.history.sql").trim();
            existedInfoSql = prop.getProperty("existed.info.sql").trim();
            saveSql = prop.getProperty("save.sql").trim();
            updateSql = prop.getProperty("update.sql").trim();
            time = Integer.valueOf(prop.getProperty("time").trim()).intValue();
            hisDataSearchDayNum=Integer.valueOf(prop.getProperty("hisDataSearchDayNum").trim()).intValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private SqlReader() {
    }

    public static String getSearchSql() {
        return searchSql;
    }

    public static String getSearchHisSql() {
        return searchHisSql;
    }

    public static String getExistedInfoSql() {
        return existedInfoSql;
    }

    public static String getSaveSql() {
        return saveSql;
    }

    public static String getUpdateSql(){return updateSql;}

    public static int getTime() { return time; }

    public static int getHisDataSearchDayNum(){return hisDataSearchDayNum;}
}
