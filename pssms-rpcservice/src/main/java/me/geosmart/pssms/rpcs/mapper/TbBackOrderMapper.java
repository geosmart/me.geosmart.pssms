package me.geosmart.pssms.rpcs.mapper;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;

/**
 * <p>
  * 退货单新增记录 Mapper 接口
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface TbBackOrderMapper extends BaseMapper<TbBackOrder> {
    /**
     * 未用退单
     */
    List selectBackOrderByStatus(@Param("orderDateBegin") Date orderDateBegin, @Param("orderDateEnd") Date orderDateEnd);

}