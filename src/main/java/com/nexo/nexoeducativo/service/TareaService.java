package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CalificacionWrongException;
import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.FormatoIncorrectoException;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.exception.MaterialNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.CalificacionesHijoView;
import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.dto.request.EventosDTO;
import com.nexo.nexoeducativo.models.dto.request.EventosView;
import com.nexo.nexoeducativo.models.dto.request.InfoMateriaHijoView;
import com.nexo.nexoeducativo.models.dto.request.NotaDTO;
import com.nexo.nexoeducativo.models.dto.request.ObtenerTareaView;
import com.nexo.nexoeducativo.models.dto.request.TareaDTO;
import com.nexo.nexoeducativo.models.entities.Calificacion;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.UsuarioTarea;
import com.nexo.nexoeducativo.repository.CalificacionRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.EventoRepository;
import com.nexo.nexoeducativo.repository.MateriaRepository;
import com.nexo.nexoeducativo.repository.TareaRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioTareaRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Autowired
    private TareaRepository tareaRepository;
    
    @Autowired
    private EventoRepository eventoRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TareaService.class);
    
    @Transactional
    public Tarea altaTarea(TareaDTO tarea, Integer cursoIdCurso){
        Calificacion c=altaCalificacion(tarea);
        
        Materia m=materiaRepository.findById(tarea.getIdMateria()).orElseThrow(
        ()-> new MateriaNotFoundException("No existe la materia ingresada"));
        
;        Tarea t=new Tarea();
        //VER PROBLEMA CON LA DESCRIPCION
        t.setDescripcion(tarea.getDescripcion());
        t.setArchivo(null);
        t.setCalificacionIdCalificacion(c);
        t.setMateriaIdMateria(m);
        
        LOGGER.info("Calificacion a guardar: "+t.getCalificacionIdCalificacion());
        Tarea tGuardada=tareaRepository.save(t);
        
        //asociar la tarea al curso
        Curso curso=cursoRepository.findById(cursoIdCurso)
                .orElseThrow(()-> new CursoNotFound("El curso ingresado no existe"));
        asociarTareaUsuario(tGuardada, curso, tarea);//error aca
        
        return tGuardada;
        
    }
    public void asociarTareaUsuario(Tarea t, Curso c, TareaDTO tarea){
        //obtengo la lista de alumnos de un curso
        List<Usuario> alumnos=usuarioRepository.findByCurso(c);
        //obtengo la materia en donde se agrega esa materia
        Materia m=materiaRepository.findById(tarea.getIdMateria()).orElseThrow(
        ()-> new MateriaNotFoundException("No existe la materia ingresada"));
        Calificacion calif=altaCalificacion(tarea);
        for(Usuario u: alumnos){
            UsuarioTarea ut=new UsuarioTarea();
            ut.setTareaIdTarea(t); 
            ut.setUsuarioIdUsuario(u);
            ut.setCalificacionIdCalificacion(calif);
            ut.setMateriaIdMateria(m);
            usuarioTRepository.save(ut);//error aca
        }
        
        
    }
    
    public Calificacion altaCalificacion(TareaDTO tarea){
        Calificacion c=new Calificacion();
        c.setNota("Tarea sin calificar");
         String fechaNueva=hoy.format(formato);
         LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
        Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
        c.setFecha(fechaDate);
        return califRepository.save(c);
    }
    
    @Transactional
    public void editarTarea (NotaDTO n, String descripcion){
   
            //obtener la calificacion de la tarea a editar
          Tarea t = tareaRepository.findById(n.getIdTarea()).orElseThrow(
                () -> new MaterialNotFoundException("No existe la tarea a editar"));

        //actualizar la calificacion SOLO SI VINO EN EL DTO
        if (!n.getCalificacion().isEmpty()) {
            if (n.getCalificacion().length() >= 1 && n.getCalificacion().length() <= 28) {
                String nota = n.getCalificacion();
                Calificacion c = t.getCalificacionIdCalificacion();
                Integer idCalificacion = c.getIdCalificacion();
                califRepository.updateNotaByIdCalificacion(nota, idCalificacion);
            }
        }

        //acualizar la descripcion SOLO SI VINO EN EL DTO
        if (!descripcion.isBlank()) {
            //chequear el largo
            if (descripcion.length() >= 5 && descripcion.length() <= 255) {
                Integer idTarea = t.getIdTarea();
                tareaRepository.updateDescripcionByIdMateria(descripcion, idTarea);
            } else {
                throw new FormatoIncorrectoException("Minimo 5 caracteres y maximo 255 en la descripcion");
            }

        }

        
      
    }
    
    public List<String> obtenerTareas (Usuario idAlumno){
        List<String> tareas=tareaRepository.descripcionTareas(idAlumno);
        return tareas;
    }
    
      public List<ObtenerTareaView> obtenerTareasProfe (Curso c, Materia materiaIdMateria ){
        List<ObtenerTareaView> tareas=tareaRepository.descripcionTareasProfe(c.getIdCurso(),materiaIdMateria.getIdMateria());
        return tareas;
    }
    
    public HashMap<String, String> agregarInfo (List<Object[]> tareas){
         HashMap<String, String> info=new HashMap<>();
        for (Object[] fila : tareas) {
            String key = (String) fila[0];
            LOGGER.info("la key: "+key);
            String value = (String) fila[1];
            info.put(key, value);            
        }
        return info;
    }
    
    
      public List<InfoMateriaHijoView> obtenerInformacion (Integer hijo){
        //primero, obtengo todas las calificaciones 
        Usuario usuarioIdUsuario=usuarioRepository.findById(hijo)
            .orElseThrow(() -> new UsuarioNotFoundException("El usuario que se desea ver no existe")); 
        
        List<CalificacionesHijoView> tareas=tareaRepository.obtenerCalificaciones(usuarioIdUsuario);
       //HashMap<String, String> nota=agregarInfo(tareas);
       LOGGER.info("lista:"+tareas);
       
       //luego, obtengo los proximos eventos
        List<EventosView> eventos=eventoRepository.obtenerEventosPosteriores(usuarioIdUsuario); 
        
        //cambio el formato de las fechas que llegan
          List<EventosDTO> eventosDTO = eventos.stream()
                  .map(evento -> {
                      String fecha="";
                     if (evento.getFecha() != null) {
                LocalDateTime localDateTime = evento.getFecha().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
                fecha = localDateTime.format(formato);
            }
            return new EventosDTO(evento.getDescripcion(), fecha);
                  })
                  .collect(Collectors.toList());
        
        //almaceno toda esta info aqui
        InfoMateriaHijoView infor=new InfoMateriaHijoView();
        infor.setNotas(tareas);
        infor.setEventos(eventosDTO);
        List<InfoMateriaHijoView> info=new ArrayList<>();
        info.add(infor);
       
        
        return info;
        
    }
    
}
