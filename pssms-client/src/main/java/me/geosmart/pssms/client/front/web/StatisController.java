package me.geosmart.pssms.client.front.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Map;

import me.geosmart.pssms.client.front.service.IStatisService;
import me.geosmart.pssms.client.front.web.DTO.SaleOrderQueryDTO;

/**
 * <p>
 * 货品销售记录 前端控制器
 * </p>
 *
 * @author geosmart
 * @since 2017-03-23
 */
@RestController
@RequestMapping("/api/statis")
public class StatisController {

    @Autowired
    IStatisService statisService;

    /**
     * 统计各货号销售额，和总销售额
     */
    @RequestMapping(value = "/saleOrderStatis", method = RequestMethod.POST)
    public Map saleOrderStatis(@RequestBody SaleOrderQueryDTO input) throws Exception {
        Date beginDate = input.getBeginDate();
        Date endDate = input.getEndDate();
        String orderType = input.getOrderType();
        Map statisAmount = statisService.statisSaleAmount(beginDate, endDate, orderType).toJavaObject(Map.class);
        return statisAmount;
    }

    /**
     * 统计各货号退货率
     */
    @RequestMapping(value = "/backRateStatis", method = RequestMethod.POST)
    public Map backRateStatis(@RequestBody SaleOrderQueryDTO input) throws Exception {
        Date beginDate = input.getBeginDate();
        Date endDate = input.getEndDate();
        Map statisAmount = statisService.statisBackRate(beginDate, endDate).toJavaObject(Map.class);
        return statisAmount;
    }
}
