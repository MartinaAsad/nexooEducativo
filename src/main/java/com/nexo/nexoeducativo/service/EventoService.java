package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.EventosView;
import com.nexo.nexoeducativo.models.entities.Usuario;
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
    
    public List<EventosView> obtenerEventosPosteriores(Usuario usuarioIdUsuario){
       List<EventosView> obtener=eventoRepository.obtenerEventosPosteriores(usuarioIdUsuario);
       return obtener;
    }
    
}
