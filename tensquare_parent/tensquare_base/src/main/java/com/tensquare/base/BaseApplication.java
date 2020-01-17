package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import utils.IdWorker;

@SpringBootApplication
// 声明此服务为客户端
@EnableEurekaClient
@EnableDiscoveryClient
public class BaseApplication {

    public static void main(String[] args) {

        SpringApplication.run(BaseApplication.class,args);

    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

}
