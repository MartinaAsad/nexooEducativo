
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.CursoAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoAsistenciaRepository extends JpaRepository<CursoAsistencia, Integer> {
    
}
