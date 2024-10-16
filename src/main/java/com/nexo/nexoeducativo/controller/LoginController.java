package com.nexo.nexoeducativo.controller;


import com.nexo.nexoeducativo.configuration.CustomAuthenticationProvider;
import com.nexo.nexoeducativo.models.dto.request.AuthRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
     //private final AuthenticationManager authenticationManager = null;
       @Autowired
      private CustomAuthenticationProvider customAuthenticationProvider;
        //private AuthenticationManager authenticationManager;
    private final AuthenticationManager authenticationManager;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
    @PostMapping("/login")
    public String login(@RequestBody AuthRequestDTO a
    ){
       return "lol";
      
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        // Spring Security ya maneja el proceso de logout automáticamente
        return ResponseEntity.ok("Sesion finalizada exitosamente");
    }
    
    
    
   
}
