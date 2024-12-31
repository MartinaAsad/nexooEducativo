package com.nexo.nexoeducativo.repository;


import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
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
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.verCursoView "
            + "(c.numero, c.division, c.activo) FROM Curso c WHERE c.idCurso= :idCurso")
    List<verCursoView> verCursos(Integer idCurso);

    
    
    

    
}
