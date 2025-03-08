
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MateriaCursoRepository extends JpaRepository<MateriaCurso, Integer> {
    //LOS ATRIBUTOS DENTRO DE LA QUERY SON SEGUN LO ESTABLEVIDO EN LA ENTIDAD DE JAVA NO DE MYSQL
    @Query("SELECT CASE WHEN COUNT(mc) > 0 THEN TRUE ELSE FALSE END\n" +
"    FROM MateriaCurso mc\n" +
"    WHERE mc.cursoIdCurso = :cursoIdCurso\n" +
"    AND mc.dia = :dia\n" +
"    AND (\n" +
"        (mc.horaInicio <= :horaFin AND mc.horaFin >= :horaInicio) \n" +
"    )")
    boolean verSiYaExisteEsaMateria(@Param("cursoIdCurso") Curso cursoIdCurso,
                                 @Param("dia") String dia,
                                 @Param("horaInicio") LocalTime horaInicio,
                                 @Param("horaFin") LocalTime horaFin);
    
   @Query(value="SELECT DISTINCT u.nombre, u.apellido, m.nombre FROM usuario u "
           + "INNER JOIN materia_curso mc ON mc.profesor_id=u.id_usuario "
           + "INNER JOIN materia m ON m.id_materia=mc.materia_id_materia "
           + "WHERE mc.curso_id_curso= :idCurso", nativeQuery=true)
List<Object[]> infoMateria(@Param("idCurso") Integer idCurso);
int deleteByCursoIdCursoAndMateriaIdMateria(Curso cursoIdCurso, Materia materiaIdMateria);

@Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView (m.idMateria, m.nombre) FROM Materia "
        + "m INNER JOIN MateriaCurso mc ON"
        + " m.idMateria=mc.materiaIdMateria"
        + " WHERE mc.cursoIdCurso= :cursoIdCurso")
List<DesplegableMateriaView> verMaterias(Curso cursoIdCurso);
List<MateriaCurso> findDistinctByProfesor (Usuario profesor);
List<MateriaCurso> findByCursoIdCursoAndProfesor (Curso cursoIdCurso, Usuario profesor);

 @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView (m.idMateria, m.nombre) FROM Materia m JOIN m.materiaCursoList mc WHERE mc.cursoIdCurso = :curso AND mc.profesor = :profesor")
    List<DesplegableMateriaView> findNombresMateriasPorCursoYProfesor(@Param("curso") Curso curso, @Param("profesor") Usuario profesor);

    List<MateriaCurso> findIdMateriaCursoByCursoIdCursoAndMateriaIdMateria(Curso cursoIdCurso, Materia materiaIdMateria);
    //findIdMateriaCursoByCursoIdCursoAndMateriaIdMateria
    
    MateriaCurso findByMateriaIdMateria(Materia materiaIdMateria);
    MateriaCurso findByCursoIdCursoAndMateriaIdMateriaAndDiaIsNotNullAndHoraInicioIsNotNullAndHoraFinIsNotNull(Curso cursoIdCurso, Materia materiaIdMateria);
    boolean existsByCursoIdCursoAndHoraInicioAndDia(Curso cursoIdCurso, LocalTime horaInicio, String dia);
    boolean existsByCursoIdCursoAndHoraFinAndDia(Curso cursoIdCurso, LocalTime horaFin, String dia);
    
    @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.verCursoView(" +
       "c.numero, c.division, c.activo, c.idCurso)" +
       " FROM MateriaCurso cu " +
       "INNER JOIN Curso c ON c.idCurso = cu.cursoIdCurso WHERE cu.profesor = :profesor")
List<verCursoView> obtenerCursosProfe(Usuario profesor);

}
