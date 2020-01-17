package cn.itcast.websocket.websocketconfig;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 处理业务参数据的拦截器
 */
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    // 在建立连接之前,进行数据的处理可以
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 获得servlet的request,需要进行向下转型
        if (request instanceof ServletServerHttpRequest) {
            // 向下转型
            // 获得session
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();
            HttpSession session = httpServletRequest.getSession();
            // 获得session的id,就是通过cookie的key值
            String sessionId = session.getId();
            System.out.println("beforeShakeHand:" + sessionId);
            String type = httpServletRequest.getParameter("type");
            // 将sessionId的参数放入到websocketSession中
            attributes.put("mall",type);
            // 将sessionId以及其他的属性参数封装到websocektSession中
            return super.beforeHandshake(request,response,wsHandler,attributes);
        }


        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("afterShakeHand");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
