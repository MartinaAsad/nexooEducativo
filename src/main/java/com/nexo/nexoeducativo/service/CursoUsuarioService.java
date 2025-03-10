
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
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
    // Verificar el rol de preceptor
    Rol rolPreceptor = rolRepository.findById(4)
            .orElseThrow(() -> new RolNotFound("El rol de Preceptor no existe"));
    
    // Obtener el curso
    Curso c = cursoRepository.getReferenceById(ap.getCurso()); // Usar getReferenceById en lugar de findById
    
    // Paso 1: Manejar el usuario
    Usuario u;
    Optional<Usuario> usuarioExistente = usuarioRepository.findByDni(ap.getPreceptor());
    
    if (usuarioExistente.isPresent()) {
        u = usuarioExistente.get();
    } else {
        // Crear nuevo usuario
        u = new Usuario();
        u.setDni(ap.getPreceptor());
        u.setNombre("");
        u.setApellido("");
        u.setMail("");
        u.setActivo((short)1);
        u.setRolidrol(rolPreceptor);
        
        // Guardar usuario
        u = usuarioRepository.saveAndFlush(u);
    }
    
    // Paso 2: Eliminar asignaciones existentes (si hay)
    Optional<CursoUsuario> asignacionExistente = cuRepository.siYaFueAsignado(c.getIdCurso());
    if (asignacionExistente.isPresent()) {
        cuRepository.deleteById(asignacionExistente.get().getIdCursoUsuario());
        cuRepository.flush();
    }
    
    // Asegurarse de que el usuario tenga un ID asignado
    if (u.getIdUsuario() == null) {
        throw new RuntimeException("El ID del usuario no fue asignado correctamente");
    }
    
    // Crear una nueva asignación utilizando solo los IDs
    CursoUsuario nuevaAsignacion = new CursoUsuario();
    nuevaAsignacion.setCursoIdCurso(c);
    nuevaAsignacion.setUsuarioIdUsuario(u);
    
    cuRepository.saveAndFlush(nuevaAsignacion);
}

    
}
