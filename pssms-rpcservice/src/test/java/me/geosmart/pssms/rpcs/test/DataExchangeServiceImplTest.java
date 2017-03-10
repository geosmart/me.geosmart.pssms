package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.service.IDataExchangeService;

/**
 * UnitTest: ISaleOrderService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApplication.class)
public class DataExchangeServiceImplTest {
    Logger logger = Logger.getLogger(DataExchangeServiceImplTest.class.getName());

    @Autowired
    private IDataExchangeService dataExchangeService;
//    String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\销售退货模板.xlsx";

    @Test
    public void test_importSaleOrder() throws Exception {
        String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\201702可风各销售明细.xlsx";
        dataExchangeService.importSaleOrder(filePath);
    }

}
