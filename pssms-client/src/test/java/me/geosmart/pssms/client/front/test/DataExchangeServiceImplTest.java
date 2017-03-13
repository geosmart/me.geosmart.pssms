package me.geosmart.pssms.client.front.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.geosmart.pssms.Application;
import me.geosmart.pssms.client.front.service.IDataExchangeService;

/**
 * UnitTest: ISaleOrderService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class DataExchangeServiceImplTest {
    Logger logger = Logger.getLogger(DataExchangeServiceImplTest.class.getName());
    @Autowired
    private IDataExchangeService dataExchangeService;

    @Test
    public void test_importData() throws Exception {
        //    String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\销售退货模板.xlsx";
        String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\201702可风各销售明细.xlsx";
        dataExchangeService.importSaleOrder(filePath);
        dataExchangeService.importBackOrder(filePath);
        dataExchangeService.importBackOrderLog(filePath);
    }

    @Test
    public void test_importSaleOrder() throws Exception {
        String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\201702可风各销售明细.xlsx";
        dataExchangeService.importSaleOrder(filePath);
    }

    @Test
    public void test_importBackOrder() throws Exception {
        String filePath = "D:\\WorkSpace\\进销存系统\\excel数据\\201702可风各销售明细.xlsx";
        dataExchangeService.importBackOrder(filePath);
    }
}
