package me.geosmart.pssms.client.front.web;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.client.front.web.DTO.SaleOrderQueryDTO;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

/**
 * <p>
 * 货品销售记录 前端控制器
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@RestController
@RequestMapping("/api/saleOrder")
public class SaleOrderController {

    @Autowired
    ITbSaleOrderService saleOrderService;

    /**
     * 分页查询
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Page querySaleOrder(@RequestBody SaleOrderQueryDTO input) {
        int pageNumber = input.getPageNumber();
        int pageSize = input.getPageSize();
        String orderType = input.getOrderType();
        String productCode = input.getProductCode();
        Date beginDate = input.getBeginDate();
        Date endDate = input.getEndDate();
        String customerCode = input.getCustomerCode();
        Page objs = saleOrderService.querySaleOrder(pageNumber, pageSize, beginDate, endDate, orderType, productCode, customerCode);
        List jsonObjs = JSON.parseArray(JSON.toJSONString(objs.getRecords()), Map.class);
        objs.setRecords(jsonObjs);
        return objs;
    }
}
