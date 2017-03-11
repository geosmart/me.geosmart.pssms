package me.geosmart.pssms.rpcs.service;

import com.alibaba.fastjson.JSONObject;

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
    JSONObject statisSaleAmount() throws Exception;


    /**
     * 统计各货号退货率
     */
    JSONObject statisBackRate() throws Exception;


    /**
     * 统计未用退单金额
     */
    JSONObject statisBackOrder() throws Exception;
}
