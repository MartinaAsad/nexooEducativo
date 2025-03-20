
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
        LOGGER.info("VALORES QUE LLEGAN ACA: "+ap.toString());
        Rol rolPreceptor= rolRepository.findById(4)
                 .orElseThrow(() -> new RolNotFound("El rol de Preceptor no existe"));
        
        Curso c=new Curso();
        c.setIdCurso(ap.getCurso());
           
        Usuario u=new Usuario();
        u.setDni(ap.getPreceptor());
        u.setRolidrol(rolPreceptor);
        
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
    Curso curso = cursoRepository.findById(ap.getCurso())
            .orElseThrow(() -> new CursoNotFound("No existe el curso seleccionado"));

    // Paso 2: Obtener el usuario (preceptor)
    Usuario usuario = usuarioRepository.findById(ap.getPreceptor())
            .orElseThrow(() -> new UsuarioNotFoundException("No existe el usuario"));

    // Paso 3: Verificar si ya existe una asignación para este curso
    Optional<CursoUsuario> asignacionExistente = cuRepository.findByCursoIdCurso(curso);

    if (asignacionExistente.isPresent()) {
        // Si ya existe una asignación, la eliminamos
        cuRepository.delete(asignacionExistente.get());
        cuRepository.flush(); // Forzar la eliminación antes de continuar
    }

    // Paso 4: Crear una nueva instancia de CursoUsuario
    CursoUsuario cursoUsuario = new CursoUsuario();
    cursoUsuario.setCursoIdCurso(curso); // Asignar el curso
    cursoUsuario.setUsuarioIdUsuario(usuario); // Asignar el usuario

    // Paso 5: Guardar la nueva asignación
    cuRepository.save(cursoUsuario);

    LOGGER.info("Asignación exitosa: Preceptor con ID " + usuario.getIdUsuario() + " asignado al curso " + curso.getIdCurso());
}

    public Integer buscarCurso(Usuario u) {
        Integer curso = cuRepository.obtenerCurso(u);
        return curso;
    }

    
}
