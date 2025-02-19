package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.repository.PresentismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class PresentismoService {
    @Autowired
    private PresentismoRepository presentismoRepository;
    
     public Integer cantInasistencias(Integer idUsuario){
         return presentismoRepository.cantInasistencias(idUsuario);
        
    }
    
}
