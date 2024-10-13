/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.PlanDTO;
import com.nexo.nexoeducativo.models.entities.Plan;
import com.nexo.nexoeducativo.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {
    
    @Autowired
    private PlanRepository planRepository;
    
    public void crearPlan(PlanDTO p){
        Plan plan=new Plan();
        plan.setActivo(p.getActivo());
        plan.setDescripcion(p.getDescripcion());
        plan.setPrecio(p.getPrecio());
        
        //verificar si ese plan no existey recien ahi, agregarlo
        if(!planRepository.existsByDescripcion(plan.getDescripcion())){
            this.planRepository.save(plan);
        }else{
             throw new IllegalArgumentException("el plan ya existe");
        }
    }
    
}
