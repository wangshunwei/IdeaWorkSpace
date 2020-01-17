package cn.itcast.websocket.websocketconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 * websocket配置类
 */

@Configuration
@EnableWebSocket
@EnableWebMvc // 完全控制mvc配置
public class WebsocketConfig implements WebSocketConfigurer {


    // 添加自己的拦截器和处理器
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler(), "/myHandler").addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("http://mydomain.com");
        registry.addHandler(myHandler(),"/myHandler/info").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }

    // 对于tomcat进行配置
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        container.setMaxSessionIdleTimeout(3000L);
        return container;
    }
    // 声明处理器,返回的是自己定义的处理器不是,spring中的.
    @Bean
    public WebSocketHandler myHandler() {
        return new WebSocketHandler();
    }


}
