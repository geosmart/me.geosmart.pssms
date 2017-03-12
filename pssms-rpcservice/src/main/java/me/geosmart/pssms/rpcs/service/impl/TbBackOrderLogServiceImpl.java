package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.sql.Date;

import me.geosmart.pssms.rpcs.entity.TbBackOrderLog;
import me.geosmart.pssms.rpcs.mapper.TbBackOrderLogMapper;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;

/**
 * <p>
 * 退货单使用记录 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Service
public class TbBackOrderLogServiceImpl extends ServiceImpl<TbBackOrderLogMapper, TbBackOrderLog> implements ITbBackOrderLogService {

    @Override
    public Double selectSumBackAmount(Date orderDateBegin, Date orderDateEnd) {
        return baseMapper.selectSumBackAmount(orderDateBegin, orderDateEnd);
    }
}
