package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.UsuarioAsistencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface UsuarioAsistenciaRepository extends JpaRepository<UsuarioAsistencia, Integer> {
    
    @Query("select a.asistenciaIdAsistencia.idAsistencia from UsuarioAsistencia a " +
"JOIN Usuario u ON " +
"u.idUsuario=a.usuarioIdUsuario " +
"JOIN EscuelaUsuario eu ON"+
 " eu.usuarioIdUsuario=u.idUsuario "+
"WHERE u.rolidrol.idRol= :idRol AND eu.escuelaIdEscuela.idEscuela= :idEscuela")
    List<Integer> obtenerAsistenciasProfe(Integer idRol, Integer idEscuela);
    
}
