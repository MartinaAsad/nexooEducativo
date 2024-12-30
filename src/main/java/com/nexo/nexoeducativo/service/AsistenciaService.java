package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
     
     @Autowired
     private UsuarioRepository usuarioRepository;
    
    public List<UsuarioView> tomarAsistencia(int idCurso){
        /*se obtiene la fecha actual */
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
         String formatoFechaMostrar=mostrarHora(actual);
         
         Curso curso=new Curso();
         curso.setIdCurso(idCurso);
         
         List<UsuarioView> alumnosDelCurso=usuarioRepository.tomarLista(curso);
         return alumnosDelCurso;
       
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
    
}
