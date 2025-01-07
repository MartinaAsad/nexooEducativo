
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer>{
    
}
