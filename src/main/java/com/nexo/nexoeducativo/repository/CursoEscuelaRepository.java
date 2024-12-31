
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.Escuela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface CursoEscuelaRepository extends JpaRepository<CursoEscuela, Integer>{
    
   // boolean existsByEscuelaIdEscuela(Escuela EscuelaIdEscuela);
    @Query(value="SELECT count(*) FROM curso c LEFT JOIN curso_escuela ce ON c.id_curso=ce.curso_id_curso WHERE c.id_curso=?1 AND ce.escuela_id_escuela=?2 AND c.activo=1;", nativeQuery = true)
    int existsByCursoIdCursoAndEscuelaIdEscuela (Integer cursoIdCurso,Integer escuelaIdEscuela );
    
}
