
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.dto.request.MateriaView;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByIdMateria (Integer idMateria);
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView (m.idMateria, m.nombre) FROM Materia m " +
"INNER JOIN MateriaCurso mc ON " +
"mc.materiaIdMateria=m.idMateria " +
"INNER JOIN CursoUsuario cu ON " +
"cu.cursoIdCurso=mc.cursoIdCurso " +
"WHERE cu.usuarioIdUsuario= :usuarioIdUsuario")
    List<DesplegableMateriaView> materiasSegunHijo(Usuario usuarioIdUsuario);
    
    @Modifying
    @Query("UPDATE Materia m SET m.nombre= :nombre WHERE m.idMateria = :idMateria")
    public void updateMateria(@Param("nombre") String nombre, @Param("idMateria") Integer idMateria);
    //Optional<Materia> findByIdMateria (Integer idMateria);
}
