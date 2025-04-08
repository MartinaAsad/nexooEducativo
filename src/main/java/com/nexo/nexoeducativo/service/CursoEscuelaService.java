package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.VerCursosPreceptorView;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class CursoEscuelaService {
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
    
    public List<VerCursosPreceptorView> cursosSinPrecpetor(Integer idEscuela){
        return cursoEscuelaRepository.cursosSinPreceptor(idEscuela);
    }
    
   
    }

