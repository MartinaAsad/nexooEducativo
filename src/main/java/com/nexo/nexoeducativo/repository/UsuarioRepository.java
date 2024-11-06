/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
       final Logger LOGGER = LoggerFactory.getLogger(UsuarioRepository.class);
    
    boolean existsByDni(int dni);

    boolean existsByMail(String mail);

    boolean existsByRolidrolAndIdUsuario(Rol rolidrol, Integer idUsuario);

    Rol findByRolidrol(Rol rolidrol);
    //EL PROBLEMA ES QUE EL MAIL NO ESTA LLEGANDO BIEN AL METODO, DESDE EL CONTROLADOR FUNCIONA BIEN PERO NO LLEGA ACA

    //@Query(value = "SELECT * FROM Usuario u WHERE u.mail=:mail", nativeQuery = true)
    //Usuario findByMail(String mail);

    @Query(value = "SELECT u.nombre, u.apellido FROM Usuario u WHERE id_usuario = :idUsuario", nativeQuery = true)
    List<NombreCompletoDTO> getFullName(@Param("idUsuario") int idUsuario);

    @Query(value = "SELECT u.id_usuario, u.nombre, u.apellido FROM usuario u LEFT JOIN escuela_usuario eu ON eu.usuario_id_usuario=u.id_usuario WHERE u.Rol_id_rol=2 and eu.escuela_id_escuela IS NULL", nativeQuery = true)
    List<NombreCompletoDTO> getJefeColegioWithoutSchool();
    
    //PARA EL LOGIN
     Optional<Usuario> findByMail(String mail);
     
     //para chequear usuario logueado
      Usuario getUsuarioByMail(String mail);
       
       
       
}
