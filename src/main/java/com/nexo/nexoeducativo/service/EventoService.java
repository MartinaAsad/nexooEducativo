package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.EventosView;
import com.nexo.nexoeducativo.repository.EventoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    
    public List<EventosView> obtenerEventosPosteriores(Integer idUsuario){
       List<EventosView> obtener=eventoRepository.obtenerEventosPosteriores(idUsuario);
       return obtener;
    }
    
}
