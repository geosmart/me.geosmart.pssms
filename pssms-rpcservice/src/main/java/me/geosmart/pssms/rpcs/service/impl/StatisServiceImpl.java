package me.geosmart.pssms.rpcs.service.impl;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.rpcs.service.IStatisService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;
import me.geosmart.pssms.rpcs.service.ITbBackProductService;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

/**
 * <p>
 * 数据统计服务
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Service
public class StatisServiceImpl implements IStatisService {

    @Autowired
    private ITbSaleOrderService saleOrderService;

    @Autowired
    private ITbBackProductService backProductService;

    @Autowired
    private ITbBackOrderService backOrderService;

    @Autowired
    private ITbBackOrderLogService backOrderLogService;

    @Override
    public JSONObject statisSaleAmount() throws Exception {
        JSONObject result = new JSONObject();

        List<Map> resultMap = saleOrderService.groupByProduct();
        resultMap.forEach(item -> {
            String key = null;
            Object value = null;
            Iterator it = item.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                if (entry.getKey().equals("product_code")) {
                    key = (String) entry.getValue();
                } else {
                    value = entry.getValue();
                }
            }
            result.put(key, value);
        });
        return result;
    }

    @Override
    public JSONObject statisBackRate() throws Exception {
        JSONObject result = new JSONObject();
        return result;
    }

    @Override
    public JSONObject statisBackOrder() throws Exception {
        JSONObject result = new JSONObject();
        return result;
    }
}
