package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.models.entities.Rol;
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
    Optional<UsuarioMensaje> findByUsuarioIdUsuario (Usuario usuarioIdUsuario);
    
    @Query("SELECT DISTINCT(um.mensajeIdMensaje) FROM UsuarioMensaje um " +
"JOIN EscuelaUsuario eu ON  " +
"eu.usuarioIdUsuario=um.usuarioIdUsuario " +
 "JOIN Usuario u ON u.idUsuario=eu.usuarioIdUsuario "+           
"WHERE eu.escuelaIdEscuela= :escuelaIdEscuela AND u.rolidrol.idRol= :idRol")
    Mensaje obtenerMensajesEscuela(Escuela escuelaIdEscuela, Integer idRol);
    
}
