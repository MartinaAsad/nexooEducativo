
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.models.dto.request.AsignarPreceptorDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoUsuarioService {
    
    @Autowired
    private CursoUsuarioRepository cuRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    public void asignarPreceptor(AsignarPreceptorDTO ap){
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
    
}
