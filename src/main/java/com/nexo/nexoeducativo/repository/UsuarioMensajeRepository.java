package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioMensaje;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioMensajeRepository extends JpaRepository<UsuarioMensaje, Integer>{
    Optional<UsuarioMensaje> findByRemitente (Usuario remitente);
    
    @Query("SELECT DISTINCT um.mensajeIdMensaje FROM UsuarioMensaje um " +
       "JOIN um.remitente u " +
       "JOIN u.escuelaUsuarioList eu " +
       "JOIN um.mensajeIdMensaje m " +
       "WHERE eu.escuelaIdEscuela = :escuelaIdEscuela " +
       "AND u.rolidrol.idRol = :idRol " +
       "AND LOWER(m.contenido) LIKE LOWER(:contenido)")
    Mensaje obtenerMensajesEscuela(Escuela escuelaIdEscuela, Integer idRol, String contenido);
    
}
