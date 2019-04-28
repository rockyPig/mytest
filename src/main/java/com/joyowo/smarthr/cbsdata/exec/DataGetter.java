package com.joyowo.smarthr.cbsdata.exec;

import com.joyowo.smarthr.cbsdata.entity.CbsBankAcctTradeDetail;
import com.joyowo.smarthr.cbsdata.jdbc.JdbcHandler;
import com.joyowo.smarthr.cbsdata.util.DateUtil;
import com.joyowo.smarthr.cbsdata.property.SqlReader;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * @ClassName DataGetter
 * @Author Spark
 * @Date 2019/4/23 18:43
 * @Description TODO
 * @Version v1.2
 **/
public class DataGetter {

    JdbcHandler jdbcHandler = new JdbcHandler();

    private static Logger log  =  Logger.getLogger(DataGetter.class );


    /**
     * 获取当天交易流水
     *
     * @return
     * @throws Exception
     */
    public ArrayList<CbsBankAcctTradeDetail> getCurryDataArray() throws Exception {
        ArrayList<CbsBankAcctTradeDetail> list = jdbcHandler.findTransInfo(SqlReader.getSearchSql());
        log.info("本次获取当日累计流水记录数 = " + list.size());
        return this.dateFilter(list);
    }

    /**
     * 获取前n天到今天已保存的交易记录id号
     *
     * @return existedTransactionSQL
     * @throws Exception
     */
    private ArrayList<CbsBankAcctTradeDetail> getExistedTransIds() throws Exception {
        String existedTransactionSQL = SqlReader.getExistedInfoSql() + "'" + DateUtil.getLastDay(1) + "'";
        ArrayList<CbsBankAcctTradeDetail> list = jdbcHandler.findExistedInfo(existedTransactionSQL);
        log.info("本次获取当日已处理流水记录数 = " + list.size());
        return list;
    }



    /**
     * 过滤掉已经处理的流水
     *
     * @param transactionList
     * @return ArrayList<JyOrderCbsBusinessTransaction>
     */
    private ArrayList<CbsBankAcctTradeDetail> dateFilter(ArrayList<CbsBankAcctTradeDetail> transactionList) throws Exception {
        if(transactionList == null || transactionList.isEmpty()){
            log.info("本次任务没有要处理的数据");
            return new ArrayList<CbsBankAcctTradeDetail>();
        }
        ArrayList<CbsBankAcctTradeDetail> existedTransList = this.getExistedTransIds();
        if(transactionList == null || transactionList.isEmpty()){
            log.info("本次任务累计需要处理流水记录数 = " + transactionList.size());
            return transactionList;
        }

        ArrayList<CbsBankAcctTradeDetail> result = new ArrayList<CbsBankAcctTradeDetail>();
        for (CbsBankAcctTradeDetail cbsTrans : transactionList) {
            boolean notExisted = true;
            for (CbsBankAcctTradeDetail existedTrans : existedTransList) {
                //如果cbs中trans_id和业务表中已经存在trans_id相同
                if (cbsTrans.getTransId().intValue() == existedTrans.getTransId().intValue()) {
                    notExisted = false;
                    break;
                }
            }
            if (notExisted) {
                log.info("未处理流水：" + cbsTrans.getTransId());
                result.add(cbsTrans);
            }
        }
        log.info("本次任务累计需要处理流水记录数 = " + result.size());
        return result;
    }
}
