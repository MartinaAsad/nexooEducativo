package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.EventosView;
import com.nexo.nexoeducativo.models.entities.Evento;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer>{
    @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.EventosView (e.descripcion, e.fecha) FROM Evento e " +
"INNER JOIN CursoUsuarioEvento ceu ON " +
"ceu.eventoIdEvento=e.idEvento " +
"INNER JOIN CursoUsuario cu ON " +
"cu.idCursoUsuario=ceu.cursoUsuarioIdCursoUsuario " +
"WHERE cu.usuarioIdUsuario= :usuarioIdUsuario and e.fecha >= CURDATE()")
    List<EventosView> obtenerEventosPosteriores (Usuario usuarioIdUsuario);
    
   @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.EventosView(e.idEvento, e.descripcion, e.fecha) " +
       "FROM Evento e " +
       "INNER JOIN CursoUsuarioEvento ceu ON ceu.eventoIdEvento = e.idEvento " +
       "INNER JOIN CursoUsuario cu ON cu.idCursoUsuario = ceu.cursoUsuarioIdCursoUsuario " +
       "WHERE cu.cursoIdCurso.idCurso = :idCurso AND e.fecha >= CURRENT_DATE")
List<EventosView> obtenerEventos(@Param("idCurso") Integer idCurso);

    
}
