
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.FormatoIncorrectoException;
import com.nexo.nexoeducativo.exception.HoraInvalidatedexception;
import com.nexo.nexoeducativo.exception.MateriaExistingException;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.AgregarInfoMateriaDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoView;
import com.nexo.nexoeducativo.models.dto.request.EditarCursoDTO;
import com.nexo.nexoeducativo.models.dto.request.EditarCursoMateriaDTO;
import com.nexo.nexoeducativo.models.dto.request.MateriaView;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoAsistenciaRepository;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import com.nexo.nexoeducativo.repository.CursoMensajeRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioEventoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.MateriaEscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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
public class CursoService {
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
    
    @Autowired
    private CursoUsuarioRepository cursoUsuarioRepository;
    
    @Autowired
    private MateriaCursoRepository materiaCursoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private MateriaRepository materiaRepository;
    
    @Autowired
    private MateriaEscuelaRepository meRepository;
    
    @Autowired
    private CursoAsistenciaRepository caRepository;
    
    @Autowired
    private CursoMensajeRepository cmRepository;
    
    @Autowired
    private CursoUsuarioEventoRepository cueRepository;
    
        
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoService.class);
    
       public boolean siYaExisteCombinacion(Integer escuelaId, int numero, Character division){
        return escuelaRepository.existsCursoInEscuela(escuelaId, numero, division);
    
}
    //PROBAR NUEVAMENTE
    public void crearCurso(CursoDTO c,Escuela e,List<AgregarInfoMateriaDTO> infoMaterias){
        Curso curso=new Curso();
        curso.setNumero(c.getNumero());
        curso.setDivision(c.getDivision());
        curso.setActivo(c.getActivo());
        int numero=curso.getNumero();
        Character division=curso.getDivision();
        int escuelaId=e.getIdEscuela();
        
        CursoEscuela ce=new CursoEscuela();//tabla intermedia donde se va a guardar a que escuela esta asociado ese curso
        ce.setCursoIdCurso(curso);//se guarda el id del curso creado
        ce.setEscuelaIdEscuela(e);//se guarda el id de la escuela asociada
        
        //valida que la division ingresada sea solo una letra
        if(!Character.isLetter(c.getDivision())){
            throw new FormatoIncorrectoException("La división debe ser un carácter alfabético");
        }else if (!Character.toString(curso.getDivision()).matches("^[a-z]$")){
//luego valida que el caracter ingresado sea una minuscula nomas
            throw new FormatoIncorrectoException("El campo division solo puede tener una letra minuscula");
        }
        
        //agregar las materias
        
        //para que se guarde el curso, el id del colegio,el numero y division ingresada (la combinacion) NO debe existir previamente en la base
        //VER QUE PROBLEMA HAY ACA
        if (!siYaExisteCombinacion(escuelaId,numero, division)) {
            this.cursoRepository.save(curso);//se guarda el curso
            this.cursoEscuelaRepository.save(ce);//se guarda la info en la tabla intermedia
            agregarMaterias(infoMaterias, e, c);
         }else{
             throw new CursoNotFound("El curso ya existe en la escuela!");
        }

    }
    
    public List<CursoView> seleccionarCurso (int idCurso){
        /*datos del curso*/
        Curso c=cursoRepository.findById(idCurso)
                 .orElseThrow(() -> new CursoNotFound("El curso no existe"));
        /*datos del preceptor*/
        // cu=cursoUsuarioRepository.findUsuarioIdUsuarioBycursoIdCurso(c);//obtengo el id del preceptor
        //LOGGER.info("info del repository: "+cu.getUsuarioIdUsuario());
        //Usuario u = cu.getUsuarioIdUsuario(); //guardo el id aqui para luego obtener nombre y apellido
        //LOGGER.info("info del repository: "+u.getRolidrol());
        
        UsuarioView p=usuarioRepository.infoPreceptor(c);
        //en caso de que no haya un preceptor
        if(p==null){
            throw new UsuarioNotFoundException("No hay un preceptor en este curso");
        }
        
        /*datos de la materia (nombre y profesor)*/
        List<Object[]> lista=materiaCursoRepository.infoMateria(c.getIdCurso());
        List<MateriaView> lista2 = lista.stream()
        .map(row -> new MateriaView((String) row[2], (String) row[0], (String) row[1]))
        .collect(Collectors.toList());
        // LOGGER.info("contenido de la lista"+lista2.toString());
         //LOGGER.info("Parametro : "+c);
         
         /*datos de los usuarios*/
          List<UsuarioView> alumnos=usuarioRepository.infoAlumnos(c);
         //LOGGER.info("lis a de alumnos: "+alumnos.toString());
         
        CursoView datosCurso=new CursoView(c.getNumero(), c.getDivision(), p.getNombre(), p.getApellido(), lista2, alumnos);
        List<CursoView> cView = new ArrayList<CursoView>();
        cView.add(datosCurso);
        return cView;
    }
    
    public List<verCursoView> verCursos(String mail){
        Escuela escuelaIdEscuela=usuarioRepository.obtenerIdEscuela(mail);//buscar el id de la escuela del usuario logueado
        List<verCursoView> cursos= usuarioRepository.obtenerCursos(escuelaIdEscuela);
        
        return cursos;
    }
    
     public List<verCursoView> verCursosAdministrativo(String mail){
       Escuela escuelaIdEscuela=usuarioRepository.obtenerIdEscuelaAdministrativo(mail);//buscar el id de la escuela del usuario logueado
       //LOGGER.info("la escuela encontrada para el usuario logueado es: "+escuelaIdEscuela.getIdEscuela());
      List<verCursoView> resultados = usuarioRepository.obtenerCursosAdministrativo(escuelaIdEscuela);

        return resultados;
        //return escuelaIdEscuela;
    }
     
        public List<verCursoView> verCursosPreceptor(String mail){
       Usuario usuario=usuarioRepository.findIdUsuarioByMail(mail);
       List<verCursoView> cursos= usuarioRepository.obtenerCursosPreceptor(usuario.getIdUsuario());
        
        
        return cursos;
    }
        
        
       public void actualizarCampos(EditarCursoDTO e,Curso c, Escuela escuela){
           if(e.getActivo()!=null){
               c.setActivo(e.getActivo());
           }
           
           //si ingreso numeror y division
               if (e.getDivision() != null && e.getNumero() != 0) {
                   boolean existe = siYaExisteCombinacion(escuela.getIdEscuela(), e.getNumero(), e.getDivision());
                   if (!existe) {
                       c.setDivision(e.getDivision());
                       c.setNumero(e.getNumero());
                   }else{
                   throw new FormatoIncorrectoException("Ya existe ese curso en la base de datos");
               }
               }
           
           //si solo ingreso el numero
           if (e.getNumero() != 0) {
               boolean existeNumero=escuelaRepository.existsCursoInEscuela(escuela.getIdEscuela(), e.getNumero(), c.getDivision());//ver si existe previamente el numero de curso a modificar
               LOGGER.info("LOL ENTRO EN EL IF");
               if(!existeNumero){
                   c.setNumero(e.getNumero());
                    //LOGGER.info("ES FALSO");
               }else{
                   throw new FormatoIncorrectoException("Ya existe ese curso en la base de datos");
               }
           }
           
            //si solo ingreso la division
           if (e.getDivision()!= null) {
               boolean existe=escuelaRepository.existsCursoInEscuela(escuela.getIdEscuela(), c.getNumero(), e.getDivision());//ver si existe previamente el numero de curso a modificar
               if(!existe){
                   c.setDivision(e.getDivision());
               }else{
                   throw new FormatoIncorrectoException("Ya existe ese curso en la base de datos");
               }
               
           }
           
           cursoRepository.save(c);
           
       } 
        
        
       
    public List<MateriaCurso> agregarMaterias(List<AgregarInfoMateriaDTO> infoMaterias, Escuela e,CursoDTO c) {
         List<MateriaCurso> materiasCurso = new ArrayList<>();

        //primero, seteo la materia en el curso creado
        Curso curso=new Curso();
        curso.setIdCurso(cursoEscuelaRepository.obtenerIdCursoCreado(e, c.getNumero(), c.getDivision()));
                
        
        for (AgregarInfoMateriaDTO info : infoMaterias) {
            Materia m = materiaRepository.findById(info.getIdMateria()).orElseThrow(()
                -> new MateriaNotFoundException("No existe la materia"));
        /*Curso c = cursoRepository.findById(info.getIdCurso()).orElseThrow(()
                -> new CursoNotFound("El curso no existe"));*/

        Usuario profesor = usuarioRepository.findById(info.getIdProfesor()).orElseThrow(()
                -> new UsuarioNotFoundException("El profesor ingresado no existe"));

        Rol rol = usuarioRepository.findRolidrolByIdUsuario(info.getIdProfesor());
        
          MateriaCurso mc = new MateriaCurso();
        mc.setCursoIdCurso(curso);
        mc.setDia(info.getDia());
        mc.setHoraFin(info.getHoraFin());
        mc.setHoraInicio(info.getHoraInicio());
        mc.setMateriaIdMateria(m);
        mc.setProfesor(profesor);

        //si el usuario es profesor
        if (rol.getIdRol() == 5) {
            //validar si ya existe una materia en ese mismo horario o que no se suponga. Ejemplo: biologia 1 12:00 - 13:00 2b lunes y jueves
            //NO ES VALIDO: biologia 1 12:30 a 13:30 2b lunes y jueves
            if (materiaCursoRepository.verSiYaExisteEsaMateria(curso, info.getDia(), info.getHoraInicio(), info.getHoraFin())) {
                throw new MateriaExistingException("Ya existe esa materia entre esos horarios en ese curso");
            } else {
                //LOGGER.info("primera validacion resultado: "+verSiYaExisteEsaMateria(m.getIdCurso(), m.getDia(), m.getHoraInicio(), m.getHoraFin()));
                if (info.getHoraFin().isBefore(info.getHoraInicio())) {
                    throw new HoraInvalidatedexception("La hora de finalizacion es inferior a la hora de inicio");

                } else {
                    materiaRepository.save(m);
                    materiaCursoRepository.save(mc);
                }
            }

        } else {
            throw new UsuarioNotAuthorizedException("El usuario que se desea ingresar no es un profesor");
        }

        }
        
        
        return materiasCurso;
    }
    public void modificarMaterias(List<EditarCursoMateriaDTO> lista, Curso c){
        for(EditarCursoMateriaDTO iterador:lista){
             Materia m=materiaRepository.findById(iterador.getIdMateria()).orElseThrow(
                    ()-> new MateriaNotFoundException("Esa materia no existe"));
            MateriaCurso mCurso = materiaCursoRepository.
                    findByCursoIdCursoAndMateriaIdMateriaAndDiaIsNotNullAndHoraInicioIsNotNullAndHoraFinIsNotNull(c, m);
            //si se cambio la materia pero no el profesor
            if(iterador.getIdMateria()!=0 & iterador.getIdProfesor()==null){
             /*Materia m=materiaRepository.findById(iterador.getIdMateria()).orElseThrow(
                    ()-> new MateriaNotFoundException("Esa materia no existe*/
            mCurso.setMateriaIdMateria(m);
            }//si se cambia el profe y no la materia
            else if(iterador.getIdProfesor()!=null){
             Usuario profesor = usuarioRepository.findById(iterador.getIdProfesor()).orElseThrow(()->
              new UsuarioNotFoundException("No existe ese profesor"));
                 mCurso.setProfesor(profesor);
            }
            
            if(iterador.getDia()!=null){
                mCurso.setDia(iterador.getDia());
            }
            
            //si solo se hizo cambio en la hora pero NO en el dia
            if(iterador.getHoraInicio()!=null){
                boolean existe=materiaCursoRepository.existsByCursoIdCursoAndHoraInicioAndDia(c,iterador.getHoraInicio(), mCurso.getDia());
                if(!existe){
                    mCurso.setHoraInicio(iterador.getHoraInicio());
                }
            }else if(iterador.getHoraFin()!=null){
                boolean existe=materiaCursoRepository.existsByCursoIdCursoAndHoraFinAndDia(c,iterador.getHoraFin(), mCurso.getDia());
                if(!existe){
                    mCurso.setHoraFin(iterador.getHoraFin());
                }
            }
            
            //si se cambian ambos horarios
            if(iterador.getHoraInicio()!=null &&iterador.getHoraFin()!=null){
                boolean existe=materiaCursoRepository.verSiYaExisteEsaMateria(c, mCurso.getDia(), iterador.getHoraInicio(), iterador.getHoraFin());
           if(!existe){
                mCurso.setHoraInicio(iterador.getHoraInicio());
                    mCurso.setHoraFin(iterador.getHoraFin());
                }
            }
            
            
        }
    }
    
    @Transactional
    public void modificarCurso(EditarCursoDTO e, Integer idCurso, Escuela escuela){
        Curso c=cursoRepository.findById(idCurso).orElseThrow(()-> new CursoNotFound("No existee el curso ingresado"));
        actualizarCampos(e, c, escuela);
        
        //solo actualizar materias ssi vhay materias para actualizar, sino no
        if(e.getMaterias() != null && !e.getMaterias().isEmpty()){
    modificarMaterias(e.getMaterias(), c);
}
    }
    
    @Transactional
    public void borrarCurso(Integer idCurso){
        Curso c=cursoRepository.findById(idCurso).orElseThrow(
                ()-> new CursoNotFound("No existe el curso"));
        
         List<CursoUsuario> cu=cursoUsuarioRepository.obtenerRegistros(c);
         if(!cu.isEmpty()){
          for (CursoUsuario cursoUsuario : cu) {
             cueRepository.deleteByCursoUsuarioIdCursoUsuario(cursoUsuario); 
        }
        //borrar tabla curso y sus referencias
        caRepository.deleteByCursoIdCurso(c);
        cursoEscuelaRepository.deleteByCursoIdCurso(c);
        cmRepository.deleteByCursoIdCurso(c);
        cursoUsuarioRepository.deleteByCursoIdCurso(c);      
         }
        
        cursoRepository.deleteById(idCurso);
    }
}
