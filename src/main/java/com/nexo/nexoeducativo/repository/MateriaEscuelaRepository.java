/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaEscuela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaEscuelaRepository extends JpaRepository<MateriaEscuela, Integer>{
                    //nombre de las campos SEGUN ENTIDAD, NO SEGUN LA ABSE DE DATOS
    boolean existsByMateriaIdMateriaAndEscuelaIdEscuela(Materia materiaIdMateria, Escuela escuelaIdEscuela);
}
