package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.UsuarioTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioTareaRepository extends JpaRepository<UsuarioTarea, Integer> {
    
    
}
