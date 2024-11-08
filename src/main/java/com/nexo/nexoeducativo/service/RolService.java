/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.RolDTO;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class RolService {
    
    @Autowired
    private RolRepository rolRepository;
    
    public void crearRol(RolDTO r){
        
        Rol rol=new Rol();
        rol.setNombre(r.getNombre());
        
        if(!rolRepository.existsByNombre(r.getNombre())){
            this.rolRepository.save(rol);
        }
        
    }
    
    public List<String> obtenerNombreRoles(){
        return rolRepository.getNombreRol();
    }
    
    public int obtenerId(String nombre){
        return rolRepository.getIdByNombre(nombre);
    }
    
}
