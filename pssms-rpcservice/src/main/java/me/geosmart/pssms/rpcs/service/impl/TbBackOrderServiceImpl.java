package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

import me.geosmart.pssms.rpcs.entity.TbBackOrder;
import me.geosmart.pssms.rpcs.mapper.TbBackOrderMapper;
import me.geosmart.pssms.rpcs.service.ITbBackOrderService;

/**
 * <p>
 * 退货单新增记录 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Component
public class TbBackOrderServiceImpl extends ServiceImpl<TbBackOrderMapper, TbBackOrder> implements ITbBackOrderService {

    @Override
    public List<TbBackOrder> selectBackOrderByStatus(Date orderDateBegin, Date orderDateEnd, String back_order_status) {
        EntityWrapper<TbBackOrder> ew = new EntityWrapper<TbBackOrder>() {
        };
        ew.where("back_order_status={0}", back_order_status).
                andNew("order_date >= '{0}' and order_date <= '{1}'", orderDateBegin, orderDateEnd)
                .orderBy("order_date desc");
        return baseMapper.selectList(ew);
    }

}
