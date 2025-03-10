
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.models.dto.request.EditarMateriaCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class MateriaCursoService {
    
    @Autowired
    private MateriaCursoRepository mcRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    public List<EditarMateriaCursoView> infoCompletaMaterias (Integer curso){
        Curso c=cursoRepository.findById(curso).orElseThrow(
                ()-> new CursoNotFound("No existe el curso"));
        
        List<MateriaCurso> lista=mcRepository.findByCursoIdCurso(c); //info de las materias halladas
        List<EditarMateriaCursoView> infoCompletaMaterias=new ArrayList<>();
        EditarMateriaCursoView iterador=null;
        //por cada materia hallada, completar la view
        for (MateriaCurso m : lista) {
            iterador.setDia(m.getDia());
            iterador.setHoraFin(m.getHoraFin());
           // iterador.getHoraInicio(m.getHoraInicio());
            //iterador.getProfesor(m.getProfesor());
            
        }
        return infoCompletaMaterias;
        
    }
    
}
