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
import com.nexo.nexoeducativo.repository.PresentismoRepository;
import com.nexo.nexoeducativo.repository.PresentismoUsuarioRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.AbstractList;
import java.util.ArrayList;
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
     
     @Autowired
     private PresentismoRepository presenRepository;
     
     @Autowired
     private PresentismoUsuarioRepository presenUsuRepository;
    
    public void altaAsistencia(AsistenciaDTO asistencia, Integer cursoIdCurso){
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
        
        //se obtiene info del curso
        Curso c=new Curso();
        c.setIdCurso(cursoIdCurso);
        List<CursoAsistencia> lista=new ArrayList<CursoAsistencia>();
        CursoAsistencia ca= guardarCursoAsistencia(c, a);
        lista.add(ca);
        a.setCursoAsistenciaList(lista);
          asistRepository.save(a);       
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
    
    public void guardarPresentismo(List<AlumnoAsistenciaDTO> lista){
        Integer cantAsistencia=0;
        Integer cantInasistencia=0;
        Integer asistCompleta=0;
        Integer mediaFalta=0;
        
        for (AlumnoAsistenciaDTO a : lista) {
            Presentismo p = new Presentismo();
            //si asistio=1 y mediafalta=1, se computa asistencia
            if(a.getAsistio()==1 && a.getMediaFalta()==0){
                asistCompleta++;   
            }else if ( a.getAsistio()==0 &&
                    a.getMediaFalta()==1){
                mediaFalta++;
                
            }else{
                cantInasistencia++;
            }
            cantAsistencia=asistCompleta+mediaFalta;
            
            p.setCantAsistencia(cantAsistencia);
            p.setCantInasistencia(cantInasistencia);
            presenRepository.save(p);
            
            PresentismoUsuario pu = new PresentismoUsuario();
            Usuario u = new Usuario();
            u.setIdUsuario(a.getIdUsuario());
            pu.setIdPresentismoUsuario(p.getIdPresentismo());
            pu.setUsuarioIdUsuario(u);
            presenUsuRepository.save(pu);
        }
        
    }
    
    public CursoAsistencia guardarCursoAsistencia(Curso c, Asistencia a){
        CursoAsistencia ca=new CursoAsistencia();
        ca.setAsistenciaIdAsistencia(a);
        ca.setCursoIdCurso(c);
        cursoARepository.save(ca);
        return ca;
    }
    
}
