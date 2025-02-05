package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.HoraInvalidatedexception;
import com.nexo.nexoeducativo.models.dto.request.EventosView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.CursoUsuarioEvento;
import com.nexo.nexoeducativo.models.entities.Evento;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioEventoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.EventoRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
public class EventoService {
     LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Autowired
    private EventoRepository eventoRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoUsuarioRepository cuRepository;
    
    @Autowired
    private CursoUsuarioEventoRepository cueRepository;
    public void altaEvento(EventosView e, Integer idCurso){
        Evento evento=new Evento();
        evento.setDescripcion(e.getDescripcion());
        /*obtener fecha actual*/
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
        Date fechaActual = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
        if(e.getFecha().before(fechaActual)){
            throw new HoraInvalidatedexception("No puedes agendar un evento antes de la fecha actual "+e.getFecha());
            
        }
        evento.setFecha(e.getFecha());
        eventoRepository.save(evento);
        
        //guardar la tabla asociada
        CursoUsuarioEvento ceu=new CursoUsuarioEvento();
        altaCursoUsuario(idCurso, ceu);
        ceu.setEventoIdEvento(evento);
        cueRepository.save(ceu);
        
    }
    
    public void altaCursoUsuario(Integer idCurso, CursoUsuarioEvento ceu){
        Curso c=cursoRepository.findById(idCurso).orElseThrow(
                ()-> new CursoNotFound("No existe el curso seleccionado"));
        
         //obtengo la lista de alumnos de un curso
        List<Usuario> alumnos=usuarioRepository.findByCurso(c);
        for(Usuario u: alumnos){
            CursoUsuario cu=new CursoUsuario();
            cu.setUsuarioIdUsuario(u);
             cu.setCursoIdCurso(c);
             cuRepository.save(cu);
             //ahora guardo el evento de un curso en cada alumno dentro de este
             ceu.setCursoUsuarioIdCursoUsuario(cu);
        }
       
    }
    
    
    
    public List<EventosView> obtenerEventosPosteriores(Usuario usuarioIdUsuario){
       List<EventosView> obtener=eventoRepository.obtenerEventosPosteriores(usuarioIdUsuario);
       return obtener;
    }
    
}
