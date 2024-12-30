package com.nexo.nexoeducativo.service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    public String tomarAsistencia(int idCurso){
        /*se obtiene la fecha actual */
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
         //Date actual=Date.from(hoy.atZone(ZoneId.systemDefault()).toInstant());
         String formatoFechaMostrar=mostrarHora(actual);
        return formatoFechaMostrar;
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
    
}
