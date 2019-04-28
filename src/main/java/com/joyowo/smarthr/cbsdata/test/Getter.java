package com.joyowo.smarthr.cbsdata.test;

import java.util.ArrayList;

/**
 * @ClassName Getter
 * @Author Spark
 * @Date 2019/4/24 22:02
 * @Description TODO
 * @Version v1.2
 **/
public class Getter {
    TestJdbcHandler jdbcHandler = new TestJdbcHandler();

    private String perm_sql = "select \n" +
            "\t\ta.id,a.`role_menu_action_id`,b.id as user_id,a.`range_type`, \n" +
            "\t\ta.`bus_table`, a.`bus_table_column`, a.`create_time`, a.`create_uid`\n" +
            "\tfrom `smarthr-tenancy`.`tnt_perm` a , `smarthr-privilege`.`user` b \n" +
            "\twhere a.user_id=%d\n" +
            "\tand b.user_account in(%s)";

    private String detail_sql = "select c.id, c.perm_id, c.bus_id from `smarthr-tenancy`.`tnt_perm_detail` c \n" +
            "\twhere c.perm_id in (%s)";

    public ArrayList<Perm> findPerm(Long userId, String mobiles) throws Exception {
        perm_sql = String.format(perm_sql, userId, mobiles);
        return jdbcHandler.findPerm(perm_sql);
    }

    public ArrayList<Detail> findDetail(String ids) throws Exception {
        detail_sql = String.format(detail_sql, ids);
        return jdbcHandler.findPermDetail(detail_sql);
    }
}
