package com.joyowo.smarthr.cbsdata.test;

import com.joyowo.smarthr.cbsdata.entity.CbsBankAcctTradeDetail;
import com.joyowo.smarthr.cbsdata.util.DataBase;
import com.joyowo.smarthr.cbsdata.util.DateUtil;
import org.apache.log4j.Logger;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Spark
 * @Create 2019/4/23
 */
public class TestJdbcHandler {

    private static Logger log  =  Logger.getLogger(TestJdbcHandler.class );


    public ArrayList<Perm> findPerm(String sql ) throws Exception{
        ArrayList<Perm> dataList = new ArrayList<Perm>();
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(DataBase.getDriver3());
            String url = DataBase.getUrl3();
            String username = DataBase.getUsername3();
            String password = DataBase.getPassword3();
            con =  DriverManager.getConnection(url, username, password) ;
            stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            Perm record;
            while(rs.next()){
                record = new Perm();
                record.setId(rs.getLong("id"));
                record.setRole_menu_action_id(rs.getLong("role_menu_action_id"));
                record.setUser_id(rs.getLong("user_id"));
                record.setRange_type(rs.getInt("range_type"));
                record.setBus_table(rs.getString("bus_table"));
                record.setBus_table_column(rs.getString("bus_table_column"));

                dataList.add(record);
            }
            if(rs != null){  rs.close();}
            return dataList;
        }catch(Exception e){
            log.error("数据库连接失败！");
            e.printStackTrace() ;
            throw e;
        }finally {
            closeJdbc(con,stmt);
        }
    }

    public ArrayList<Detail> findPermDetail(String sql ) throws Exception{
        ArrayList<Detail> dataList = new ArrayList<Detail>();
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(DataBase.getDriver3());
            String url = DataBase.getUrl3();
            String username = DataBase.getUsername3();
            String password = DataBase.getPassword3();
            con =  DriverManager.getConnection(url, username, password) ;
            stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            Detail record;
            while(rs.next()){
                record = new Detail();
                record.setId(rs.getLong("id"));
                record.setPerm_id(rs.getLong("perm_id"));
                record.setBus_id(rs.getString("bus_id"));

                dataList.add(record);
            }
            if(rs != null){  rs.close();}
            return dataList;
        }catch(Exception e){
            log.error("数据库连接失败！");
            e.printStackTrace() ;
            throw e;
        }finally {
            closeJdbc(con,stmt);
        }
    }



    /**
     * 关闭数据库连接
     * @param con
     * @param stmt
     */
    private void closeJdbc(java.sql.Connection con,Statement stmt) {
        try {
            if (stmt != null) { stmt.close(); }
            if (con != null) { con.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
