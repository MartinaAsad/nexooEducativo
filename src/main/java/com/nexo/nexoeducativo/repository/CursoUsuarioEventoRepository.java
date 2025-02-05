package com.nexo.nexoeducativo.repository;
import com.nexo.nexoeducativo.models.entities.CursoUsuarioEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CursoUsuarioEventoRepository extends JpaRepository<CursoUsuarioEvento, Integer> {
    
}
