package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    
    Curso findNumeroAndDivisionByIdCurso (Integer idCurso);
    
    
    

    
}
