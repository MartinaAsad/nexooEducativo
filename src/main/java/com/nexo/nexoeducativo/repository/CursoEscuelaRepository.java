
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.Escuela;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface CursoEscuelaRepository extends JpaRepository<CursoEscuela, Integer>{
    
   // boolean existsByEscuelaIdEscuela(Escuela EscuelaIdEscuela);
    @Query(value="SELECT count(*) FROM curso c LEFT JOIN curso_escuela ce ON c.id_curso=ce.curso_id_curso WHERE ce.escuela_id_escuela=? AND c.activo=1;", nativeQuery = true)
    int existsByCursoIdCursoAndEscuelaIdEscuela (Integer escuelaIdEscuela );
    
    @Query("SELECT ce.cursoIdCurso.idCurso FROM CursoEscuela ce JOIN Curso c ON "
            + "c.idCurso=ce.cursoIdCurso WHERE ce.escuelaIdEscuela= :escuelaIdEscuela "
            + "AND c.numero= :numero AND c.division= :division")
    Integer obtenerIdCursoCreado(Escuela escuelaIdEscuela, int numero, Character division);
    Integer deleteByCursoIdCurso(Curso cursoIdCurso );
    
}
