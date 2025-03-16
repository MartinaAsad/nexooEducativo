package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.DesplegableChatView;
import com.nexo.nexoeducativo.models.dto.request.MensajeIndividualDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.MensajeService;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/api/mensaje") //reemplazarlo por api
@CrossOrigin(origins="http://localhost:3000")
public class MensajeController {
    @Autowired
    private MensajeService mensajeService;
    
    @Autowired
    private EscuelaService escuelaService;
    
     @Autowired
    private UsuarioService usuarioService;

     private static final Logger LOGGER = LoggerFactory.getLogger(MensajeController.class);
    
      @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
      @PostMapping("/nuevoMensaje")
     ResponseEntity<?> nuevoMensaje( @Valid @RequestBody MensajeIndividualDTO mensaje, Authentication auth) {
        String mail=auth.getPrincipal().toString();//obtengo el mail del usuario logueado, osea el que envia el mensaje
       Mensaje nuevo=mensajeService.altaMensajeIndividual(mensaje, mail);
       if(mensaje!=null){
           return ResponseEntity.ok("Mensaje enviado");
       }
       
       return new ResponseEntity<>("Mensaje no enviado", HttpStatus.BAD_REQUEST);
        
    }
    
     
     
     @PatchMapping("/editarMensajePrivado/{idMensaje}")
    public MensajeIndividualDTO editarMensajePrivado(MensajeIndividualDTO mensaje, @PathVariable("idMensaje") Integer idMensaje) {
        Mensaje mensajeExistente = mensajeService.buscarMensaje(idMensaje);

       /* if (mensajeExistente != null && mensaje.getComunicador().equals(mensaje.getComunicador())) {
            // Actualizar el contenido y guardar en la BD
            mensajeExistente.setContenido(mensaje.getContenido());
            mensajeService.editarMensaje(mensajeExistente);

            // Notificar al destinatario sobre la modificación
            usuarioPrivado.convertAndSendToUser(mensaje.getDestinatario(), "/privado", mensaje);
        }*/

        return mensaje;
    }

       @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
    @GetMapping(value="/chatIndividual")
    ResponseEntity<?> cuotaM(Authentication auth ){
        String mailUsuario=auth.getPrincipal().toString();
        Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
        Usuario u=usuarioService.buscarUsuario(mailUsuario);
        LOGGER.info("probando logger"+u.toString());
        List<DesplegableChatView> infoUsuariosChat=usuarioService.infoUsuariosChat(e, u);
        //System.out.println("🔍 Lista de cursos recibida: " + verCursos);

        //return new ResponseEntity<>("lolll"+infoUsuariosChat,HttpStatus.OK);   
         return ResponseEntity.ok(infoUsuariosChat);
    }

		
		
    
    
}
