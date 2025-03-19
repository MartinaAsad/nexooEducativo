package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.DesplegableChatView;
import com.nexo.nexoeducativo.models.dto.request.MensajeDTO;
import com.nexo.nexoeducativo.models.dto.request.MensajeIndividualDTO;
import com.nexo.nexoeducativo.models.dto.request.MensajeView;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */

@RestController
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
     
      @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
      @GetMapping("/obtenerMensajes")
     ResponseEntity<?> obtenerMensaje(Authentication auth) {
        String mail=auth.getPrincipal().toString();//obtengo el mail del usuario logueado, osea el que envia el 
        Usuario u=usuarioService.buscarUsuario(mail);
       List<MensajeView> nuevo=mensajeService.obtenerMensajes(mail);
       if(nuevo.isEmpty()){
           return new ResponseEntity<>("No hay mensajes enviados", HttpStatus.NO_CONTENT);
       }
       
       return new ResponseEntity<>(nuevo, HttpStatus.OK);
        
    }
     
         @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
      @GetMapping("/obtenerMensajeDestinatario/{mailD}")
     ResponseEntity<?> obtenerMensajesDestinatario(Authentication auth,@PathVariable("mailD") String mailD) {
        String mailR=auth.getPrincipal().toString();
        System.out.println("el remitente (osea el que inicia sesion) "+mailR);
        Usuario remitente=usuarioService.buscarUsuario(mailR);
        Usuario destinatarioU=usuarioService.buscarUsuario(mailD);
        System.out.println("el destinatario (osea el que envio previamente el mensaje) "+mailD);
       List<MensajeDTO> mensajeMostrar=mensajeService.obtenerMensajesDesdeDestinatario(remitente, destinatarioU);
       return new ResponseEntity<>(mensajeMostrar, HttpStatus.OK);
        
    }
    
     
    @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')") 
    @PatchMapping(value = "/editarMensajePrivado/{idMensaje}", consumes = MediaType.TEXT_PLAIN_VALUE)
    ResponseEntity<?> editarMensajePrivado(@RequestBody String mensaje, @PathVariable("idMensaje") Integer idMensaje) {
        mensajeService.editarMensaje(idMensaje, mensaje);
        return new ResponseEntity<>("Mensaje editado correctamente", HttpStatus.OK);
    }

     @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
    @DeleteMapping("/borrarMensaje/{idMensaje}")
    ResponseEntity<?> borrarMensaje(@PathVariable("idMensaje") Integer idMensaje) {
        mensajeService.borrarMensaje(idMensaje);
        return new ResponseEntity<>("Mensaje borrado correctamente", HttpStatus.OK);
    }

       @PreAuthorize("hasAuthority('alumno') or hasAuthority('administrativo') or hasAuthority('preceptor') or hasAuthority('padre') or hasAuthority('profesor')")
    @GetMapping(value="/chatIndividual")
    ResponseEntity<?> cuotaM(Authentication auth ){
        String mailUsuario=auth.getPrincipal().toString();
        Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
        Usuario u=usuarioService.buscarUsuario(mailUsuario);
        List<DesplegableChatView> infoUsuariosChat=usuarioService.infoUsuariosChat(e, u);

        //return new ResponseEntity<>("lolll"+infoUsuariosChat,HttpStatus.OK);   
         return ResponseEntity.ok(infoUsuariosChat);
    }

		
		
    
    
}
