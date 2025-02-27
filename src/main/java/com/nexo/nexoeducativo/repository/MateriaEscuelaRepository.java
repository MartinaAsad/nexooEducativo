
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaEscuela;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaEscuelaRepository extends JpaRepository<MateriaEscuela, Integer>{
                    //nombre de las campos SEGUN ENTIDAD, NO SEGUN LA ABSE DE DATOS
    boolean existsByMateriaIdMateriaAndEscuelaIdEscuela(Materia materiaIdMateria, Escuela escuelaIdEscuela);
   @Query("SELECT DISTINCT new com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView (m.idMateria AS idMateria, m.nombre AS nombre)"
           + " FROM Materia m"
           + " INNER JOIN MateriaEscuela me ON "
           + "m.idMateria=me.materiaIdMateria "
           + "WHERE me.escuelaIdEscuela= :escuelaIdEscuela")
   List<DesplegableMateriaView> materiasSegunEscuela(Escuela escuelaIdEscuela);
   
   @Query("SELECT COUNT(*) > 0 AS existe " +
"FROM MateriaEscuela me " +
"INNER JOIN Materia m ON me.materiaIdMateria = m.idMateria " +
"WHERE m.nombre = :nombre AND me.escuelaIdEscuela = :escuela")
   boolean siExisteMateria (String nombre, Escuela escuela);

}
