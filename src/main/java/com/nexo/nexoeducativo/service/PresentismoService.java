package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.repository.PresentismoRepository;
import com.nexo.nexoeducativo.repository.PresentismoUsuarioRepository;
import java.util.List;
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
    
    @Autowired
    private PresentismoUsuarioRepository puRepository;
    
     public Integer cantInasistencias(Integer idUsuario){
         return presentismoRepository.cantInasistencias(idUsuario);
        
    }
     
      public List<Double> cantPresentismo(Integer idUsuario){
         return puRepository.cantTotalPresentismo(idUsuario);
        
    }
     
     
    
}
