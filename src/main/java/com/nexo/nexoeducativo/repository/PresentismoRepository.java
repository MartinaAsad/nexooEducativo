/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.repository;

import com.nexo.nexoeducativo.models.entities.Presentismo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PresentismoRepository extends JpaRepository<Presentismo, Integer> {
    
}
