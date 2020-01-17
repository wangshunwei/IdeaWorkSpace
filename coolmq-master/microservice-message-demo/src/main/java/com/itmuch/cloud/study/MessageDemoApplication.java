package com.itmuch.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author fw
 */

@SpringBootApplication(scanBasePackages = {"com.coolmq.amqp","com.itmuch.cloud.study"})
@EnableAspectJAutoProxy(proxyTargetClass=true)//使用Aspect方式使用AOP cglib方式
public class MessageDemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(MessageDemoApplication.class, args);
  }
}
