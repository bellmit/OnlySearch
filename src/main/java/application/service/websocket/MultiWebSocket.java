package application.service.websocket;

import net.sf.json.JSONObject;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 处理浏览器和服务器使用ws协议进行连接
 */
@ServerEndpoint("/webSocket/{username}")
public class MultiWebSocket {
    private static int onlineCount = 0;
    private static Map<String, MultiWebSocket> clients = new ConcurrentHashMap<String, MultiWebSocket>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");
    }

    @OnClose
    public void onClose() throws IOException {
        clients.remove(username);
        subOnlineCount();
    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        JSONObject jsonTo = JSONObject.fromObject(message);
        String mes = (String) jsonTo.get("message");
        if (!jsonTo.get("To").equals("All")) {
            sendMessageTo(mes, jsonTo.get("To").toString());
        } else {
            sendMessageAll(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessageTo(String message, String To) throws IOException {
        for (MultiWebSocket multiWebSocket : clients.values()) {
            if (multiWebSocket.username.equalsIgnoreCase(To)) {
                multiWebSocket.session.getAsyncRemote().sendText(message);
            }
        }
    }


    public void sendMessageAll(String message) throws IOException {
        for (MultiWebSocket multiWebSocket : clients.values()) {
            multiWebSocket.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MultiWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MultiWebSocket.onlineCount--;
    }

    public static synchronized Map<String, MultiWebSocket> getClients() {
        return clients;
    }

}
