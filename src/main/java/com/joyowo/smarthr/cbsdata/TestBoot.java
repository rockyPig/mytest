package com.joyowo.smarthr.cbsdata;

import com.joyowo.smarthr.cbsdata.jdbc.JdbcHandler;
import com.joyowo.smarthr.cbsdata.test.Detail;
import com.joyowo.smarthr.cbsdata.test.Getter;
import com.joyowo.smarthr.cbsdata.test.Perm;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * @ClassName TestBoot
 * @Author Spark
 * @Date 2019/4/24 21:47
 * @Description TODO
 * @Version v1.2
 **/
public class TestBoot {
    //运营经理/总监
//    private static String[] mobiles = {"15064105232","15253226100","18905321612","18660293126","18351880279","15651071981","15003872285","18039295691","15882433193","13550232353","15884410259","17774901214","15915814423","15813367882","15014157517","15268579307","18936878288","18710560811","18841261756","15840858261","15142300270","15979433667","18817931057","13802268804","18565655605","15936981801","15827183422","15902777456","18258508011","13601157492","13517232724","13588052003","17757421705","13951088910","13718754989","17631179513","18601358782","13520218620","18910100360","18600052768","13321196963","13207692980","13370057107","18220543192","13772468731","15057453933","18156022655","13957843052"};
//    private static Long userId = 1577L;
//    private static Long permId = 100000000000000000L;
//    private static Long detailId = 200000000000000000L;

    //业管法务
//    private static String[] mobiles = {"17501681703","18709897727","15510092865","15063958172","13450389503","13921297122","13924155795"};
//    private static Long userId = 102584L;
//    private static Long permId = 110000000000000000L;
//    private static Long detailId = 210000000000000000L;

    //业管销售支持
//    private static String[] mobiles = {"13868132400","15868118348","13335717590","18510111027"};
//    private static Long userId = 14L;
//    private static Long permId = 120000000000000000L;
//    private static Long detailId = 220000000000000000L;

    /**
     * 业管销售支持
     */
    private static String[] mobiles = {"13358007700","18677196675","18100692857","18606277133","15895563501","15221301675","18801734698","18301720832","18701505243","18352533670","18451053677","13770617646","18251100814","13952053574","18151527585","15801828458","15905152837","13621773247","15800768753","18705143283","17321541052","18356638030","13776018756","18013568880","13816218171","13501853725","16602113496","17621654122","15882696536","17317190570","13922248935","13971295712","13250734369","18011704234","18040509166","18578986657","15913120818","13797000817","18814143993","17724521216","13250315997","13660037688","17557289713","13713922205","13794465376","15913162741","18978801034","13670071252","13610320938","18376664401","15940919972","15242548113","13511057684","18501921247","15809215131","13752408477","18842684421","15810342815","18511008964","16619890455","18603160418","18701628683","17791643265","18039338231","15202450900","15686046716","18611711721","15158864849","18968049022","18250716071","15757856849","15980738839","15888825304","15705593740","13819105693","18240268692","17794549174","13859349677","17855826820","13018931995","13588086520","15657502710","15955487240","13568821219","18697319968","18382006093","18561489545","17667506202","15154290772","13792879932","15882080997","18668937020","18224434645","18140238558","15653257107"};
    private static Long userId = 2741L;
    private static Long permId = 130000000000000000L;
    private static Long detailId = 230000000000000000L;


    private static Logger log  =  Logger.getLogger(TestBoot.class );

//    public static void main(String[] args) throws Exception{
    static void a ()throws Exception{
        StringBuffer ms = new StringBuffer();
        for(String mobile : mobiles){
            ms.append("'").append(mobile).append("'").append(",");
        }
        StringBuffer sql = new StringBuffer();
        StringBuffer detailSql = new StringBuffer();
        Getter getter = new Getter();
        ArrayList<Perm> list = getter.findPerm(userId, ms.deleteCharAt(ms.length() - 1).toString());

        String permIds = "";
        for(Perm perm : list){
            permIds += ","+perm.getId();
        }
        ArrayList<Detail> dlist = getter.findDetail(permIds.substring(1));


        for(Perm perm : list){
            permId ++;
            sql.append("insert into `smarthr-tenancy`.`tnt_perm` (`id`, `role_menu_action_id`, `user_id`, `range_type`,`bus_table`, `bus_table_column`, `create_time`, `create_uid`) values ( ");
            sql.append(permId).append(",");
            sql.append(perm.getRole_menu_action_id()).append(",");
            sql.append(perm.getUser_id()).append(",");
            sql.append(perm.getRange_type()).append(",");
            sql.append(perm.getBus_table() == null ? null : "'" + perm.getBus_table() + "'").append(",");
            sql.append(perm.getBus_table_column() == null ? null : "'" + perm.getBus_table_column() + "'").append(",");
            sql.append("now()").append(",");
            sql.append("1); \n");


            for(Detail detail : dlist) {
                if(perm.getId().longValue() == detail.getPerm_id().longValue()){
                    detailId ++;
                    detailSql.append("insert into `smarthr-tenancy`.`tnt_perm_detail` (`id`,`perm_id`,`bus_id`) values ( ");
                    detailSql.append(detailId).append(",");
                    detailSql.append(permId).append(",");
                    detailSql.append("'").append(detail.getBus_id()).append("');\n");
                }
            }

        }
        System.out.println(sql);
        System.out.println(detailSql);
        log.info(sql);
        log.info(detailSql);
    }


}
