package com.nexo.nexoeducativo.repository;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.CursoUsuarioEvento;
import com.nexo.nexoeducativo.models.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface CursoUsuarioEventoRepository extends JpaRepository<CursoUsuarioEvento, Integer> {
    Integer deleteByCursoUsuarioIdCursoUsuario(CursoUsuario cursoUsuarioIdCursoUsuario );
    Integer deleteByEventoIdEvento (Evento eventoIdEvento);
}
