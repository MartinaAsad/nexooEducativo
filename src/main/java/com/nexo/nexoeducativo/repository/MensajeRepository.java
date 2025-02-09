package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
    
}
