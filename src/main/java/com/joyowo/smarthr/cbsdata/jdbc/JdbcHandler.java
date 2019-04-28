package com.joyowo.smarthr.cbsdata.jdbc;

import com.joyowo.smarthr.cbsdata.MainBoot;
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
public class JdbcHandler {

    private static Logger log  =  Logger.getLogger(JdbcHandler.class );

    /**
     * 取出CBS流水记录
     * @param sql
     * @return
     * @throws Exception
     */
    public ArrayList<CbsBankAcctTradeDetail> findTransInfo(String sql ) throws Exception{

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=df.format(new Date());
        ArrayList<CbsBankAcctTradeDetail> dataList = new ArrayList<CbsBankAcctTradeDetail>();
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(DataBase.getDriver2());
            String url = DataBase.getUrl2();
            String username = DataBase.getUsername2();
            String password = DataBase.getPassword2();
            con =  DriverManager.getConnection(url, username, password) ;
            stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            CbsBankAcctTradeDetail record;
            while(rs.next()){
                record = new CbsBankAcctTradeDetail();
                //主键ID
                record.setId(rs.getLong("TRANS_ID"));
                //账号ID //处理后集中更新
                record.setAcctId(0L);
                //账户编号  //处理后集中更新
                record.setAcctCode("init");
                //账户名称  //处理后集中更新
                record.setAcctTitle("init");
                //银行账户
                record.setBankAcct(rs.getString("ACCOUNTS"));
                //银行户名
                record.setBankAcctTitle(rs.getString("ACCOUNTS_NAME"));
                //开户行号
                record.setBankOpenCode(null);
                //开户行名    //处理后集中更新
                record.setBankOpenTitle("init");
                //对方账号
                record.setPartyAcctCode(rs.getString("OPPOSITE_ACCOUNTS"));
                //对方户名
                record.setPartyAcctTitle(rs.getString("OPPOSITE_ACCOUNTS_NAME"));
                //对方开户行
                record.setPartyBankOpenTitle(rs.getString("OPPOSITE_BANK_NAME"));
                //币种
                record.setCurrency(rs.getString("CURRENCY_TYPE"));
                //交易类型: 1-借(付款); 2-贷(收款)
                record.setTradeType(Integer.parseInt(rs.getString("DEBIT_CREDIT_FLAG")));
                //交易时间
                record.setTradeTime(DateUtil.stringToDate(rs.getString("TRANS_DATETIME"), DateUtil.formdatetime1));
                //交易金额
                record.setTradeAmount(rs.getDouble("AMOUNT"));
                //交易渠道(结算类型):
                record.setTradeChannel(null);
                //流水表交易ID
                record.setTransId(rs.getInt("TRANS_ID"));
                //银行流水号
                record.setTradeBankSerial(rs.getString("BANK_SEQ_NUMBER"));
                //摘要
                record.setSummary(rs.getString("ABSTRACT"));
                //用途
                record.setPurpose(rs.getString("PURPOSE"));
                //附言
                record.setPostscript(null);
                //备注
                record.setRemark(rs.getString("RECORD_INFO"));
                //扩展1
                record.setExta(null);
                //扩展2
                record.setExtb(null);
                //余额
                record.setBalance(rs.getDouble("BALANCE"));
                //对账状态: 0-未对账; 1-对账成功; 2-对账失败
                record.setAcctCheckStatus(0);
                //业务参考号
                record.setBusRemarkCode(null);
                record.setBankUpdateTime(null);
                //子账号ID
                record.setAcctSubId(0L);
                //子账号编号
                record.setAcctCodeSub(null);
                //子账号名称
                record.setAcctTitleSub(null);
                //子账号核对状态: 0-未核对; 1-已核对
                record.setAcctSubCheckStatus(0);
                //数据来源: 1-CBS; 2-浙商银行缴保通; 3-浙商银企直联; 4-手工
                record.setDataSource(1);
                //认款状态
//                record.setClaimState(0);
//                record.setCreateTime(DateUtil.getCurrentDate());
//                record.setUpdateTime(DateUtil.getCurrentDate());
//                record.setCreateUid(0L);
//                record.setUpdateUid(0L);

                dataList.add(record);
            }
            if(rs != null){  rs.close();}
            return dataList;
        }catch(Exception e){
            log.error("数据库连接失败！");
            log.error(e);
            e.printStackTrace() ;
            throw e;
        }finally {
            closeJdbc(con,stmt);
        }
    }


    /**
     * 查询已经存入的数据
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public ArrayList<CbsBankAcctTradeDetail> findExistedInfo(String sql )throws Exception{
        log.info("查询的sql：" + sql);
        ArrayList<CbsBankAcctTradeDetail> dataList = new ArrayList<CbsBankAcctTradeDetail>();
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(DataBase.getDriver());
            String url = DataBase.getUrl();
            String username = DataBase.getUsername();
            String password = DataBase.getPassword();
            con =  DriverManager.getConnection(url, username, password) ;
            stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery(sql);
            while(rs.next()){
                CbsBankAcctTradeDetail bo = new CbsBankAcctTradeDetail();
                bo.setTransId(rs.getInt("trans_id"));
                bo.setTradeAmount(rs.getDouble("trade_amount"));
                bo.setTradeBankSerial(rs.getString("trade_bank_serial"));
                bo.setBankAcct(rs.getString("bank_acct"));
                bo.setBankAcctTitle(rs.getString("bank_acct_title"));
                bo.setPartyAcctCode(rs.getString("party_acct_code"));
                bo.setPartyAcctTitle(rs.getString("party_acct_title"));
                dataList.add(bo);
            }
            if(rs != null){ rs.close();}
            return dataList;
        }catch(Exception e){
            log.error("数据库连接失败！");
            log.error(e);
            e.printStackTrace() ;
            throw e;
        }finally {
            closeJdbc(con,stmt);
        }
    }



    /**
     * 处理sql语句
     * @return boolean
     */
    public boolean updateBySql( String sql ) throws Exception{
        log.info("执行更新的sql：" + sql);
        java.sql.Connection con = null;
        Statement stmt = null;
        try{
            Class.forName(DataBase.getDriver());
            String url = DataBase.getUrl();
            String username = DataBase.getUsername();
            String password = DataBase.getPassword();
            con =  DriverManager.getConnection(url, username, password) ;
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) > 0;
        }catch(Exception e){
            log.error(e.getMessage());
            log.error(e);
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
            log.error(e);
            e.printStackTrace();
        }
    }




}
