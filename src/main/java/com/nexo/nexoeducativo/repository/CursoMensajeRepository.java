
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoMensaje;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CursoMensajeRepository extends JpaRepository<CursoMensaje, Integer> {
    Integer deleteByCursoIdCurso(Curso cursoIdCurso );
    Optional<CursoMensaje> findByMensajeIdMensaje (Mensaje mensajeIdMensaje);
}
