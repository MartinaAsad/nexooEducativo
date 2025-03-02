package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.AsistenciaView;
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
    
    @Query("select DISTINCT new com.nexo.nexoeducativo.models.dto.request.AsistenciaView (a.asistenciaIdAsistencia.fecha AS fecha,"
            + " a.asistenciaIdAsistencia.idAsistencia AS idAsistencia) from UsuarioAsistencia a " +
"JOIN Usuario u ON " +
"u.idUsuario=a.usuarioIdUsuario " +
"JOIN EscuelaUsuario eu ON"+
 " eu.usuarioIdUsuario=u.idUsuario "+
"WHERE u.rolidrol.idRol= :idRol AND eu.escuelaIdEscuela.idEscuela= :idEscuela")
    List<AsistenciaView> obtenerAsistenciasProfe(Integer idRol, Integer idEscuela);
    
}
