package me.geosmart.pssms.rpcs.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import me.geosmart.pssms.rpcs.domain.User;
import me.geosmart.pssms.rpcs.service.UserDao;

@Service
public class UserService{

    @Resource
    private UserDao userDao;

    public int insert(User pojo){
        return userDao.insert(pojo);
    }

    public int insertSelective(User pojo){
        return userDao.insertSelective(pojo);
    }

    public int insertList(List<User> pojos){
        return userDao.insertList(pojos);
    }

    public int update(User pojo){
        return userDao.update(pojo);
    }
}
