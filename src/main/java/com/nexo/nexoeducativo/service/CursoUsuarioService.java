
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.AsignarPreceptorDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoUsuarioService {
    
    @Autowired
    private CursoUsuarioRepository cuRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private EscuelaService escuelaService;
    
      private static final Logger LOGGER = LoggerFactory.getLogger(CursoUsuarioService.class);
    public void asignarPreceptor(AsignarPreceptorDTO ap){
        Rol rolPreceptor= rolRepository.findById(4)
                 .orElseThrow(() -> new RolNotFound("El rol de Preceptor no existe"));
        
        Curso c=cursoRepository.findById(ap.getCurso())
                 .orElseThrow(() -> new CursoNotFound("El curso no existe"));
           
        Usuario u=usuarioRepository.findByDni(ap.getPreceptor())
                 .orElseThrow(() -> new UsuarioNotFoundException("El preceptor no existe "+ap.getPreceptor()));
        
        Optional<CursoUsuario> verSiYaFueAsignado=cuRepository.siYaFueAsignado(c.getIdCurso());    //aca hay un problema
        
        //primero verificar si el rol del preceptor correpsonde a un preceptor y que no haya registros previos de ese usuario
        if(u.getRolidrol().getIdRol()!=rolPreceptor.getIdRol()){
            throw new UsuarioNotAuthorizedException("El usuario que se desea ingresar no es un preceptor");
        }else{
            if(!verSiYaFueAsignado.isPresent()){
                CursoUsuario cu=new CursoUsuario();
                cu.setCursoIdCurso(c);
                cu.setUsuarioIdUsuario(u);   
                cuRepository.save(cu);
            }else{
                throw new UsuarioAssignedException("El usuario ya esta asignado como preceptor en otro curso");
            }

        }
        
    }
    
@Transactional
public void actualizarPreceptor(AsignarPreceptorDTO ap) {
    // Paso 1: Obtener el curso
    System.out.println("VALORES QUE LLEGAN ACA ACTUALIZAR PRECEPTOR: "+ap.toString());
    Curso curso = cursoRepository.findById(ap.getCurso())
            .orElseThrow(() -> new CursoNotFound("No existe el curso seleccionado"));
    
    Usuario preceptor=usuarioRepository.findById(ap.getPreceptor())
            .orElseThrow(()-> new UsuarioNotFoundException("No existe ese preceptor"));

    // Paso 3: Verificar si ya existe una asignación para este curso
    Optional<CursoUsuario> asignacionExistente = cuRepository.findByCursoIdCursoAndUsuarioIdUsuario(curso, preceptor);
    Optional<CursoUsuario> asignacionExistentePrecpetor = cuRepository.findByUsuarioIdUsuario(preceptor);

    if (!asignacionExistentePrecpetor.isEmpty()) {
        // Si el precpetor fue asignado previamente a un curso, borrar ese registro
       cuRepository.delete(asignacionExistentePrecpetor.get());
       //luego, asignarlo al nuevo curso
        asignarCurso(preceptor, curso);
    }else{
        //si el preceptor a editar nunca estuvo en un curso, asignarlo desde cero
        asignarCurso(preceptor, curso);
        
        
    }

    LOGGER.info("Asignación exitosa: Preceptor con ID " + preceptor.getIdUsuario() + " asignado al curso " + curso.getIdCurso());
}


@Transactional
public void asignarCurso (Usuario preceptor, Curso curso){
     CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setCursoIdCurso(curso); // Asignar el curso
        cursoUsuario.setUsuarioIdUsuario(preceptor); // Asignar el usuario
        cuRepository.save(cursoUsuario);
}

    public Integer buscarCurso(Usuario u) {
        Integer curso = cuRepository.obtenerCurso(u);
        return curso;
    }

    
}
