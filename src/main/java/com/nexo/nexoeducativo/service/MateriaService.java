package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.HoraInvalidatedexception;
import com.nexo.nexoeducativo.exception.MateriaExistingException;
import com.nexo.nexoeducativo.exception.MateriaNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.dto.request.MateriaDTO;
import com.nexo.nexoeducativo.models.dto.request.MateriaView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.MateriaEscuela;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoEscuelaRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.MateriaEscuelaRepository;
import com.nexo.nexoeducativo.repository.MateriaRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaService {
    
    @Autowired
     private MateriaRepository materiaRepository;
    
    @Autowired
    private MateriaCursoRepository materiaCursoRepository;
    
    @Autowired
    private MateriaEscuelaRepository materiaEscuelaRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoEscuelaRepository cursoEscuelaRepository;
      private static final Logger LOGGER = LoggerFactory.getLogger(MateriaService.class);
    
    public void crearMateria(MateriaDTO m, Escuela e) {
        //inserto lo ingresado en el dto en las entidades correspondientes

        Materia materia = new Materia();
        materia.setNombre(m.getNombre());

        MateriaEscuela me = new MateriaEscuela();
        me.setMateriaIdMateria(materia);
        me.setEscuelaIdEscuela(e);
        //ver si ya existe la materia a ingresar en esa escuela
        if(materiaEscuelaRepository.siExisteMateria(m.getNombre(), e)){
            throw new MateriaExistingException("Ya existe la materia "+m+" en esa escuela");
        }
        
        //si la escuela esta inactica
        if (cursoEscuelaRepository.existsByCursoIdCursoAndEscuelaIdEscuela(e.getIdEscuela()) == 0) {
            //excepcion de ejemplo, solo para comporobar si efectivamente funciona la query
            throw new CursoNotFound("La escuela esta inactiva");
        }
   
                materiaRepository.save(materia);
                materiaEscuelaRepository.save(me);
            }

    
    
    @Transactional 
    public int borrarMateria(int idCurso, int idMateria){
        Curso cursoIdCurso=new Curso();
        cursoIdCurso.setIdCurso(idCurso);
        Materia materiaIdMateria=new Materia();
        materiaIdMateria.setIdMateria(idMateria);
        
        int seBorroCorrectamente=materiaCursoRepository.deleteByCursoIdCursoAndMateriaIdMateria(cursoIdCurso, materiaIdMateria);
        return seBorroCorrectamente;
    }
    
    public List<DesplegableMateriaView> verMaterias (Curso cursoIdCurso){
        List<DesplegableMateriaView> materias=materiaCursoRepository.verMaterias(cursoIdCurso);
        return materias;
    }
    
    public List<DesplegableMateriaView> mostrarMateriasProfe(Curso cursoIdCurso, Usuario profesor){
        List<DesplegableMateriaView> materias= materiaCursoRepository.findNombresMateriasPorCursoYProfesor(cursoIdCurso, profesor);
        return materias;
    }
    
    public List<String> verMateriasEscuela (Escuela escuelaIdEscuela){
        List<String> verMateriasEscuela=materiaEscuelaRepository.materiasSegunEscuela(escuelaIdEscuela);
        return verMateriasEscuela;
    }
    
    public List<DesplegableMateriaView> verMateriasSegunHijo(Integer id){
        Usuario usuarioIdUsuario=usuarioRepository.findById(id).orElseThrow(
        ()-> new UsuarioNotFoundException("No existe el hijo seleccionado"));
        List<DesplegableMateriaView> verMaterias=materiaRepository.materiasSegunHijo(usuarioIdUsuario);
        return verMaterias;
    }
    
    public void modificarMateria(int idMateria, MateriaDTO nombre, Escuela e){
        //boolean modifico=false;
        Materia materia=materiaRepository.findById(idMateria).orElseThrow(
        ()-> new MateriaNotFoundException("La materia ingresada no existe"));
           
        Escuela escuela=escuelaRepository.findById(idMateria).orElseThrow(()->
        new EscuelaNotFoundException("No existe la escuela ingresada"));
        
        //en caso de que exista la materia, chequear si el nombre a modificar NO existe
        if(materia!=null &&materiaEscuelaRepository.siExisteMateria(nombre.getNombre(), escuela)){
           Materia existente=materia;
           existente.setNombre(nombre.getNombre());
           
           //guardo los cambios
            materiaRepository.save(existente);
            //modifico=false;
        }else{
            throw new MateriaExistingException("La materia ingresada ya existe");
            
        }
        
        //return modifico;
        
    }
    
}
