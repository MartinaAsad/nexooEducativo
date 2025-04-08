
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.VerCursosPreceptorView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.Escuela;
import java.util.List;
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
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.VerCursosPreceptorView (ce.cursoIdCurso.idCurso, ce.cursoIdCurso.numero, ce.cursoIdCurso.division) FROM CursoEscuela ce "
            + "WHERE ce.escuelaIdEscuela.idEscuela = :idEscuela AND NOT EXISTS "
            + "( SELECT 1 FROM CursoUsuario cu INNER JOIN Usuario u ON cu.usuarioIdUsuario = u.idUsuario "
            + "WHERE cu.cursoIdCurso = ce.cursoIdCurso AND u.rolidrol.idRol = 4 )")
    List<VerCursosPreceptorView> cursosSinPreceptor(Integer idEscuela);
    
    
}
