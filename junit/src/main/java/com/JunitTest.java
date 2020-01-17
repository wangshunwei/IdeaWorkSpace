package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages="com.junit.dao")
@ComponentScan(basePackages = {"com.junit"})
@MapperScan("com.junit.dao")
public class JunitTest {
    public static void main(String[] args) {
        SpringApplication.run(JunitTest.class,args);
    }
}
