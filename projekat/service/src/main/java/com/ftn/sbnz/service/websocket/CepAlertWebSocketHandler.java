package com.ftn.sbnz.service.websocket;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftn.sbnz.model.decision.FinalDecision;

@Component
public class CepAlertWebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ConcurrentHashMap<Long, Set<WebSocketSession>> sessionsByUser = new ConcurrentHashMap<>();

    public CepAlertWebSocketHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long userId = userIdFrom(session.getUri());
        if (userId == null) {
            try {
                session.close(CloseStatus.BAD_DATA);
            } catch (IOException ignored) {
            }
            return;
        }

        session.getAttributes().put("userId", userId);
        sessionsByUser
                .computeIfAbsent(userId, ignored -> Collections.newSetFromMap(new ConcurrentHashMap<>()))
                .add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Object userId = session.getAttributes().get("userId");
        if (userId instanceof Long id) {
            Set<WebSocketSession> sessions = sessionsByUser.get(id);
            if (sessions != null) {
                sessions.remove(session);
            }
        }
    }

    public void sendCepAlert(Long userId, FinalDecision decision) {
        Set<WebSocketSession> sessions = sessionsByUser.get(userId);
        if (sessions == null || sessions.isEmpty()) {
            return;
        }

        try {
            TextMessage message = new TextMessage(objectMapper.writeValueAsString(decision));
            for (WebSocketSession session : sessions) {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot send CEP alert", e);
        }
    }

    private Long userIdFrom(URI uri) {
        if (uri == null || uri.getQuery() == null) {
            return null;
        }

        for (String param : uri.getQuery().split("&")) {
            String[] parts = param.split("=", 2);
            if (parts.length == 2 && "userId".equals(parts[0])) {
                try {
                    return Long.valueOf(parts[1]);
                } catch (NumberFormatException ignored) {
                    return null;
                }
            }
        }

        return null;
    }
}
