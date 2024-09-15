/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
//logica de negocio
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public void crearUsuario(UsuarioDTO u){
        //reglas de negocio, ejemplo campos obligatorios con validaciones
    }
}
