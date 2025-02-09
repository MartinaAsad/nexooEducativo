
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.FormatoIncorrectoException;
import com.nexo.nexoeducativo.models.entities.Cuota;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaCuota;
import com.nexo.nexoeducativo.repository.CuotaRepository;
import com.nexo.nexoeducativo.repository.EscuelaCuotaRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private EscuelaCuotaRepository escuelaCuotaRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(CuotaService.class);
    
    @Transactional
    public void altaCuota(double monto, Integer idEscuela, String jornada){
        Cuota c=new Cuota();
        if(monto<150){
            throw new FormatoIncorrectoException("La cuota debe ser mayor a $150");
        }
        
        if(!(jornada.equalsIgnoreCase("simple") || jornada.equalsIgnoreCase("completa"))){
            throw new FormatoIncorrectoException("La jornada puede ser simple o completa "+jornada);
        }
        c.setTipoJornada(jornada);
        c.setImporte(monto);
        cuotaRepository.save(c);
        
        Escuela e=escuelaRepository.findById(idEscuela).orElseThrow(()
                -> new EscuelaNotFoundException("La escuela no existe"));
        
        EscuelaCuota ec=new EscuelaCuota();
        ec.setCuota(c);
        ec.setEscuela(e);
        escuelaCuotaRepository.save(ec);
        
        //guardar el monto en la escuela
       // escuelaCuotaRepository.updateEscuelaByIdCuotaAndIdEscuela(c, e.getIdEscuela());
    }
    
     @Transactional
    public void modificarCuota(double monto, Integer idEscuela, String jornada){
        Escuela e=escuelaRepository.findById(idEscuela).orElseThrow(()
                -> new EscuelaNotFoundException("La escuela no existe"));
             
        if(monto<150){
            throw new FormatoIncorrectoException("La cuota debe ser mayor a $150");
        }
        Cuota c=cuotaRepository.findByTipoJornada(jornada).orElseThrow(()
                ->new FormatoIncorrectoException("No existe un valor de cuota asignada a la jornada "+jornada));
        
        c.setImporte(monto);
        c.setTipoJornada(jornada);
        escuelaCuotaRepository.updateEscuelaByIdCuotaAndIdEscuela(c.getIdCuota(), e.getIdEscuela(), jornada);
        
        }
    }
    
 
    

