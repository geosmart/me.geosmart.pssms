package me.geosmart.pssms.rpcs.service.impl;

import me.geosmart.pssms.rpcs.entity.TbUser;
import me.geosmart.pssms.rpcs.mapper.TbUserMapper;
import me.geosmart.pssms.rpcs.service.ITbUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * `用户表` 服务实现类
 * </p>
 *
 * @author geosmart
 * @since 2017-03-08
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {
	
}
