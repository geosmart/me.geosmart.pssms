package me.geosmart.pssms.client.front.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;
import me.geosmart.pssms.rpcs.util.DateUtil;

/**
 * <p>
 * 货品销售记录 前端控制器
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@RestController
@RequestMapping("/demo")
public class RestSample {

    @Autowired
    private ITbSaleOrderService saleOrderService;

    /**
     * 分页查询
     */
    @RequestMapping("/test")
    public List<TbSaleOrder> test3() {
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        return saleOrderService.querySaleOrder(sDate, eDate, "1", "123");
    }
}
