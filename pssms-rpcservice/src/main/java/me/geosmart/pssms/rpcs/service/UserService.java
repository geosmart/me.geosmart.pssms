package me.geosmart.pssms.rpcs.service;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

import me.geosmart.pssms.rpcs.dao.UserDao;
import me.geosmart.pssms.rpcs.domain.User;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public int insert(User pojo) {
        return userDao.insert(pojo);
    }

    public int insertSelective(User pojo) {
        return userDao.insertSelective(pojo);
    }

    public int insertList(List<User> pojos) {
        return userDao.insertList(pojos);
    }

    public int update(User pojo) {
        return userDao.update(pojo);
    }

    public User findById(Long id) {
        return userDao.findById(id);
    }

    public int deleteById(Long id) {
        return userDao.deleteById(id);
    }

}
