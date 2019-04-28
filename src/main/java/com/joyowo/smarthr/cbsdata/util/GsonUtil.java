package com.joyowo.smarthr.cbsdata.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DecimalFormat;

/**
 * @ClassName GsonUtil
 * @Author Spark
 * @Date 2019/4/24 13:26
 * @Description TODO
 * @Version v1.2
 **/
public class GsonUtil {

    /**
     * @param isSerializeNulls
     *                不处理null字段
     * @param isDisableHtmlEscaping
     *                不处理url地址特殊符号
     * @return
     */
    private static Gson getInstants(boolean isSerializeNulls, boolean isDisableHtmlEscaping) {
        GsonBuilder builder = new GsonBuilder();

        if (isSerializeNulls) {
            builder.serializeNulls();
        }

        if (isSerializeNulls) {
            builder.disableHtmlEscaping();
        }

        Gson gson = builder.create();
        return gson;
    }

    /**
     * 对象转换成json
     *
     * @param bean
     * @return
     */
    public static String toJson(Object bean) {
        Gson gson = getInstants(false, false);
        return gson.toJson(bean);
    }
}
