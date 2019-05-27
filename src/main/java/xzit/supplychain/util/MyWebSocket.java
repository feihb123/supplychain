package xzit.supplychain.util;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket/{from}/{to}")
@Component
public class MyWebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    private static ConcurrentHashMap<String,MyWebSocket> webSocketMap = new ConcurrentHashMap<String,MyWebSocket>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String from;
    private String to;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam(value = "from") String from,@PathParam(value = "to") String to, Session session) {
        //System.out.println(id);
        this.from = from;
        this.to = to;
        this.session = session;

        webSocketMap.put(from,this);
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketMap.remove(from);
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);
        try {
            System.out.println(to);
            webSocketMap.get(to).sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //给当前会话对象回复消息
        /*try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //群发消息
        /*for (MyWebSocket item : webSocketSet) {
            try {
                System.out.println(webSocketSet.toString());
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }
}