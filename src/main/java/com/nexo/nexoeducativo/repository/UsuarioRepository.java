/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    boolean existsByDni(int dni);
    boolean existsByRolidrolAndIdUsuario (Rol rolidrol, Integer idUsuario);
    Rol findByRolidrol(Rol rolidrol);
   //poner una query que traiga el mail segun el mail
     @Query(value = "SELECT u.mail, u.clave FROM Usuario u WHERE mail = ?1", nativeQuery = true)
       String findByMail (String mail);
    
}
