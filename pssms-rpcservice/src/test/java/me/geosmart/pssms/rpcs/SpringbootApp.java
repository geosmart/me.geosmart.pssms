package me.geosmart.pssms.rpcs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Siva
 */

@SpringBootApplication
@MapperScan("me.geosmart.pssms.rpcs.mapper*")
public class SpringbootApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApp.class, args);
    }
}
