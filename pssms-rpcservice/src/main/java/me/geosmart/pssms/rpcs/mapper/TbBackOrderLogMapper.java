package me.geosmart.pssms.rpcs.mapper;

import me.geosmart.pssms.rpcs.entity.TbBackOrderLog;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import org.apache.ibatis.annotations.Param;

import java.sql.Date;

/**
 * <p>
  * 退货单使用记录 Mapper 接口
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface TbBackOrderLogMapper extends BaseMapper<TbBackOrderLog> {
    /**
     * 总销售额
     */
    Double selectSumBackAmount(@Param("orderDateBegin") Date orderDateBegin, @Param("orderDateEnd") Date orderDateEnd);
}