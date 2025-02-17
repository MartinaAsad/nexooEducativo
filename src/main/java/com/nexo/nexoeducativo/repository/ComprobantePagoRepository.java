package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.ComprobantePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface ComprobantePagoRepository extends JpaRepository<ComprobantePago, Integer> {
    
}
