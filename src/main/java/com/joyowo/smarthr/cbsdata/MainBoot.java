package com.joyowo.smarthr.cbsdata;

import com.joyowo.smarthr.cbsdata.entity.CbsBankAcctTradeDetail;
import com.joyowo.smarthr.cbsdata.exec.DataGetter;
import com.joyowo.smarthr.cbsdata.exec.DataSaver;
import com.joyowo.smarthr.cbsdata.property.SqlReader;
import com.joyowo.smarthr.cbsdata.util.GsonUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * @author Spark
 * @Create 2019/4/23
 */
public class MainBoot {

    private static Logger log  =  Logger.getLogger(MainBoot.class );

    public static void main(String[ ] arg) {
        DataGetter dataGetter =new DataGetter();
        DataSaver dataSaver = new DataSaver();

        do{
            try{
                //处理实时数据
                log.info("=====================处理当日交易==开始======================");
                ArrayList<CbsBankAcctTradeDetail> dataList = dataGetter.getCurryDataArray();
                if( dataList.size() > 0 ){
//                    String datas = GsonUtil.toJson(dataList);
//                    System.out.println(datas);
                    dataSaver.executeData(dataList);
                }
                log.info("=====================处理当日交易==结束======================");
            }catch (Exception e){
                log.error("=====================处理当日交易==结束======================出现错误：" + e.getMessage());
                e.printStackTrace();
            }
            try{
                Thread.sleep(SqlReader.getTime()*60*1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }while (true);

    }
}
