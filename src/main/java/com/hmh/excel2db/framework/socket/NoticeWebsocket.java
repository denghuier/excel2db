package com.hmh.excel2db.framework.socket;


import com.alibaba.fastjson2.JSONObject;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/notice/{taskId}")
@Component

public class NoticeWebsocket {

    //记录连接的客户端
    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    /**
     * userId关联sid（解决同一用户id，在多个web端连接的问题）
     */
    public static Map<String, Set<String>> conns = new ConcurrentHashMap<>();

    private String sid = null;

    private String taskId;
    /**
     * 连接成功后调用的方法
     * @param session
     * @param taskId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("taskId") String taskId) {
        this.sid = UUID.randomUUID().toString();
        this.taskId = taskId;
        clients.put(this.sid, session);

        Set<String> clientSet = conns.get(taskId);
        if (clientSet==null){
            clientSet = new HashSet<>();
            conns.put(taskId,clientSet);
        }
        clientSet.add(this.sid);

    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {

        clients.remove(this.sid);
    }

    /**
     * 判断是否连接的方法
     * @return
     */
    public static boolean isServerClose() {
        if (NoticeWebsocket.clients.values().size() == 0) {

            System.out.println("已断开");
            return true;
        }else {
            System.out.println("已连接");

            return false;
        }
    }

    /**
     * 发送给所有用户
     * @param noticeType
     */
    public static void sendMessage(String noticeType){
        NoticeWebsocketResp noticeWebsocketResp = new NoticeWebsocketResp();
        noticeWebsocketResp.setNoticeType(noticeType);
        sendMessage(noticeWebsocketResp);
    }


    /**
     * 发送给所有用户
     * @param noticeWebsocketResp
     */
    public static void sendMessage(NoticeWebsocketResp noticeWebsocketResp){
        String message = JSONObject.toJSONString(noticeWebsocketResp);
        for (Session session1 : NoticeWebsocket.clients.values()) {
            try {
                session1.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据用户id发送给某一个用户
     * **/
    public static void sendMessageByTaskId(String taskId, String message) {
        if (!StringUtils.isEmpty(taskId)) {

            Set<String> clientSet = conns.get(taskId);
            if (clientSet != null) {
                Iterator<String> iterator = clientSet.iterator();
                while (iterator.hasNext()) {
                    String sid = iterator.next();
                    Session session = clients.get(sid);
                    if (session != null) {
                        try {
                            session.getBasicRemote().sendText(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    /**
     * 收到客户端消息后调用的方法
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message, Session session) {

    }

    /**
     * 发生错误时的回调函数
     * @param error
     */
    @OnError
    public void onError(Throwable error) {

        error.printStackTrace();
    }


}
