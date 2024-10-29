/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Martina
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    boolean existsByDescripcion (String descripcion);

    @Query(value="SELECT idPlan, descripcion FROM Plan")
    public List<String> getDescripcionAndIdPlan();
    
}
