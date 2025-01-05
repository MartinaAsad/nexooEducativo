package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
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
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
      private static final Logger LOGGER = LoggerFactory.getLogger(AsistenciaService.class);
    
     @Transactional
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
         List<UsuarioAsistencia> registro2=asistencia.getAlumnosCurso().stream()
          .map(alumno ->{
              
              //para evitar error de persistencia
              Usuario u=usuarioRepository.findById(
              alumno.getIdUsuario()).orElseThrow(
                      ()-> new UsuarioNotFoundException ("Usuario no encontrado "+alumno.getIdUsuario()));
              
             UsuarioAsistencia usuarioAsistencia = new UsuarioAsistencia();
              usuarioAsistencia.setAsistenciaIdAsistencia(a);
             usuarioAsistencia.setUsuarioIdUsuario(u);
              a.setAsistio(alumno.getAsistio());
            a.setMediaFalta(alumno.getMediaFalta());
            a.setRetiroAntes(alumno.getRetiroAntes());
            //LOGGER.info(usuarioAsistencia.toString());
            return usuarioAsistencia;
         })
                 .toList();
        a.setUsuarioAsistenciaList(registro2);
         asistRepository.save(a);  
        
        //se obtiene info del curso
        Curso c=cursoRepository.findById(cursoIdCurso)
                .orElseThrow(()-> new CursoNotFound("Curso no encontrado"));
       
        //List<CursoAsistencia> lista=new ArrayList<CursoAsistencia>();
        CursoAsistencia ca= guardarCursoAsistencia(c, a);
        //lista.add(ca);
        a.setCursoAsistenciaList(List.of(ca));
             
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
    
    @Transactional
    public void guardarPresentismo(List<AlumnoAsistenciaDTO> lista){
        Integer cantAsistencia = 0;
        Integer cantInasistencia = 0;
        Integer asistCompleta = 0;
        Integer mediaFalta = 0;

        for (AlumnoAsistenciaDTO a : lista) {
            
            //si asistio=1 y mediafalta=1, se computa asistencia
            asistCompleta += (a.getAsistio() == 1 && a.getMediaFalta() == 0) ? 1 : 0;
            mediaFalta += (a.getAsistio() == 0 && a.getMediaFalta() == 1) ? 1 : 0;
            cantInasistencia += (asistCompleta == 0 && mediaFalta == 0) ? 1 : 0;
            
            Presentismo p = new Presentismo();
            p.setCantAsistencia(asistCompleta+mediaFalta);
            p.setCantInasistencia(cantInasistencia);
            //LOGGER.info("Guardando Presentismo...");
            //Presentismo savedPresentismo = presenRepository.save(p);
            LOGGER.info("Presentismo guardado con ID: " + p.getIdPresentismo());

            presenRepository.save(p);

            Usuario u = usuarioRepository.findById(
                    a.getIdUsuario()).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

            PresentismoUsuario pu = new PresentismoUsuario();
            pu.setIdPresentismoUsuario(p.getIdPresentismo());
            pu.setUsuarioIdUsuario(u);
            //LOGGER.info(pu.toString());
            presenUsuRepository.save(pu);
        }
        
    }
    
    @Transactional
    public CursoAsistencia guardarCursoAsistencia(Curso c, Asistencia a){
        Curso curso= cursoRepository.findById(c.getIdCurso())
        .orElseThrow(() -> new CursoNotFound("Curso no encontrado"));
        
        CursoAsistencia ca=new CursoAsistencia();
        ca.setAsistenciaIdAsistencia(a);
        ca.setCursoIdCurso(curso);
        //LOGGER.info(ca.toString());
        cursoARepository.save(ca);
        return ca;
    }
    
}
