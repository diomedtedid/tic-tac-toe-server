package org.proskura.tictactoeserver.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@EnableScheduling
public class WebsocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Value("${app.ws.destinationPrefix}")
    private String destinationPrefix;

    @Value("${app.ws.applicationDestinationPrefix}")
    private String applicationDestinationPrefix;

    @Value("${app.ws.endpoint}")
    private String endpoint;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(destinationPrefix);
        config.setApplicationDestinationPrefixes(applicationDestinationPrefix);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(endpoint).setAllowedOriginPatterns("*").withSockJS();
    }

}
