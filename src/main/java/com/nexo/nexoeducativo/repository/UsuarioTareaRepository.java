package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.UsuarioTarea;
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

    
    
}
