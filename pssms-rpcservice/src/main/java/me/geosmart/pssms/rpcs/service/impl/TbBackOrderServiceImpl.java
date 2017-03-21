package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.lang.StringUtils;
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

    @Override
    public  Page<TbBackOrder> queryBackOrder(int pageNumber, int pageSize, Date beginDate, Date endDate, String customerCode, String backOrderStatus,String backOrderId) {
        Page<TbBackOrder> pager = new Page<TbBackOrder>(pageNumber, pageSize);
        Wrapper<TbBackOrder> ew = new EntityWrapper<>();
        if (StringUtils.isNotBlank(backOrderStatus)) {
            ew.eq("back_order_status", backOrderStatus);
        }
        if (StringUtils.isNotBlank(backOrderId)) {
            ew.like("back_order_id", backOrderId);
        }
        if (StringUtils.isNotBlank(customerCode)) {
            ew.eq("customer_code", customerCode);
        }
        if (beginDate != null) {
            ew.andNew("order_date >= '{0}'", beginDate);
        }
        if (endDate != null) {
            ew.andNew("order_date <= '{0}'", endDate);
        }
        ew.orderBy(" order_date desc");
        pager.setRecords(baseMapper.selectPage(pager, ew));
        return pager;
    }
}
