package com.nexo.nexoeducativo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint para la conexión WebSocket
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:3000") // Permitir React
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefijo para que el cliente envíe mensajes al servidor
        registry.setApplicationDestinationPrefixes("/app");

        // Prefijos donde los clientes escucharán mensajes
        registry.enableSimpleBroker("/grupo", "/privado");

        // Para los chats individuales (permitir mensajes privados a usuarios específicos)
        registry.setUserDestinationPrefix("/usuario");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        // Interceptor para propagar la autenticación
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    // Extraer el token o credenciales del encabezado
                    String user = accessor.getFirstNativeHeader("user");
                    if (user != null) {
                        accessor.setUser(() -> user); // Asociar el usuario a la sesión
                    }
                }
                return message;
            }
        });
    }
}