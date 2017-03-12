package me.geosmart.pssms.rpcs.service;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import com.baomidou.mybatisplus.service.IService;

import java.sql.Date;
import java.util.List;

/**
 * <p>
 * 退货单新增记录 服务类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface ITbBackOrderService extends IService<TbBackOrder> {

    List<TbBackOrder> selectBackOrderByStatus(Date orderDateBegin, Date orderDateEnd, String back_order_status);
}
