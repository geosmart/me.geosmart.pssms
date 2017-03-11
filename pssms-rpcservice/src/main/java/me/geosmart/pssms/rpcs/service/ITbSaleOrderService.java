package me.geosmart.pssms.rpcs.service;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;

import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品销售记录 服务类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface ITbSaleOrderService extends IService<TbSaleOrder> {
    List<Map> groupByProduct();
}
