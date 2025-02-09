package com.nexo.nexoeducativo.repository;


import com.nexo.nexoeducativo.models.entities.EscuelaCuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface EscuelaCuotaRepository extends JpaRepository<EscuelaCuota, Integer>{
     @Modifying
    @Query(value="UPDATE escuela_cuota e"
            + " JOIN cuota c ON c.id_cuota=e.cuota_id "
            + "SET e.cuota_id = :id_cuota "
            + "WHERE e.escuela_id= :escuela_id AND c.tipo_jornada= :tipo_jornada", nativeQuery = true)
    void updateEscuelaByIdCuotaAndIdEscuela(Integer id_cuota, Integer escuela_id, String tipo_jornada);
    
}
