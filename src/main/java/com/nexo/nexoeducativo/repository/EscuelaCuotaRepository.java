package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Cuota;
import com.nexo.nexoeducativo.models.entities.Escuela;
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
    @Query("UPDATE EscuelaCuota e SET e.cuota = :cuota WHERE e.escuela.idEscuela= :idEscuela")
    void updateEscuelaByIdCuotaAndIdEscuela(Cuota cuota, Integer idEscuela);
    
}
