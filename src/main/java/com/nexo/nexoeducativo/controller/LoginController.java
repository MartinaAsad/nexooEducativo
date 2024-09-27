
package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class LoginController {
    @Autowired
    private UsuarioService uService;
    
//    @GetMapping("/getUsuario")
    @GetMapping
    public ResponseEntity<?> prueba(){
        
        return new ResponseEntity<>("Hola que tal", HttpStatus.OK);
    }
    
//    @GetMapping
//    public ResponseEntity<?> prueba4(){
//        
//        return new ResponseEntity<>("msj nuevo para chequear", HttpStatus.OK);
//    }
    
    
//    @PostMapping("/postUsuario")
    @PostMapping
    public ResponseEntity<?> prueba2(@Valid @RequestBody UsuarioDTO request ){
//            uService.crearJefeColegio(request);
          uService.crearUsuario(request);           
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @PatchMapping
    public ResponseEntity<?> prueba3(){
        
        return new ResponseEntity<>("texto modificado", HttpStatus.OK);
    }
    
}
