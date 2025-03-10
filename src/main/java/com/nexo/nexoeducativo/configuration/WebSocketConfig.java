package com.nexo.nexoeducativo.configuration;

import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
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
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        // Aquí puedes configurar límites de tamaño de mensaje si es necesario
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registry) {
        // Aquí puedes agregar intercepción de mensajes si es necesario
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registry) {
        // Aquí puedes agregar intercepción de mensajes si es necesario
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // No se necesitan resolvers adicionales por ahora
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        // No se necesitan handlers adicionales por ahora
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> converters) {
        return false; // Usar configuración por defecto
    }
}
