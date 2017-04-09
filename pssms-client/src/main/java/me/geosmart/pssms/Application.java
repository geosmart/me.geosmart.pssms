package me.geosmart.pssms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import me.geosmart.pssms.client.front.web.config.MvcConfiguration;

/**
 * @author geosmart
 */
@SpringBootApplication(exclude = MvcConfiguration.class)
@MapperScan("me.geosmart.pssms.rpcs.mapper*")
//@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
