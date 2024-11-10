/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.MateriaExistingException;
import com.nexo.nexoeducativo.models.dto.request.MateriaDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.MateriaEscuela;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.MateriaEscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class MateriaService {
    
    @Autowired
     private MateriaRepository materiaRepository;
    
    @Autowired
    private MateriaCursoRepository materiaCursoRepository;
    
    @Autowired
    private MateriaEscuelaRepository materiaEscuelaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
    
    public void crearMateria(MateriaDTO m){
        //inserto lo ingresado en el dto en las entidades correspondientes
        Curso c=cursoRepository.findById(m.getIdCurso())
                .orElseThrow(() -> new CursoNotFound("El curso no existe"));
        
        Escuela e= escuelaRepository.findById(m.getIdEscuela())
                .orElseThrow(() -> new EscuelaNotFoundException("La escuela no existe"));
        
        Materia materia=new Materia();
        materia.setNombre(m.getNombre());
        //materia.setMateriaCursoList(materiaCursoList); FALTA ESTO
        //materia.setMateriaEscuelaList(materiaEscuelaList); FALTA ESTO
        
        MateriaEscuela me= new MateriaEscuela();
        me.setMateriaIdMateria(materia);
        me.setEscuelaIdEscuela(e);
        
        MateriaCurso mc = new MateriaCurso();
        mc.setCursoIdCurso(c); 
        mc.setDia(m.getDia());
        mc.setHoraFin(m.getHoraFin());
        mc.setHoraInicio(m.getHoraInicio());
        mc.setMateriaIdMateria(materia);
        //mc.setMateriaCursoMaterialList(materiaCursoMaterialList); DUDOSO DE SI VA O NO
        
        //si el curso esta inactivo
        if(cursoEscuelaRepository.existsByCursoIdCursoAndEscuelaIdEscuela(c.getIdCurso(), e.getIdEscuela())==0){
            //excepcion de ejemplo, solo para comporobar si efectivamente funciona la query
            throw new CursoNotFound("el curso o escuela esta inactivo");
        }
        
        
        //validar si existe esa materia en ese curso en esa escuela en ese dia entre las horas de inicio y fin
        //ejemplo: si ya existe materia biologia en escuela id 2 en curso id 2 los martes de 9 a 11 NO se va
        //a poder ingresar la misma materia en ese mismo curso y escuela a las 9:30 por ejemplo
        //PROBAR MAS TARDE ESTA VALIDACION
        if(materiaEscuelaRepository.existsByMateriaIdMateriaAndEscuelaIdEscuela(materia, e)
                && materiaCursoRepository.existsByMateriaIdMateriaAndCursoIdCursoAndDia(materia, c, m.getDia())){
            throw new MateriaExistingException("la materia que se desea ingresar ya existe en ese curso");
        }
        
        materiaRepository.save(materia);
        materiaEscuelaRepository.save(me);
        materiaCursoRepository.save(mc);
    }
    
}
