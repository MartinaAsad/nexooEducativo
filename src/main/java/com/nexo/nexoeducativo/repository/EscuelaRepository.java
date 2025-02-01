/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.NombreDireccionEscuelaDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface EscuelaRepository extends JpaRepository<Escuela, Integer> {

    boolean existsByDireccion (String direccion);//metodo que busca a una escuela segun edireccion
    @Query("SELECT CASE WHEN COUNT(ce) > 0 THEN true ELSE false END " +
           "FROM CursoEscuela ce " +
           "JOIN ce.cursoIdCurso c " +
           "WHERE ce.escuelaIdEscuela.idEscuela = :escuelaId " +
           "AND c.numero = :numero " +
           "AND c.division = :division")
    boolean existsCursoInEscuela(@Param("escuelaId") Integer escuelaId, 
                                 @Param("numero") int numero, 
                                 @Param("division") Character division);
    
     @Query(value="SELECT id_escuela, nombre, direccion FROM escuela", nativeQuery = true)
    List<NombreDireccionEscuelaDTO> getInfoEscuelas();
    
    Escuela findActivoByIdEscuela(int idEscuela);
    
    //boolean isActivo(Integer idEscuela);
    @Modifying
    @Query("UPDATE Escuela e SET e.cuotaIdCuota.idCuota = :idCuota WHERE e.idEscuela= :idEscuela")
    void updateEscuelaByIdCuotaAndIdEscuela(Integer idCuota, Integer idEscuela);
    
}
