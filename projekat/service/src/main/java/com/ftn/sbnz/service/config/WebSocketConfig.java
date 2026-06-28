package com.ftn.sbnz.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.ftn.sbnz.service.websocket.CepAlertWebSocketHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final CepAlertWebSocketHandler cepAlertWebSocketHandler;

    public WebSocketConfig(CepAlertWebSocketHandler cepAlertWebSocketHandler) {
        this.cepAlertWebSocketHandler = cepAlertWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(cepAlertWebSocketHandler, "/ws/cep-alerts")
                .setAllowedOrigins("http://localhost:4200");
    }
}
