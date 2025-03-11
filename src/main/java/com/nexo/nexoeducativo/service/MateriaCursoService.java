
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.models.dto.request.EditarMateriaCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     private static final Logger LOGGER = LoggerFactory.getLogger(MateriaCursoService.class);
    
    public List<EditarMateriaCursoView> infoCompletaMaterias (Integer curso){
        Curso c=cursoRepository.findById(curso).orElseThrow(
                ()-> new CursoNotFound("No existe el curso"));
        
        List<EditarMateriaCursoView> infoCompletaMaterias=mcRepository.findDistinctByCursoIdCurso(c);
          return infoCompletaMaterias;   
        }
        
    }
    

