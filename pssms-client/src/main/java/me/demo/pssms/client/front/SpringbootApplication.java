package me.demo.pssms.client.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Siva
 */

@SpringBootApplication
@MapperScan("me.geosmart.pssms.rpcs.mapper")
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"me.demo.pssms.client.front.service"})
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }
}
