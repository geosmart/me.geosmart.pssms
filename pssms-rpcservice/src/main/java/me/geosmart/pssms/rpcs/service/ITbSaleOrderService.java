package me.geosmart.pssms.rpcs.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;

/**
 * <p>
 * 货品销售记录 服务类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface ITbSaleOrderService extends IService<TbSaleOrder> {

    Page<TbSaleOrder> querySaleOrder(int pageNumber, int pageSize, Date orderDateBegin, Date orderDateEnd, String orderType, String product_code, String customerCode);

    /**
     * 每个货号的销售额
     */
    List<Map> groupAmountByProduct(Date orderDateBegin, Date orderDateEnd, String orderType);

    /**
     * 总销售额
     */
    Double selectSumSaleAmount(Date orderDateBegin, Date orderDateEnd, String orderType);

    /**
     * 每个货号的销售件数
     */
    List groupNumberByProduct(Date orderDateBegin, Date orderDateEnd, String orderType);


    /**
     * 每个客户-每个货号的销售件数
     */
    List groupOrderNumByCustomer(Date orderDateBegin, Date orderDateEnd, String orderType
            , String customerCode);

}
