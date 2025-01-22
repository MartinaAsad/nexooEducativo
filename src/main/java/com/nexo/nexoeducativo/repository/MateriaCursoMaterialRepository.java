package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.MateriaCursoMaterial;
import com.nexo.nexoeducativo.models.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface MateriaCursoMaterialRepository extends JpaRepository<MateriaCursoMaterial, Integer>{
    int deleteByMaterialIdMaterial(Material materialIdMaterial);
    MateriaCursoMaterial findIdMateriaCursoMaterialByMateriaCursoIdMateriaCurso (MateriaCurso MateriaCursoIdMateriaCurso);
    
}
