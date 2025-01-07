package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.TareaDTO;
import com.nexo.nexoeducativo.models.entities.Calificacion;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.repository.CalificacionRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TareaService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    @Autowired
    private CalificacionRepository califRepository;
    
    @Transactional
    public Tarea altaTarea(TareaDTO tarea){
        Calificacion c=altaCalificacion(tarea);
        Tarea t=new Tarea();
        t.setDescripcion(tarea.getDescripcion());
        t.setArchivo(tarea.getArchivo());
        return t;
        
    }
    public void asociarTareaUsuario(Tarea t){
        
    }
    
    public Calificacion altaCalificacion(TareaDTO tarea){
        Calificacion c=new Calificacion();
        c.setNota(tarea.getCalificacion());
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
        Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
        c.setFecha(fechaDate);
        return califRepository.save(c);
    }
    
}
