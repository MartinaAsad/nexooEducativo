/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreDireccionEscuelaDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Plan;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.EscuelaUsuarioRepository;
import com.nexo.nexoeducativo.repository.PlanRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class EscuelaService {

       
    @Autowired
    private PlanRepository planRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private EscuelaUsuarioRepository escuelaUsuarioRepository;
    
     public void crearEscuela (EscuelaDTO e){
        
        //e.setIdPlan(0);
       int idPlanDto=e.getIdPlan(); //obtengo id del plan ingresado en el body del Postman
       Plan p=new Plan();
       p.setIdPlan(idPlanDto);
       
       Usuario u=new Usuario();//almaceno la info del jefe colegio ingresado
       u.setIdUsuario(e.getJefeColegio());
       
     
         if(escuelaRepository.existsByDireccion(e.getDireccion())){
             //aca salta una excepcion evitando que se guarde la escuela, ver si esta bien el tipo de excepcion
             throw new EscuelaNotFoundException("la escuela ya existe en la plataforma");
         } else if (!planRepository.existsById(idPlanDto)) {//si se intenta guardar un id de un plan inexistente
             //aca salta una excepcion evitando que se guarde la escuela, ver si esta bien el tipo de excepcion
             throw new IllegalArgumentException("el plan seleccionado no existe"+e.getIdPlan());
        } else {
             //si se ingresa un plan existente y el nombre de una escuela que no existe , seguir guardando sino no
              Escuela escuela = new Escuela();
              
              EscuelaUsuario jefeColegio=new EscuelaUsuario();
                escuela.setActivo(e.getActivo());
                escuela.setDireccion(e.getDireccion());
                escuela.setNombre(e.getNombre());
                escuela.setPlanIdPlan(p);
                //jefeColegio.setIdEscuelaUsuario(e.getJefeColegio());//guardo el id del jefe colegio en la tabla intermedia
                //jefeColegio.setEscuelaIdEscuela(escuela);//ver si esta bien
                jefeColegio.setEscuelaIdEscuela(escuela); // Asigna la escuela
                 jefeColegio.setUsuarioIdUsuario(u); // Establece el jefe del colegio
                // Aquí iría la lógica para guardar la entidad Escuela
                escuelaRepository.save(escuela);
                escuelaUsuarioRepository.save(jefeColegio);//se guarda la info donde se asocia 1 ejefe colegio a un colegio, en su tabla intermedia correspondiente
        }
     }
     
      public void borrarEscuela(int idEscuela){
         escuelaRepository.deleteById(idEscuela);
     }
      
        public List<NombreDireccionEscuelaDTO> obtenerEscuelas(){
         return escuelaRepository.getInfoEscuelas();
     }
    
    
 
}
