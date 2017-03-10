package me.geosmart.pssms.client.front.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import me.demo.pssms.client.front.service.IDataExchangeService;
import me.demo.pssms.client.front.service.impl.DataExchangeServiceImpl;
import me.geosmart.pssms.rpcs.SpringbootApplication;
import me.geosmart.pssms.rpcs.entity.TbSaleOrder;
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
public class DataExchangeServiceImplTest {
    Logger logger = Logger.getLogger(DataExchangeServiceImplTest.class.getName());

    private IDataExchangeService dataExchangeService;


    @Test
    public void test_importSaleOrder() throws IOException {
        String filePath="D:\\WorkSpace\\进销存系统\\excel数据\\可风各销售退货模板.xlsx";
        new DataExchangeServiceImpl().importSaleOrder(filePath);
    }

}
