package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.DesplegableChatView;
import com.nexo.nexoeducativo.models.dto.request.MensajeGrupalDTO;
import com.nexo.nexoeducativo.models.dto.request.MensajeIndividualDTO;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.service.MensajeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
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
    @SendTo("/grupo")  //cambiar mas adelante por MensajeGrupalDTO
    public void enviarMensaje(@Payload MensajeGrupalDTO mensaje) {
        List<DesplegableChatView> destinatarios=mensaje.getGrupoUsuarios();
        for (DesplegableChatView destinatario : destinatarios) {
          Mensaje m = mensajeService.altaMensaje(mensaje);   
        }
    }

    //mensaje a un usuario especifico
    @MessageMapping("/mensajePrivado")
    public MensajeIndividualDTO agregarUsuario(@Payload MensajeIndividualDTO mensaje) {
        usuarioPrivado.convertAndSendToUser(mensaje.getDestinatario(), "/privado", mensaje);
        return mensaje;
    }
    
     @MessageMapping("/editarMensajePrivado/{idMensaje}")
    public MensajeIndividualDTO editarMensajePrivado(@Payload MensajeIndividualDTO mensaje, @PathVariable("idMensaje") Integer idMensaje) {
        Mensaje mensajeExistente = mensajeService.buscarMensaje(idMensaje);

        if (mensajeExistente != null && mensaje.getComunicador().equals(mensaje.getComunicador())) {
            // Actualizar el contenido y guardar en la BD
            mensajeExistente.setContenido(mensaje.getContenido());
            mensajeService.editarMensaje(mensajeExistente);

            // Notificar al destinatario sobre la modificación
            usuarioPrivado.convertAndSendToUser(mensaje.getDestinatario(), "/privado", mensaje);
        }

        return mensaje;
    }

    // Editar un mensaje grupal y guardarlo en la BD
    @MessageMapping("/editarMensajeGrupo/{idMensaje}")
    public void editarMensajeGrupo(@Payload MensajeGrupalDTO mensaje, @PathVariable("idMensaje") Integer idMensaje) {
        Mensaje mensajeExistente = mensajeService.buscarMensaje(idMensaje);

        if (mensajeExistente != null && mensaje.getComunicador().equals(mensaje.getComunicador())) {
            // Actualizar el contenido y guardar en la BD
            mensajeExistente.setContenido(mensaje.getContenido());
            mensajeService.editarMensaje(mensajeExistente);

            // Notificar a todos los usuarios del grupo
            for (DesplegableChatView destinatario : mensaje.getGrupoUsuarios()) {
                usuarioPrivado.convertAndSendToUser(destinatario.getMail(), "/privado", mensaje);
            }
        }
    }
    
}
