package me.geosmart.pssms.client.front.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Date;

/**
 * <p>
 * 数据统计服务
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface IStatisService {


    /**
     * 统计各货号销售额，和总销售额
     */
    JSONObject statisSaleAmount(Date orderDateBegin, Date orderDateEnd, String orderType) throws Exception;


    JSONObject statisSaleAmount(Date orderDateBegin, Date orderDateEnd, String orderType, String customerCode) throws Exception;

    /**
     * 统计各货号退货率
     */
    JSONObject statisBackRate(Date orderDateBegin, Date orderDateEnd) throws Exception;


    /**
     * 统计未用退单
     */
    JSONArray statisBackOrder(Date orderDateBegin, Date orderDateEnd, String orderStatus) throws Exception;

    /**
     * 导出统计结果
     */
    void exportStatisResult(Date orderDateBegin, Date orderDateEnd, String templatePath, String exportPath) throws Exception;
}
