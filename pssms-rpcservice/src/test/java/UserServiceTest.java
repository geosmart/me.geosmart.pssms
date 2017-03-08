import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.domain.User;
import me.geosmart.pssms.rpcs.service.UserService;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Think on 2017/3/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApplication.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test_insertUser() {
        User user = new User(1L, "geosmart", "geosmart@foxmail.com");
        userService.insert(user);
    }

    @Test
    public void test_getUsers() {
        User user = userService.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void test_updateUser() {
        User user = new User(1L, "geosmart", "geosmart@qq.com");
        userService.update(user);
    }

    @Test
    public void test_deleteUser() {
        userService.deleteById(1L);
    }
}
