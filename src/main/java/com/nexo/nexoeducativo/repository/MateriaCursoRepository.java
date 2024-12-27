/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import java.time.LocalDate;
import com.nexo.nexoeducativo.models.dto.request.MateriaView;
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
    
   @Query(value="SELECT u.nombre, u.apellido, m.nombre FROM usuario u "
           + "INNER JOIN materia_curso mc ON mc.profesor_id=u.id_usuario "
           + "INNER JOIN materia m ON m.id_materia=mc.materia_id_materia "
           + "WHERE mc.curso_id_curso= :idCurso", nativeQuery=true)
List<Object[]> infoMateria(@Param("idCurso") Integer idCurso);
boolean deleteByCursoIdCursoAndMateriaIdMateria(Curso cursoIdCurso, Materia materiaIdMateria);

@Query("SELECT DISTINCT m.nombre FROM Materia "
        + "m INNER JOIN MateriaCurso mc ON"
        + " m.idMateria=mc.materiaIdMateria"
        + " WHERE mc.cursoIdCurso= :cursoIdCurso")
List<String> verMaterias(Curso cursoIdCurso);


}
