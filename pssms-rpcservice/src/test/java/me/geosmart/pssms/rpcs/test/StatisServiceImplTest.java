package me.geosmart.pssms.rpcs.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
import me.geosmart.pssms.rpcs.service.IStatisService;
import me.geosmart.pssms.rpcs.service.ITbSaleOrderService;

import static org.junit.Assert.assertNotNull;

/**
 * UnitTest: ISaleOrderService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApplication.class)
public class StatisServiceImplTest {
    Logger logger = Logger.getLogger(StatisServiceImplTest.class.getName());

    @Autowired
    private IStatisService statisService;

    @Test
    public void test_groupProduct() throws Exception {
        JSONObject findOrder = statisService.statisSaleAmount();
        System.out.println(JSON.toJSONString(findOrder,true));
    }

}
