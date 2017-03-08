package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.entity.TbUser;
import me.geosmart.pssms.rpcs.service.ITbUserService;

import static org.junit.Assert.assertNotNull;

/**
 * UnitTest: IUserService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApplication.class)
public class UserServiceTest {
    Logger logger = Logger.getLogger(UserServiceTest.class.getName());

    @Autowired
    private ITbUserService userService;

    @Test
    public void test_insert() {
        TbUser user = new TbUser();
        user.setId(1L);
        user.setName("geosmart");
        user.setEmail("geosmart@foxmail.com");
        userService.insert(user);
        TbUser findUser = userService.selectById(user.getId());
        assertNotNull(findUser);
    }

    @Test
    public void test_findById() {
        TbUser user = userService.selectById(1L);
        logger.debug(user.getEmail());
        assertNotNull(user);
    }

    @Test
    public void test_update() {
        TbUser user = new TbUser();
        user.setId(1L);
        user.setName("geosmart");
        user.setEmail("geosmart@qq.com");
        userService.updateById(user);
    }

    @Test
    public void test_delete() {
        userService.deleteById(1L);
    }
}
