package com.nexo.nexoeducativo.controller;


import com.nexo.nexoeducativo.configuration.CustomAuthenticationProvider;
import com.nexo.nexoeducativo.models.dto.request.AuthRequestDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.Math.log;
import static java.rmi.server.LogStream.log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/auth") 
//EN TODOS LOS METODOS, SI HAY UN ERROR EN EL CASO DE QUE EXISTAN, 
//NO SALTA EL MENSAJE EN POSTMAN SINO EN CONSOLA DE JAVA, ARREGLAR ESO
public class LoginController {
    @Autowired
       private UsuarioRepository usuarioRepository;
         @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO loginRequest) {
            // The actual authentication is handled by Spring Security
            // This method will only be reached if authentication was successful
            //return ResponseEntity.ok(loginRequest.getMail());//LLEGA LA CLAVE PERO NO EL EMAIL
           Usuario u = usuarioRepository.findByMail(loginRequest.getMail());//chequear lo que recibo del email y contraseña despues
           // chequear si esta en el repositoriodel body
           //desencriptar la clave llegada obtenida en el objeto u
           String claveDesencriptada="";
            
            if (u != null) {
            // Si el usuario existe, retornamos el string en el cuerpo de la respuesta
            return ResponseEntity.ok(u);
        } else {
            // Si no se encuentra el usuario, retornamos un error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
                 //return ResponseEntity.ok("todo bien");
}
}
    
    
   

