package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import me.geosmart.pssms.rpcs.SpringbootApp;
import me.geosmart.pssms.rpcs.entity.TbCustomer;
import me.geosmart.pssms.rpcs.mapper.TbSaleOrderMapper;
import me.geosmart.pssms.rpcs.service.ITbCustomerService;

/**
 * UnitTest: IUserService
 *
 * @author geosmart
 * @date 2017-3-8
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootApp.class)
public class CustomerServiceTest {
    Logger logger = Logger.getLogger(CustomerServiceTest.class.getName());

    @Autowired
    private TbSaleOrderMapper saleOrderMapper;

    @Autowired
    private ITbCustomerService customerService;

    @Test
    public void test_insert() {
        List<Map> customerCodes = saleOrderMapper.getCustomerCode();
        for (Map customerData : customerCodes) {
            TbCustomer customer = new TbCustomer();
            customer.setCustomerName((String) customerData.get("customer_code"));
            customerService.insert(customer);
        }
    }
}
