package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Rol;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    
    boolean existsByNombre(String nombre);
    
    @Query(value="SELECT nombre FROM Rol")
    List<String> getNombreRol();
    
    int getIdByNombre(String nombre);//chequear
    
    Optional<Rol> findByNombre (String nombre);
}
