package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

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
public class SaleOrderServiceTest {
    Logger logger = Logger.getLogger(SaleOrderServiceTest.class.getName());

    @Autowired
    private ITbSaleOrderService saleOrderService;

    @Test
    public void test_insert() {
        TbSaleOrder sale = new TbSaleOrder();
        sale.setOrderId(UUID.randomUUID().toString());
        sale.setProductCode("6021");
        sale.setNumber(2);
        sale.setPrice(45D);
        sale.setAmount(2 * 45D);
        saleOrderService.insert(sale);
    }

    @Test
    public void test_selectById() {
        TbSaleOrder findOrder = saleOrderService.selectById("479ced03-1d3e-44c3-873d-a3c7044d3fdb");
        assertNotNull(findOrder);
    }

    @Test
    public void test_delete() {
        saleOrderService.deleteById("479ced03-1d3e-44c3-873d-a3c7044d3fdb");
    }
}
