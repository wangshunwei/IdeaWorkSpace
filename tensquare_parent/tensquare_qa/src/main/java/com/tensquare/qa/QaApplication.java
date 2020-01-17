package com.tensquare.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class QaApplication {

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class, args);
	}

	/*@Bean
	public IdWorker idWorkker(){
		return new IdWorker(1, 1);
	}
	@EnableEurekaClient
@EnableDiscoveryClient   // 发现客户端注解,远程调用的注解
@EnableFeignClients      // 远程调用的注解
	*/

}
