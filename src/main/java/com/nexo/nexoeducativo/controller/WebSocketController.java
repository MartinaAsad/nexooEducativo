package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebSocketController {

    @MessageMapping("/enviar/mensajePrivado")
    @SendToUser("/privado") // Envía el mensaje solo al usuario destinatario
    public Mensaje enviarMensajePrivado(@Payload Mensaje mensaje, Principal principal) {
        System.out.println("📩 Mensaje privado enviado por: " + principal.getName());
        System.out.println("📜 Contenido: " + mensaje.getContenido());
        return mensaje;
    }
}
