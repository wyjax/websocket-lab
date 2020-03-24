package com.wyjax.websocketlab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
        /*
            Broker를 설정할 때는 /topic으로 보내거나 /queue로 보낸다
            /topic : 토픽을 구독하는 모두에게 메세지를 보냄.
            /queue : 한명이 한명에게 보냄.

            < setApplicationDestinationPrefixes > : 메세지가 도착할 prefix를 설정한다.
            >> prefix가 /app이고 컨트롤러에서 /hello로 매핑했다면 실제경로는 /app/hello 이다.
         */
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 서버에사 클라이언트로부터 받을 api prefix
        registry.addEndpoint("/chat").setAllowedOrigins("*").withSockJS();
        // 클라이언트가 서버로 연결하는 주소이다ㅣ
    }
}
