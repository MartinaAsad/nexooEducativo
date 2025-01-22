
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.SeleccionarMaterialView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.Material;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer>{
    List<Material> findDescripcionByIdMaterial(Integer idMaterial);
    
    @Query("SELECT new com.nexo.nexoeducativo.models.dto.request.SeleccionarMaterialView(m.idMaterial, m.descripcion) from Material m " +
" JOIN MateriaCursoMaterial mcm ON " +
"mcm.materialIdMaterial=m.idMaterial " +
" JOIN MateriaCurso mc ON " +
"mc.idMateriaCurso=mcm.materiaCursoIdMateriaCurso " +
"WHERE mc.cursoIdCurso= :cursoIdCurso AND mc.materiaIdMateria= :materiaIdMateria")
    List<SeleccionarMaterialView> buscarMaterial(Curso cursoIdCurso, Materia materiaIdMateria);
    
}
