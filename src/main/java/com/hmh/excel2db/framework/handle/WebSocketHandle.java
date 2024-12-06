package com.hmh.excel2db.framework.handle;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class WebSocketHandle extends TextWebSocketHandler {

    // 建立连接
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        System.out.println("建立连接");
        session.sendMessage(new TextMessage("连接成功"));

    }
    //接收文本
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        System.out.println("接收到文本消息："+message.getPayload());
        session.sendMessage(new TextMessage("收到消息"));

    }
    // 断开连接
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {

        System.out.println("断开连接");
        session.close();
    }
}
