package com.joyowo.smarthr.cbsdata.exec;

import com.google.gson.GsonBuilder;
import com.joyowo.smarthr.cbsdata.entity.CbsBankAcctTradeDetail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.joyowo.smarthr.cbsdata.entity.HttpBody;
import com.joyowo.smarthr.cbsdata.jdbc.JdbcHandler;
import com.joyowo.smarthr.cbsdata.property.ApiReader;
import com.joyowo.smarthr.cbsdata.property.SqlReader;
import com.joyowo.smarthr.cbsdata.util.DateUtil;
import com.joyowo.smarthr.cbsdata.util.GsonUtil;
import org.apache.log4j.Logger;

/**
 * @ClassName DataSaver
 * @Author Spark
 * @Date 2019/4/23 19:51
 * @Description TODO
 * @Version v1.2
 **/
public class DataSaver {

    private static Logger log  =  Logger.getLogger(DataSaver.class );

    /**
     * 流水处理
     *
     * @param dataList
     * @return
     * @throws Exception
     */
    public boolean executeData(ArrayList<CbsBankAcctTradeDetail> dataList)  throws Exception{
        log.info("处理流水------开始------");
        try{
            Set<Long> ids = this.saveDataArray(dataList);
            if(ids != null){
                this.updateAccounts();

                HttpBody body = new HttpBody();
                body.setIds(ids);
                String bodyJson = GsonUtil.toJson(body);
                HttpPoster.doPost(ApiReader.getApiUrl(), null, bodyJson);
            }
        }catch (Exception e){
            log.info("交易记录保存失败"+e.getMessage());
            log.error(e);
        }
        log.info("处理流水------结束------");
        return true;
    }

    /**
     * 保存流水
     *
     * @param dataList
     * @return
     * @throws Exception
     */
    public Set<Long> saveDataArray(ArrayList<CbsBankAcctTradeDetail> dataList)  throws Exception{
        Set<Long> ids = new HashSet<Long>();
        StringBuffer sqlValues = new StringBuffer();
        log.info("交易记录保存----开始--");
        for (CbsBankAcctTradeDetail bo : dataList) {
            sqlValues.append(" (");
            sqlValues.append(bo.getId()).append(",");
            if(bo.getAcctId() != null){
                sqlValues.append(bo.getAcctId()).append(",");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctCode() != null){
                sqlValues.append("'").append(bo.getAcctCode()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctTitle() != null){
                sqlValues.append("'").append(bo.getAcctTitle()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBankAcct() != null){
                sqlValues.append("'").append(bo.getBankAcct()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBankAcctTitle() != null){
                sqlValues.append("'").append(bo.getBankAcctTitle()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBankOpenCode() != null){
                sqlValues.append("'").append(bo.getBankOpenCode()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBankOpenTitle() != null){
                sqlValues.append("'").append(bo.getBankOpenTitle()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getPartyAcctCode() != null){
                sqlValues.append("'").append(bo.getPartyAcctCode()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getPartyAcctTitle() != null){
                sqlValues.append("'").append(bo.getPartyAcctTitle()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getPartyBankOpenTitle() != null){
                sqlValues.append("'").append(bo.getPartyBankOpenTitle()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getCurrency() != null){
                sqlValues.append("'").append(bo.getCurrency()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTradeType() != null){
                sqlValues.append("'").append(bo.getTradeType()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTradeTime() != null){
                sqlValues.append("'").append(DateUtil.dateToString(bo.getTradeTime(), DateUtil.formdatetime1)).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTradeAmount() != null){
                sqlValues.append("'").append(bo.getTradeAmount()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTradeChannel() != null){
                sqlValues.append("'").append(bo.getTradeChannel()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTradeBankSerial() != null){
                sqlValues.append("'").append(bo.getTradeBankSerial()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getSummary() != null){
                sqlValues.append("'").append(bo.getSummary()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getPurpose() != null){
                sqlValues.append("'").append(bo.getPurpose()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getPostscript() != null){
                sqlValues.append("'").append(bo.getPostscript()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getRemark() != null){
                sqlValues.append("'").append(bo.getRemark()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getExta() != null){
                sqlValues.append("'").append(bo.getExta()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getExtb() != null){
                sqlValues.append("'").append(bo.getExtb()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBalance() != null){
                sqlValues.append("'").append(bo.getBalance()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getTransId() != null){
                sqlValues.append(bo.getTransId()).append(",");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctCheckStatus() != null){
                sqlValues.append("'").append(bo.getAcctCheckStatus()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBusRemarkCode() != null){
                sqlValues.append("'").append(bo.getBusRemarkCode()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getBankUpdateTime() != null){
                sqlValues.append("'").append(bo.getBankUpdateTime()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctSubId() != null){
                sqlValues.append("'").append(bo.getAcctSubId()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctCodeSub() != null){
                sqlValues.append("'").append(bo.getAcctCodeSub()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctTitleSub() != null){
                sqlValues.append("'").append(bo.getAcctTitleSub()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getAcctSubCheckStatus() != null){
                sqlValues.append("'").append(bo.getAcctSubCheckStatus()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            if(bo.getDataSource() != null){
                sqlValues.append("'").append(bo.getDataSource()).append("',");
            }else{
                sqlValues.append("null").append(",");
            }
            sqlValues.append("now()").append(",");
            sqlValues.append("now()").append(",");
            sqlValues.append("0").append(",");
            sqlValues.append("0").append(",");
            sqlValues.append("0");
            sqlValues.append("),");
            ids.add(bo.getId());
        }
        try{
            JdbcHandler jdbcHandler = new JdbcHandler();
            jdbcHandler.updateBySql(SqlReader.getSaveSql() + sqlValues.deleteCharAt(sqlValues.length() - 1));
        }catch (Exception e){
            ids = null;
            log.info("流水导入到业务库发生错误：" + e.getMessage());
            log.error(e);
        }
        log.info("交易记录保存----结束--");
        return ids;
    }

    /**
     * 更新账户信息
     *
     * @return
     * @throws Exception
     */
    public boolean updateAccounts()  throws Exception{
        JdbcHandler jdbcHandler = new JdbcHandler();
        jdbcHandler.updateBySql(SqlReader.getUpdateSql());
        log.info("更新账户信息----成功--");
        return true;
    }
}
