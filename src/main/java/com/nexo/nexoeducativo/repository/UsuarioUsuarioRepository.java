/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.UsuarioUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioUsuarioRepository extends JpaRepository<UsuarioUsuario, Integer>{
    
     boolean existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(int usuarioIdUsuario, int UsuarioIdUsuario1);
    
}