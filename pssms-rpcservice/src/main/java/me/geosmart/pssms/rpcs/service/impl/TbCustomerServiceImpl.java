package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Component;

import me.geosmart.pssms.rpcs.entity.TbCustomer;
import me.geosmart.pssms.rpcs.mapper.TbCustomerMapper;
import me.geosmart.pssms.rpcs.service.ITbCustomerService;

/**
 * <p>
 * 客户表 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-04-09
 */
@Component
public class TbCustomerServiceImpl extends ServiceImpl<TbCustomerMapper, TbCustomer> implements ITbCustomerService {
	
}
