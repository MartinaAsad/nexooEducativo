package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Integer> {
    
}
