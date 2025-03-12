package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.AlumnoAsistenciaDTO;
import com.nexo.nexoeducativo.models.dto.request.AsistenciaDTO;
import com.nexo.nexoeducativo.models.dto.request.AsistenciaView;
import com.nexo.nexoeducativo.models.entities.Asistencia;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoAsistencia;
import com.nexo.nexoeducativo.models.entities.Presentismo;
import com.nexo.nexoeducativo.models.entities.PresentismoUsuario;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioAsistencia;
import com.nexo.nexoeducativo.repository.AsistenciaRepository;
import com.nexo.nexoeducativo.repository.CursoAsistenciaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.PresentismoRepository;
import com.nexo.nexoeducativo.repository.PresentismoUsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioAsistenciaRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
             
      @Autowired
      private UsuarioAsistenciaRepository uaRepository;
              
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
         
          short asistio=0,retiro=0, mediaFalta=0;
         //registrar la asistencia de cada alumno         
          List<UsuarioAsistencia> registro2 = new ArrayList<>();
         for (AlumnoAsistenciaDTO alumno : asistencia.getAlumnosCurso()) {
        Usuario u = usuarioRepository.findById(alumno.getIdUsuario())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado " + alumno.getIdUsuario()));

        UsuarioAsistencia usuarioAsistencia = new UsuarioAsistencia();
        usuarioAsistencia.setAsistenciaIdAsistencia(a);
        usuarioAsistencia.setUsuarioIdUsuario(u);

        if (alumno.getAsistio() == 1) asistio++;
        if (alumno.getMediaFalta() == 1) mediaFalta++;
        if (alumno.getRetiroAntes() == 1) retiro++;

        registro2.add(usuarioAsistencia);
    }
         a.setAsistio(asistio);
         a.setMediaFalta(mediaFalta);
         a.setRetiroAntes(retiro);
        a.setUsuarioAsistenciaList(registro2);
        //LOGGER.info("esta es la lista "+registro2.toString());
       // LOGGER.info("esto va a ser guardado: "+a.toString());
         asistRepository.save(a);  
         //LOGGER.info("esto va a ser guardado: "+a.toString());
        
        //se obtiene info del curso
        Curso c=cursoRepository.findById(cursoIdCurso)
                .orElseThrow(()-> new CursoNotFound("Curso no encontrado"));
       
        //List<CursoAsistencia> lista=new ArrayList<CursoAsistencia>();
        CursoAsistencia ca= guardarCursoAsistencia(c, a);
        //lista.add(ca);
        a.setCursoAsistenciaList(List.of(ca));
        //LOGGER.info("esto va a ser guardado: "+a.toString());
         guardarPresentismo(asistencia.getAlumnosCurso());
             
    }
    
    private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
       @Transactional
    public void altaAsistenciaProfe(AsistenciaDTO asistencia){ //ENDPOINTS NECESARIOS: verProfeAdministrativo y tomarAsistenciaProfesor
        /*se obtiene la fecha actual */
       // LOGGER.info("ESTO LLEGA DESDE EL FRONT: "+asistencia.getAlumnosCurso().toString());
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
         String formatoFechaMostrar=mostrarHora(actual);
         Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
         
         /*se da de alta una asistencia*/
         Asistencia a=new Asistencia();
         a.setFecha(fechaDate);
         
          short asistio=0,retiro=0, mediaFalta=0;
         //registrar la asistencia de cada alumno         
          List<UsuarioAsistencia> registro2 = new ArrayList<>();
         for (AlumnoAsistenciaDTO profe : asistencia.getAlumnosCurso()) {
        Usuario u = usuarioRepository.findById(profe.getIdUsuario())
            .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado " + profe.getIdUsuario()));

        UsuarioAsistencia usuarioAsistencia = new UsuarioAsistencia();
        usuarioAsistencia.setAsistenciaIdAsistencia(a);
        usuarioAsistencia.setUsuarioIdUsuario(u);

        if (profe.getAsistio() == 1) asistio++;
        if (profe.getMediaFalta() == 1) mediaFalta++;
        if (profe.getRetiroAntes() == 1) retiro++;

        registro2.add(usuarioAsistencia);
    }
         a.setAsistio(asistio);
         a.setMediaFalta(mediaFalta);
         a.setRetiroAntes(retiro);
        a.setUsuarioAsistenciaList(registro2);
        //LOGGER.info("esta es la lista "+registro2.toString());
       // LOGGER.info("esto va a ser guardado: "+a.toString());
         asistRepository.save(a);  
         //LOGGER.info("esto va a ser guardado: "+a.toString());
        
         guardarPresentismo(asistencia.getAlumnosCurso());
             
    }
    
    
    @Transactional
    public void guardarPresentismo(List<AlumnoAsistenciaDTO> lista){
        Double cantAsistencia = 0D;
        Integer cantInasistencia = 0;
        Double asistCompleta = 0D;
        Double mediaFalta = 0D;

        for (AlumnoAsistenciaDTO a : lista) {
            
            //si asistio=1 y mediafalta=1, se computa asistencia
             asistCompleta = (a.getAsistio() == 1 && a.getMediaFalta() == 0 && a.getRetiroAntes() == 0) ? 1D : 0D;
            mediaFalta = (a.getAsistio() == 0 && (a.getMediaFalta() == 1 || a.getRetiroAntes() == 1)) ? 1D : 0D;
            cantInasistencia = (asistCompleta == 0 && mediaFalta == 0) ? 1 : 0;
            
            cantAsistencia=asistCompleta+(mediaFalta/2);
            
            Presentismo p = new Presentismo();
            p.setCantAsistencia(cantAsistencia);
            p.setCantInasistencia(cantInasistencia);
            presenRepository.save(p);
            
              Presentismo persistido= presenRepository.save(p);
              //LOGGER.info("Presentismo guardado con ID: " + persistido.getIdPresentismo());

            Usuario u = usuarioRepository.findById(
                    a.getIdUsuario()).orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

            PresentismoUsuario pu = new PresentismoUsuario();
            pu.setPresentismoIdPresentismo(persistido);
            pu.setUsuarioIdUsuario(u);
            //LOGGER.info(pu.toString());
            presenUsuRepository.save(pu); //aca esta el problema segun consola de spring
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
    
    public List<AsistenciaView> obtenerFechasAsistencias (Curso id){
        //obtengo las fechas de las asistencias de todos los miembros de un curdo
        List<AsistenciaView> a = asistRepository.fechasAsistencias(id);
        return a;
    }
    
    public List<Integer> obtenerIdAsistencias (Date fecha){
        List<Asistencia> asistencias=asistRepository.findAsistenciaByFecha(fecha);
        List<Integer> ids=new ArrayList<>();
        Integer id=0;
        for (Asistencia a : asistencias) {
            id=a.getIdAsistencia();
            ids.add(id);
        }
        
        return ids;
        
    }
    
    @Transactional
    public void modificarAsistencia(Curso c, Date fecha, List<AlumnoAsistenciaDTO> lista){
        //guardo todas las asistencias asociadas a cada alumno de un curso
        List<Integer> obtenerIdAsistencias=obtenerIdAsistencias(fecha);//22
        Double cantAsistencia = 0D;
        Integer cantInasistencia = 0;
        Double asistCompleta = 0D;
        Double mediaFalta = 0D;
        
        
        for (Integer asist : obtenerIdAsistencias) {
            for (AlumnoAsistenciaDTO alumno : lista) {
                Optional<Asistencia> siExiste=asistRepository.findById(asist);
                                    //buscar el presentismo correspondiente al alumno
                   Usuario u=new Usuario();
                   u.setIdUsuario(alumno.getIdUsuario()); 
                Optional<PresentismoUsuario> presentismo = presenUsuRepository.findByUsuarioIdUsuario(u);
                 
                if(presentismo.isPresent()){
                    PresentismoUsuario pu=presentismo.get();
                    Presentismo p=pu.getPresentismoIdPresentismo();
                    Asistencia actual=siExiste.get();
                    
                    //Actualizar la asistencia
                    asistCompleta = (alumno.getAsistio() == 1 && alumno.getMediaFalta() == 0 && alumno.getRetiroAntes() == 0) ? 1D : 0D;
            mediaFalta = (alumno.getAsistio() == 0 && (alumno.getMediaFalta() == 1 || alumno.getRetiroAntes() == 1)) ? 1D : 0D;
            cantInasistencia = (asistCompleta == 0 && mediaFalta == 0) ? 1 : 0;
            
            cantAsistencia=asistCompleta+(mediaFalta/2);
            
            p.setCantAsistencia(cantAsistencia);
            p.setCantInasistencia(cantInasistencia);
            
            presenRepository.save(p);
            asistRepository.save(actual);
                }
                
                
                        
                
            }
            
        }
    }
    
    public List<AsistenciaView> obtenerAsistenciasProfe(Integer idRol, Integer idEscuela){
        return uaRepository.obtenerAsistenciasProfe(idRol, idEscuela);
    }
    
    
   @Transactional
public void modificarAsistenciaProfe(Date fecha, List<AlumnoAsistenciaDTO> lista) {
    List<Integer> obtenerIdAsistencias = obtenerIdAsistencias(fecha);
    Double cantAsistencia = 0D;
    Integer cantInasistencia = 0;
    Double asistCompleta = 0D;
    Double mediaFalta = 0D;

    for (Integer asist : obtenerIdAsistencias) {
        for (AlumnoAsistenciaDTO profe : lista) {
            Optional<Asistencia> siExiste = asistRepository.findById(asist);
            // Buscar el presentismo correspondiente al alumno
            Usuario u = new Usuario();
            u.setIdUsuario(profe.getIdUsuario());
            Optional<PresentismoUsuario> presentismo = presenUsuRepository.findByUsuarioIdUsuario(u);

            if (siExiste.isPresent() && presentismo.isPresent()) {
                Asistencia actual = siExiste.get();
                PresentismoUsuario pu = presentismo.get();
                Presentismo p = pu.getPresentismoIdPresentismo();

                // Calcular asistencia
                asistCompleta = (profe.getAsistio() == 1 && profe.getMediaFalta() == 0 && profe.getRetiroAntes() == 0) ? 1D : 0D;
                mediaFalta = (profe.getAsistio() == 0 && (profe.getMediaFalta() == 1 || profe.getRetiroAntes() == 1)) ? 1D : 0D;
                cantInasistencia = (asistCompleta == 0 && mediaFalta == 0) ? 1 : 0;

                cantAsistencia = asistCompleta + (mediaFalta / 2);

                // Actualizar la asistencia
                p.setCantAsistencia(cantAsistencia);
                p.setCantInasistencia(cantInasistencia);

                // Obtener el valor anterior de 'asistio' para el cambio
                int asistenciaAnterior = actual.getAsistio();  // Obtener el valor anterior de 'asistio'
                actual.setAsistio(profe.getAsistio());  // Actualizar el valor de 'asistio'
                actual.setMediaFalta(profe.getMediaFalta());  // Asegúrate de actualizar 'mediaFalta'
                actual.setRetiroAntes(profe.getRetiroAntes());  // Asegúrate de actualizar 'retiroAntes'

                // Si la asistencia cambia de 1 a 0 o de 0 a 1, actualizar el contador de asistencias en 'Presentismo'
                if (asistenciaAnterior != profe.getAsistio()) {
                    // Si la persona pasó de 'asistió' a 'no asistió', decrementamos el contador
                    if (asistenciaAnterior == 1 && profe.getAsistio() == 0) {
                        p.setCantAsistencia(p.getCantAsistencia() - 1);
                    }
                    // Si la persona pasó de 'no asistió' a 'asistió', incrementamos el contador
                    else if (asistenciaAnterior == 0 && profe.getAsistio() == 1) {
                        p.setCantAsistencia(p.getCantAsistencia() + 1);
                    }

                    // Guardar los cambios en el presentismo
                    presenRepository.save(p);
                    presenRepository.flush(); // Asegura que los cambios se persistan inmediatamente
                }
                
                // Guardar la asistencia actualizada
                asistRepository.save(actual);
                asistRepository.flush();
            }
        }
    }
}

    
    
}
    
    

