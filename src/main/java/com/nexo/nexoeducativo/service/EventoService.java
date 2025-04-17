package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EventoNotFoundException;
import com.nexo.nexoeducativo.exception.HoraInvalidatedexception;
import com.nexo.nexoeducativo.models.dto.request.EditarEventoDTO;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
     private static final Logger LOGGER = LoggerFactory.getLogger(EventoService.class);
    
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
               LOGGER.info("LO QUE LLEGA: "+e.getDescripcion()+" "+e.getFecha());
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
    
     public List<EventosView> obtenerEventos(Integer curso){
       List<EventosView> obtener=eventoRepository.obtenerEventos(curso);
       return obtener;
    }
    
    public void verificarCampos(EditarEventoDTO dto, Evento ingresado) {
    LOGGER.info("DTO recibido: " + dto);

    if (dto.getDescripcion() != null && !dto.getDescripcion().isEmpty()) {
        ingresado.setDescripcion(dto.getDescripcion());
        LOGGER.info("Descripción: " + ingresado.getDescripcion());
    } else {
        LOGGER.info("Descripción vacía");
    }

    // Verificar fecha
    if (dto.getFecha() != null) {
        // Obtén la fecha actual (en formato LocalDateTime)
        LocalDateTime fechaActual = LocalDateTime.now();

        // Comparar fechas
        if (dto.getFecha().isBefore(fechaActual)) {  // Usamos LocalDateTime directamente para la comparación
            throw new HoraInvalidatedexception("No puedes agendar un evento antes de la fecha actual " + dto.getFecha());
        }

        ingresado.setFecha(Date.from(dto.getFecha().atZone(ZoneId.systemDefault()).toInstant()));  // Si es necesario, convertir a Date
    }

    // Guardar evento
    eventoRepository.save(ingresado);
}

    
    @Transactional
    public Evento editarEvento(EditarEventoDTO dto){
        LOGGER.info("id evento ingresado: "+dto.getIdEvento());
        Evento modificado=eventoRepository.findById(dto.getIdEvento()).orElseThrow(
                ()-> new EventoNotFoundException("No existe el evento que se desea modificar"));
        
        verificarCampos(dto, modificado);
        return modificado;
    }
    
    @Transactional
    public void borrarEvento(Integer idEvento){
        Evento e=eventoRepository.findById(idEvento).orElseThrow(
                ()-> new EventoNotFoundException("No existe el evento"));
        cueRepository.deleteByEventoIdEvento(e);
        eventoRepository.deleteById(idEvento);
        
    }
    
}
