
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.EscuelaComprobantePago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface EscuelaComprobantePagoRepository extends JpaRepository<EscuelaComprobantePago, Integer>{
    
}
