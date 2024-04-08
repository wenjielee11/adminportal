package com.alphvinterview.adminportal.websockets;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configures WebSocket communication using STOMP protocol.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   * Configures message broker options.
   * 
   * @param config the configuration for the message broker
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // Enable a simple in-memory message broker to carry the messages back to the client on destinations prefixed with "/topic"
    config.enableSimpleBroker("/topic");
  }
  
  /**
   * Registers STOMP endpoints mapping each to a specific URL and enabling SockJS fallback options.
   * 
   * @param registry the registry for STOMP endpoints
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // Registers the "/get-users" endpoint, enabling SockJS protocol fallback options, allowing for alternative messaging options if WebSocket is not available.
    registry.addEndpoint("/get-users").setAllowedOrigins("http://localhost:3000").withSockJS();
  }
}
