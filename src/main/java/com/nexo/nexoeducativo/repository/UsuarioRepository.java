/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Martina
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
}
