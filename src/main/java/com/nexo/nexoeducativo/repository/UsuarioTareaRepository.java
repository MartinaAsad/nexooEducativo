package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Calificacion;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioTarea;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioTareaRepository extends JpaRepository<UsuarioTarea, Integer> {
    @Modifying
@Query("DELETE FROM UsuarioTarea ut WHERE ut.usuarioIdUsuario IN " +
       "(SELECT cu.usuarioIdUsuario FROM CursoUsuario cu WHERE cu.cursoIdCurso.idCurso = :idCurso AND ut.tareaIdTarea.idTarea= :idTarea)")
void deleteByIdCursoAndIdTarea(@Param("idCurso") Integer idCurso, @Param("idTarea") Integer idTarea);

    @Modifying
    @Query("UPDATE UsuarioTarea ut SET ut.calificacionIdCalificacion= :calificacionIdCalificacion"
            + " WHERE ut.usuarioIdUsuario.idUsuario= :idUsuario AND ut.tareaIdTarea.idTarea= :idTarea")
    
    void updateByIdUsuarioAndIdTarea(@Param("calificacionIdCalificacion") Calificacion calificacionIdCalificacion,@Param("idUsuario") Integer idUsuario, @Param("idTarea") Integer idTarea);
Optional<UsuarioTarea> findByUsuarioIdUsuarioAndTareaIdTarea(Usuario usuarioIdUsuario, Tarea tareaIdTarea);
    
    
}
