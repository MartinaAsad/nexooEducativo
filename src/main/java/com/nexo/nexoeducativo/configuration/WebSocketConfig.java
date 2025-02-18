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

/**
 *
 * @author Martina
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

    @Override
    public void registerStompEndpoints(StompEndpointRegistry ser) {
       // url de acceso : ('http://localhost:8080/ms');
       ser.addEndpoint("/ms").setAllowedOrigins("http://localhost:3000").withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration wstr) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration cr) {
     //   throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration cr) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> list) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    return false;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry mbr) {
        //endpoints que administran el envio de lols mensajes
        mbr.enableSimpleBroker("/grupo", "/usuario");
        
        //prefijo que usa el cliente para enviar el mensje al servidor
        mbr.setApplicationDestinationPrefixes("/enviar");
        
        //para chat individual
        mbr.setUserDestinationPrefix("/usuario");
    }
    
    
   
}
