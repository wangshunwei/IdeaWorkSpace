package cn.itcast.websocket.websocketconfig;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebSocketHandler extends TextWebSocketHandler {

    private static final Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    private static final Map<String, Thread> threads = new HashMap<String, Thread>();
    // 定义用户标识
    private static final String CLIENT_ID = "sessionId";

    /**
     * 接受到客户端发送的消息进行处理,然后可以返回数据给前端页面.接受前端数据处理,然后给前端发送消息再
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 获得线程
        Thread thread = threads.get(session.getId());
        if (thread != null) {
            System.out.println("handleTextMessage:" + session.getId());
            // 获得websocketSession中的session
            WebSocketSession webSocketSession = sessions.get(session.getId());
            if (webSocketSession == null) {
                // 要是session不存在,要存入到session中
                sessions.put(session.getId(),session);
            } else {
                session = webSocketSession;
            }
            // 处理前端发送过来的数据
            final WebSocketSession localSession = session;
            final TextMessage localMessage = message;
            String payLoad = message.getPayload();
            System.out.println("===============================");
            System.out.println(payLoad);
            System.out.println("===============================");
                        Thread thead = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                    // 进行业务的处理,然后给客户端进行发送消息
                                    Map<String,Object> map = new HashMap<String, Object>();
                                    map.put("data","服务端接受到客户端的消息,处理完成,返回客户端信息");
                                    String jsonString = JSONObject.toJSONString(map);
                                    // JSONObject jsonObject = JSONObject.parseObject(jsonString);
                                    TextMessage message = new TextMessage(jsonString);
                                    // 发送消息
                                    try {
                                        localSession.sendMessage(message);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                            }
                        });
                        // 开启线程
                        thread.start();
        }
        super.handleTextMessage(session, message);
    }

    /**
     * 需要根据业务进行参数的传递,这里直接返回就行.参数可以根据业务定义.这是后端主动给前端发送数据
     * @param sessionId
     */
    public void sendMessageToClient(String  sessionId) throws IOException {
        WebSocketSession webSocketSession = sessions.get(sessionId);
        HashMap<String,String> map = new HashMap<>();
        map.put("b","服务端主动给客户端进行数据的发送");
        String jsonString = JSONObject.toJSONString(map);
        TextMessage message = new TextMessage(jsonString);
        // 发送信息
        webSocketSession.sendMessage(message);
    }




    /**
     * 建立连接之后,调用此方法
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 获得websocketsesession中的数据
        Map<String, Object> attributes = session.getAttributes();
        Object mall = attributes.get("mall");

        if (mall != null && "mall".equals(mall)) {
            // 把websocketsession存入map中,固定字符串作为key
            sessions.put("CLIENT_ID", session);
            // 把websocketsession存入map中,sessionid作为key
            sessions.put(session.getId(), session);
            System.out.println("连接建立");
        }
        super.afterConnectionEstablished(session);
    }

    /**
     * 连接关闭之后调用方法
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Thread thread = threads.get(session.getId());
        if (thread != null) {
            thread.interrupt();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 关闭之后清除session
        sessions.remove(session.getId());
        sessions.remove(CLIENT_ID);
        System.out.println("连接关闭");
        super.afterConnectionClosed(session, status);
    }
}
