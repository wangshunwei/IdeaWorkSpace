package cn.itcast.rabbit.rabbit;


import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * RabbitMq配置类
 *
 */
@Component
@ConfigurationProperties(prefix = "rmq")// 读取配置文件对参数进行初始化
public class RabbitConfig {


    private Map<String,String> ip;
    private Map<String,String> port;
    private Map<String,String> username;
    private Map<String,String> password;
    private Map<String,String> virtualHost;

    public Map<String, String> getIp() {
        return ip;
    }

    public void setIp(Map<String, String> ip) {
        this.ip = ip;
    }

    public Map<String, String> getPort() {
        return port;
    }

    public void setPort(Map<String, String> port) {
        this.port = port;
    }

    public Map<String, String> getUsername() {
        return username;
    }

    public void setUsername(Map<String, String> username) {
        this.username = username;
    }

    public Map<String, String> getPassword() {
        return password;
    }

    public void setPassword(Map<String, String> password) {
        this.password = password;
    }

    public Map<String, String> getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(Map<String, String> virtualHost) {
        this.virtualHost = virtualHost;
    }

    /**
     * 配置链接
     */

    @Bean(name = "specialLineConnectionFactory")
    public ConnectionFactory testConnectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        String clusterIps = ip.get("specialLine");
        String[] ips = clusterIps.split(" *, *");
        for (String ipAddress : ips) {
            connectionFactory.setAddresses(ipAddress + ":" + port.get("specialLine"));
        }
        connectionFactory.setUsername(username.get("specialLine"));
        connectionFactory.setPassword(password.get("specialLine"));
        connectionFactory.setVirtualHost(virtualHost.get("specialLine"));
        // 开启确认,当消息发送到交换机,回回调confirm()方法
        connectionFactory.setPublisherConfirms(true);
        //回调returnedMessage()方法
        connectionFactory.setPublisherReturns(true);
        return null;

    }

    /**
     *
     * 配置客户端
     *
     */
    @Bean(name = "specialineRabbitTemplate")
    @Scope("protoType")// returnedCallback  conformsCallback  一个
    public RabbitTemplate testRabbitTemplate(@Qualifier("specialLineConnectionFactory") ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;

    }

    /**
     *
     *
     * 配置监听
     *
     * 根据boot的建议可以再次创建一个连接工厂给消费者使用
     *
     */
    @Bean(name = "specialineListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory testListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configure,
                @Qualifier("specialLineConnectionFactory")ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configure.configure(factory,connectionFactory);
        return factory;
    }
}
