package me.geosmart.pssms.client.front.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;

import me.geosmart.pssms.Application;
import me.geosmart.pssms.client.front.service.IDataExchangeService;
import me.geosmart.pssms.client.front.service.IStatisService;
import me.geosmart.pssms.rpcs.util.DateUtil;

/**
 * UnitTest: ISaleOrderService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class StatisServiceImplTest {
    Logger logger = Logger.getLogger(StatisServiceImplTest.class.getName());

    @Autowired
    private IStatisService statisService;

    @Autowired
    private IDataExchangeService dataExchangeService;

    @Test
    public void test_export() throws Exception {
        String templatePath = "D:\\WorkSpace\\进销存系统\\excel数据\\销售退货数据统计模版.xlsx";
        String exportPath = "D:\\WorkSpace\\进销存系统\\excel数据\\201702销售退货数据统计（系统生成）.xlsx";
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        statisService.exportStatisResult(sDate, eDate, templatePath, exportPath);
    }

    @Test
    public void test_statisSaleAmount() throws Exception {
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        JSONObject findOrder = statisService.statisSaleAmount(sDate, eDate, "1");
        System.out.println(JSON.toJSONString(findOrder, true));
    }

    @Test
    public void test_statisBackRate() throws Exception {
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        JSONObject findOrder = statisService.statisBackRate(sDate, eDate);
        System.out.println(JSON.toJSONString(findOrder, true));
    }

    @Test
    public void test_statisBackOrder() throws Exception {
        Date sDate = DateUtil.getDate("2017-02-01");
        Date eDate = DateUtil.getDate("2017-03-01");
        JSONArray findOrder = statisService.statisBackOrder(sDate, eDate, "0");
        System.out.println(JSON.toJSONString(findOrder, true));
    }
}
