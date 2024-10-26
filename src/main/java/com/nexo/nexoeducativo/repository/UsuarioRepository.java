/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
       final Logger LOGGER = LoggerFactory.getLogger(UsuarioRepository.class);
    
    boolean existsByDni(int dni);
     boolean existsByMail(String mail);
    boolean existsByRolidrolAndIdUsuario (Rol rolidrol, Integer idUsuario);
    Rol findByRolidrol(Rol rolidrol);
   //EL PROBLEMA ES QUE EL MAIL NO ESTA LLEGANDO BIEN AL METODO, DESDE EL CONTROLADOR FUNCIONA BIEN PERO NO LLEGA ACA
     @Query(value = "SELECT * FROM Usuario u WHERE u.mail=:mail" , nativeQuery = true)
       Usuario findByMail (String mail);
       
       
       @Query(value="SELECT NEW com.nexo.nexoeducativo.models.dto.request.UsuarioDTO(u.nombre, u.apellido) FROM Usuario u WHERE idUsuario = ?1", nativeQuery = true)
       List<UsuarioDTO>getFullName(int idUsuario);
       
       
}
