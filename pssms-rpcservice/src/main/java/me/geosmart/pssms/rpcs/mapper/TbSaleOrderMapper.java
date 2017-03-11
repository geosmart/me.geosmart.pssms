package me.geosmart.pssms.rpcs.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;

/**
 * <p>
 * 货品销售记录 Mapper 接口
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface TbSaleOrderMapper extends BaseMapper<TbSaleOrder> {
    List groupByProduct();
}