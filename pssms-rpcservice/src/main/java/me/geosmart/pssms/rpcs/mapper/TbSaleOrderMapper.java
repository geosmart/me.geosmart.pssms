package me.geosmart.pssms.rpcs.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
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
    /**
     * 每个货号的销售额/退货额
     */
    List groupAmountByProduct(@Param("orderDateBegin") Date orderDateBegin,
                              @Param("orderDateEnd") Date orderDateEnd,
                              @Param("orderType") String orderType);

    /**
     * 每个货号的销售件数
     */
    List groupNumberByProduct(@Param("orderDateBegin") Date orderDateBegin, @Param("orderDateEnd") Date orderDateEnd);

    /**
     * 总销售额
     */
    Double selectSumSaleAmount(@Param("orderDateBegin") Date orderDateBegin, @Param("orderDateEnd") Date orderDateEnd);
}