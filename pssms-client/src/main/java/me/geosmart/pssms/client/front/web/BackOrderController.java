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

import me.geosmart.pssms.client.front.web.DTO.BackOrderQueryDTO;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;

/**
 * <p>
 * 退单使用记录 前端控制器
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@RestController
@RequestMapping("/api/backOrder")
public class BackOrderController {

    @Autowired
    ITbBackOrderService backOrderService;

    /**
     * 分页查询
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Page queryBackOrder(@RequestBody BackOrderQueryDTO input) {
        int pageNumber = input.getPageNumber();
        int pageSize = input.getPageSize();
        Date beginDate = input.getBeginDate();
        Date endDate = input.getEndDate();
        String customerCode = input.getCustomerCode();
        String backOrderStatus = input.getBackOrderStatus();
        String backOrderId = input.getBackOrderId();
        Page objs = backOrderService.queryBackOrder(pageNumber, pageSize, beginDate, endDate, customerCode, backOrderStatus, backOrderId);
        List jsonObjs = JSON.parseArray(JSON.toJSONString(objs.getRecords()), Map.class);
        objs.setRecords(jsonObjs);
        return objs;
    }
}
