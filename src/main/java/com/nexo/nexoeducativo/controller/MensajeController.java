package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.MensajeDTO;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.service.MensajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequiredArgsConstructor
//https://medium.com/@samuelgbenga972/building-a-real-time-messaging-app-with-spring-boot-websocket-stompjs-and-react-part-ii-67ac5979c252
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;
    //esto es para enviar un mensaje privado a un usuario
    private final SimpMessagingTemplate usuarioPrivado;

    @MessageMapping
    @SendTo("/grupo/publico")
    public Mensaje enviarMensaje(@Payload MensajeDTO mensaje) {
        Mensaje m = mensajeService.altaMensajeIndividual(mensaje);

        return m;
    }

    //mensaje a un usuario especifico
    @MessageMapping("/mensajePrivado")
    public MensajeDTO agregarUsuario(@Payload MensajeDTO mensaje) {
        usuarioPrivado.convertAndSendToUser(mensaje.getDestinatario(), "/privado", mensaje);
        return mensaje;
    }
    
}
