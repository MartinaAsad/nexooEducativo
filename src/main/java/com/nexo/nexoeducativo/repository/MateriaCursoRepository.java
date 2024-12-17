/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaCursoRepository extends JpaRepository<MateriaCurso, Integer> {
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
}
