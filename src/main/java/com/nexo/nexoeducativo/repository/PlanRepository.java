
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.dto.request.PlanView;
import com.nexo.nexoeducativo.models.entities.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martina
 */
@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    boolean existsByDescripcion (String descripcion);

    @Query(value="SELECT idPlan, descripcion FROM Plan where activo=1 ")
    public List<String> getDescripcionAndIdPlan();
    
    @Query("SELECT p.idPlan AS idPlan, p.descripcion AS descripcion, p.precio AS precio FROM Plan p WHERE p.activo = 1")
    List<PlanView> infoPlanes();

    
}
