/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/usuario")
public class LoginController {
    //
    @Autowired
    private UsuarioService usuarioService;
    
    
    
    @GetMapping("/getUsuario")
    public ResponseEntity<?> prueba(){
        return new ResponseEntity<>("prueba", HttpStatus.OK);
    }
    
    @PostMapping("/saveUsuario")
    public ResponseEntity<?> prueba2(@Valid @RequestBody UsuarioDTO u ){
        /*if (u.getEMail().isEmpty()){
             return new ResponseEntity<>("email vacio", HttpStatus.BAD_REQUEST);
        }*/
        usuarioService.crearUsuario(u);
        return new ResponseEntity<>("prueba", HttpStatus.OK);
    }
    
}
