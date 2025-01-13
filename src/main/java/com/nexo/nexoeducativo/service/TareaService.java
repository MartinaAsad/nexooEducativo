package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.models.dto.request.TareaDTO;
import com.nexo.nexoeducativo.models.entities.Calificacion;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioTarea;
import com.nexo.nexoeducativo.repository.CalificacionRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioTareaRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TareaService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    @Autowired
    private CalificacionRepository califRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
            
    @Autowired
    private UsuarioTareaRepository usuarioTRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Transactional
    public Tarea altaTarea(TareaDTO tarea, Integer cursoIdCurso){
        Calificacion c=altaCalificacion(tarea);
        Tarea t=new Tarea();
        t.setDescripcion(tarea.getDescripcion());
        t.setArchivo(tarea.getArchivo());
        t.setCalificacionIdCalificacion(c);
        
        //asociar la tarea al curso
        Curso curso=cursoRepository.findById(cursoIdCurso)
                .orElseThrow(()-> new CursoNotFound("El curso ingresado no existe"));
        asociarTareaUsuario(t, curso);
        return t;
        
    }
    public void asociarTareaUsuario(Tarea t, Curso c){
        //obtengo la lista de alumnos de un curso
        List<Usuario> alumnos=usuarioRepository.findByCurso(c);
        for(Usuario u: alumnos){
            UsuarioTarea ut=new UsuarioTarea();
            ut.setTareaIdTarea(t); 
            ut.setUsuarioIdUsuario(u);
            usuarioTRepository.save(ut);
        }
        
        
    }
    
    public Calificacion altaCalificacion(TareaDTO tarea){
        if(tarea.getCalificacion()==null){
            tarea.setCalificacion("Tarea sin calificar");
        }
        Calificacion c=new Calificacion();
        c.setNota(tarea.getCalificacion());
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
        Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
        c.setFecha(fechaDate);
        return califRepository.save(c);
    }
    
}
