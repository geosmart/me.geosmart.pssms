package me.geosmart.pssms.rpcs.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Component;

import me.geosmart.pssms.rpcs.entity.TbUser;
import me.geosmart.pssms.rpcs.mapper.TbUserMapper;
import me.geosmart.pssms.rpcs.service.ITbUserService;

/**
 * <p>
 * `用户表` 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-11
 */
@Component
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {
	
}
