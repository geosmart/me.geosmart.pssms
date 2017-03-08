package me.geosmart.pssms.rpcs.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import me.geosmart.pssms.rpcs.domain.User;

@Mapper
public interface UserDao {
    int insert(@Param("pojo") User pojo);

    int insertSelective(@Param("pojo") User pojo);

    int insertList(@Param("pojos") List<User> pojo);

    int update(@Param("pojo") User pojo);

    int deleteById(@Param("id") Long id);

    User findById(@Param("id") Long id);
}
