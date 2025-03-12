package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.AsistenciaView;
import com.nexo.nexoeducativo.models.entities.Asistencia;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioAsistencia;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    Optional<UsuarioAsistencia> findByUsuarioIdUsuarioAndAsistenciaIdAsistencia(Usuario usuarioIdUsuario, Asistencia asistenciaIdAsistencia);
    @Query("SELECT ua FROM UsuarioAsistencia ua WHERE ua.usuarioIdUsuario = :usuarioIdUsuario AND ua.asistenciaIdAsistencia.fecha = :fecha")
List<UsuarioAsistencia> findByUsuarioIdUsuarioAndAsistenciaFecha(
    @Param("usuarioIdUsuario") Usuario usuarioIdUsuario, 
    @Param("fecha") Date fecha);
}
