
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Materia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByIdMateria (Integer idMateria);
    
}
