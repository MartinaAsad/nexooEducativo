/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
    
    
       public boolean siYaExisteCombinacion(Integer escuelaId, int numero, Character division){
        return escuelaRepository.existsCursoInEscuela(escuelaId, numero, division);
    
}
    //PROBAR NUEVAMENTE
    public void crearCurso(CursoDTO c){
        Curso curso=new Curso();
        curso.setNumero(c.getNumero());
        curso.setDivision(c.getDivision());
        curso.setActivo(c.getActivo());
        int numero=curso.getNumero();
        Character division=curso.getDivision();
        
        Escuela e=new Escuela();
        e.setIdEscuela(c.getEscuela());//se almacena el id de la escuela
        int escuelaId=e.getIdEscuela();
        
        CursoEscuela ce=new CursoEscuela();//tabla intermedia donde se va a guardar a que escuela esta asociado ese curso
        ce.setCursoIdCurso(curso);//se guarda el id del curso creado
        ce.setEscuelaIdEscuela(e);//se guarda el id de la escuela asociada
        
        //valida que la division ingresada sea solo una letra
        if(!Character.isLetter(c.getDivision())){
            throw new IllegalArgumentException("La división debe ser un carácter alfabético");
        }else if (!Character.toString(curso.getDivision()).matches("^[a-z]$")){
//luego valida que el caracter ingresado sea una minuscula nomas
            throw new IllegalArgumentException("El campo division solo puede una letra minuscula");
        }
        
        //para que se guarde el curso, el id del colegio,el numero y division ingresada (la combinacion) NO debe existir previamente en la base
        if (!siYaExisteCombinacion(escuelaId,numero, division)) {
            this.cursoRepository.save(curso);//se guarda el curso
            this.cursoEscuelaRepository.save(ce);//se guarda la info en la tabla intermedia
         }else{
             throw new IllegalArgumentException("El curso ya existe en la escuela!");
        }

    }
}
