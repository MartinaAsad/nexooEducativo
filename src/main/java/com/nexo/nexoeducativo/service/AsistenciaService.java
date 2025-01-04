package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.models.dto.request.AlumnoAsistenciaDTO;
import com.nexo.nexoeducativo.models.dto.request.AsistenciaDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Asistencia;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoAsistencia;
import com.nexo.nexoeducativo.models.entities.Presentismo;
import com.nexo.nexoeducativo.models.entities.PresentismoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.AsistenciaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.models.entities.UsuarioAsistencia;
import com.nexo.nexoeducativo.repository.CursoAsistenciaRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
     
     @Autowired
     private UsuarioRepository usuarioRepository;
     
     @Autowired
     private CursoRepository cursoRepository;
     
     @Autowired
     private AsistenciaRepository asistRepository;
     
     @Autowired
     private CursoAsistenciaRepository cursoARepository;
    
    public void altaAsistencia(AsistenciaDTO asistencia){
        /*se obtiene la fecha actual */
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
         String formatoFechaMostrar=mostrarHora(actual);
         Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
         
         /*se da de alta una asistencia*/
         Asistencia a=new Asistencia();
         a.setFecha(fechaDate);
         
         //registrar la asistencia de cada alumno         
         List<UsuarioAsistencia> registro2=(List<UsuarioAsistencia>) asistencia.getAlumnosCurso().stream()
          .map(alumno ->{
             UsuarioAsistencia usuarioAsistencia = new UsuarioAsistencia();
              usuarioAsistencia.setAsistenciaIdAsistencia(a);
             usuarioAsistencia.setIdUsuarioAsistencia(alumno.getIdUsuario());
              a.setAsistio(alumno.getAsistio());
            a.setMediaFalta(alumno.getMediaFalta());
            a.setRetiroAntes(alumno.getMediaFalta());
            return usuarioAsistencia;
         })
                 .toList();
        a.setUsuarioAsistenciaList(registro2);
          asistRepository.save(a);
         
         //List<verCursoView> infoCurso=cursoRepository.verCursos(curso.getIdCurso());
         
         //List<NombreCompletoDTO> alumnosDelCurso=usuarioRepository.tomarLista(curso);
         
       //return registro2;
         
       
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
    
    public void guardarPresentismo(List<AlumnoAsistenciaDTO> lista){ 
        for (AlumnoAsistenciaDTO a : lista) {
            Presentismo p = new Presentismo();
            PresentismoUsuario pu = new PresentismoUsuario();
            Usuario u = new Usuario();
            u.setIdUsuario(a.getIdUsuario());
            pu.setIdPresentismoUsuario(p.getIdPresentismo());
            pu.setUsuarioIdUsuario(u);
        }
        
    }
    
    public void guardarCursoAsistencia(Curso c, Asistencia a){
        CursoAsistencia ca=new CursoAsistencia();
        ca.setAsistenciaIdAsistencia(a);
        ca.setCursoIdCurso(c);
        cursoARepository.save(ca);
    }
    
}
