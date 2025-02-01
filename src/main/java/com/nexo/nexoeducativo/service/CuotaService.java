
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.entities.Cuota;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.repository.CuotaRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martina
 */
@Service
public class CuotaService {
    @Autowired
    private CuotaRepository cuotaRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Transactional
    public void altaCuota(double monto, Integer idEscuela){
        Cuota c=new Cuota();
        c.setImporte(monto);
        cuotaRepository.save(c);
        
        //guardar el monto en la escuela
        escuelaRepository.updateEscuelaByIdCuotaAndIdEscuela(c.getIdCuota(), idEscuela);
    }
    
 
    
}
