package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer>{
    
}
