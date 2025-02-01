package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Integer>{
    
}
