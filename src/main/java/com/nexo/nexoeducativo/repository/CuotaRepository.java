package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Cuota;
import com.nexo.nexoeducativo.models.entities.Escuela;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Integer>{
    
    Optional<Cuota> findByTipoJornada(String tipoJornada);
      
}
