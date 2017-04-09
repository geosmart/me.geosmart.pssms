package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.mapper.TbSaleOrderMapper;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

/**
 * <p>
 * 货品销售记录 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Component
public class TbSaleOrderServiceImpl extends ServiceImpl<TbSaleOrderMapper, TbSaleOrder> implements ITbSaleOrderService {

    @Override
    public Page<TbSaleOrder> querySaleOrder(int pageNumber, int pageSize, Date orderDateBegin, Date orderDateEnd, String orderType, String product_code, String customerCode) {
        Page<TbSaleOrder> pager = new Page<TbSaleOrder>(pageNumber, pageSize);
        Wrapper<TbSaleOrder> ew = new EntityWrapper<>();
        if (StringUtils.isNotBlank(orderType)) {
            ew.eq("order_Type", orderType);
        }
        if (StringUtils.isNotBlank(product_code)) {
            ew.like("product_code", product_code);
        }
        if (StringUtils.isNotBlank(customerCode)) {
            ew.eq("customer_code", customerCode);
        }
        if (orderDateBegin != null) {
            ew.andNew("order_date >= '{0}'", orderDateBegin);
        }
        if (orderDateEnd != null) {
            ew.andNew("order_date <= '{0}'", orderDateEnd);
        }
        ew.orderBy(" order_date desc");
        pager.setRecords(baseMapper.selectPage(pager, ew));
        return pager;
    }


    @Override
    public List groupOrderNumByCustomer(Date orderDateBegin, Date orderDateEnd, String orderType, String customerCode) {
        return baseMapper.groupOrderNumByCustomer(orderDateBegin, orderDateEnd, orderType, customerCode);
    }

    @Override
    public List<Map> groupAmountByProduct(Date orderDateBegin, Date orderDateEnd, String orderType) {
        return baseMapper.groupAmountByProduct(orderDateBegin, orderDateEnd, orderType);
    }

    @Override
    public Double selectSumSaleAmount(Date orderDateBegin, Date orderDateEnd, String orderType) {
        return baseMapper.selectSumSaleAmount(orderDateBegin, orderDateEnd, orderType);
    }

    @Override
    public List groupNumberByProduct(Date orderDateBegin, Date orderDateEnd, String orderType) {
        return baseMapper.groupNumberByProduct(orderDateBegin, orderDateEnd, orderType);
    }
}
