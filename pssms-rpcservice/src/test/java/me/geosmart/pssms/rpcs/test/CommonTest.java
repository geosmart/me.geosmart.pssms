package me.geosmart.pssms.rpcs.test;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * UnitTest: IUserService
 *
 * @author geosmart
 * @date 2017-3-8
 */
public class CommonTest {
    Logger logger = Logger.getLogger(CommonTest.class.getName());

    @Test
    public void test_date() {
        Date date = new Date(1488211200000L);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println(formatter.format(date));
    }
}
