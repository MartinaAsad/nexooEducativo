
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.models.dto.request.AsignarPreceptorDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoUsuarioService {
    
    @Autowired
    private CursoUsuarioRepository cuRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
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
    
   public void actualizarPreceptor(AsignarPreceptorDTO ap) {
    LOGGER.info("VALORES QUE LLEGAN ACA: " + ap.toString());

    // Verificar el rol de preceptor
    Rol rolPreceptor = rolRepository.findById(4)
            .orElseThrow(() -> new RolNotFound("El rol de Preceptor no existe"));

    // Obtener el curso
    Curso c = new Curso();
    c.setIdCurso(ap.getCurso());

    // Obtener el usuario por su DNI
    Optional<Usuario> usuarioExistente = usuarioRepository.findById(ap.getPreceptor());
    
    // Si el usuario no existe, lo creamos
    Usuario u;
    if (!usuarioExistente.isPresent()) {
        u = new Usuario();
        u.setDni(ap.getPreceptor());
        u.setRolidrol(rolPreceptor);
        usuarioRepository.save(u); // Guardar el nuevo usuario
    } else {
        u = usuarioExistente.get(); // Si ya existe, lo utilizamos
    }

    // Verificar si ya existe una asignación del preceptor a un curso
    Optional<CursoUsuario> asignacionExistente = cuRepository.siYaFueAsignado(c.getIdCurso());

    // Si el preceptor ya está asignado a un curso, eliminamos la asignación anterior
    asignacionExistente.ifPresent(cuRepository::delete);

    // Ahora creamos la nueva asignación con el curso actual
    CursoUsuario nuevaAsignacion = new CursoUsuario();
    nuevaAsignacion.setCursoIdCurso(c);
    nuevaAsignacion.setUsuarioIdUsuario(u);

    // Guardamos la nueva asignación
    cuRepository.save(nuevaAsignacion);

    LOGGER.info("Asignación exitosa: El preceptor con DNI " + u.getDni() + " ha sido asignado al curso " + c.getIdCurso());
}

    
}
