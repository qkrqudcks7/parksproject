package com.example.parksproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    // 클라이언트가 웹 소켓 서버에 연결하는데 사용할 웹 소켓 엔드 포인트를 등록한다.
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는 데 사용된다.
        // /ws는 websockt 클라이언트가 handshake를 위해 연결해야하는 end-point의 url이다.
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    // 메세지 브로커 옵션을 설정한다.
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // destination이 /pub로 된 메세지를 @Controller의 @MessageMapping으로 라우팅한다.
        registry.setApplicationDestinationPrefixes("/pub");
        // /sub로 시작하는 목적지를 가진 메세지가 메세지 브로커로 라우팅한다.
        registry.enableSimpleBroker("/sub");
    }
}
