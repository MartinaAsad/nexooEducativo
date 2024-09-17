
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
}
