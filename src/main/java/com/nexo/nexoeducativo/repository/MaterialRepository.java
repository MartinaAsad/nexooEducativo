
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer>{
    
}
