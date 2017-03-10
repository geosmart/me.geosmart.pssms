package me.geosmart.pssms.rpcs.util;

import org.apache.log4j.Logger;

import java.net.InetAddress;

/**
 * Package: com.udcredit.serial.service.impl
 * Description: SerialServiceImpl
 * Author: Eric(matao@udcredit.com)
 * Update: Eric(2016-08-24 12:34)
 */
public class SerialService {
    private final static Logger logger = Logger.getLogger(SerialService.class);

    public static Long newSerialId() throws Exception {
        SnowFlake snowFlake = new SnowFlake(new Worker().workId);
        return snowFlake.nextId();
    }

    static class Worker {
        long workId = Long.valueOf(InetAddress.getLocalHost().getHostAddress().replace(".", ""));

        Worker() throws Exception {
            this.workId = 1;
        }
    }
}
