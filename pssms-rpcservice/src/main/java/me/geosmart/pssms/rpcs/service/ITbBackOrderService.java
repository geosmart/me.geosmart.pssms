package me.geosmart.pssms.rpcs.service;

import com.baomidou.mybatisplus.service.IService;

import java.sql.Date;
import java.util.List;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;

/**
 * <p>
 * 退货单新增记录 服务类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface ITbBackOrderService extends IService<TbBackOrder> {

    /**
     * 查询退单使用情况
     *
     * @param back_order_status 使用情况 （0-未使用，1-已使用）
     */
    List<TbBackOrder> selectBackOrderByStatus(Date orderDateBegin, Date orderDateEnd, String back_order_status);
}
