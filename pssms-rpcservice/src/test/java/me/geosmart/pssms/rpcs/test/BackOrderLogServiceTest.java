package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.service.ITbBackOrderLogService;
import me.geosmart.pssms.rpcs.util.DateUtil;

import static org.junit.Assert.assertNotNull;

/**
 * UnitTest: ISaleOrderService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApplication.class)
public class BackOrderLogServiceTest {
    Logger logger = Logger.getLogger(BackOrderLogServiceTest.class.getName());

    @Autowired
    private ITbBackOrderLogService backOrderLogService;

    @Test
    public void test_selectSumSaleAmount() throws Exception {
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        Double amount = backOrderLogService.selectSumBackAmount(sDate, eDate);
        System.out.println(amount);
        assertNotNull(amount);
    }
}
