package me.geosmart.pssms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author geosmart
 */
@SpringBootApplication
//@EnableAutoConfiguration
@MapperScan("me.geosmart.pssms.rpcs.mapper*")
//@ComponentScan("me.geosmart.pssms.rpcs.service")
//@ComponentScan("me.demo.pssms.client.front.service")
//@ComponentScan("me.demo.pssms.client.front.web")
@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
