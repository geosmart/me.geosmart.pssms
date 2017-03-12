package me.geosmart.pssms.rpcs.service;

import com.baomidou.mybatisplus.service.IService;

import java.sql.Date;

import me.geosmart.pssms.rpcs.entity.TbBackOrderLog;

/**
 * <p>
 * 退货单使用记录 服务类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
public interface ITbBackOrderLogService extends IService<TbBackOrderLog> {

    /**
     * 总退货单使用金额
     */
    Double selectSumBackAmount(Date orderDateBegin, Date orderDateEnd);
}
