package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.apache.ibatis.session.RowBounds;
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
    public List querySaleOrder(Date orderDateBegin, Date orderDateEnd, String orderType, String product_code) {
        int pageNumber = 0;
        int pageSize = 10;
        int offset = pageNumber * pageNumber;
        RowBounds rowBounds = new RowBounds(offset, pageSize);
        Wrapper<TbSaleOrder> ew = new EntityWrapper<>();
        ew.like("order_Type", orderType).like("product_code", product_code).
                andNew("order_date >= '{0}' and order_date <= '{1}'", orderDateBegin, orderDateEnd)
                .orderBy("order_date desc");
        return baseMapper.selectPage(rowBounds, ew);
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
