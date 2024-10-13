/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioUsuario;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martina
 */
@Service
//logica de negocio
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuariorepository;
    
    @Autowired
    private UsuarioUsuarioRepository usuarioUsuarioRepo;
    
    @Autowired
    private CursoUsuarioRepository cursoUsuarioRepository;
 
   
    /*public void crearUsuario(UsuarioDTO u){
        //reglas de negocio, ejemplo campos obligatorios con validaciones
    }*/
    
     public void crearUsuario(UsuarioDTO uDTO){
         
         Rol r=new Rol();
         r.setIdRol(uDTO.getRol());//aca se inserta el id del rol segun la bbdd
        
        Usuario u = new Usuario();
        u.setNombre(uDTO.getNombre());
        u.setApellido(uDTO.getApellido());
        u.setEMail(uDTO.geteMail());
        u.setDni(uDTO.getDni());
        u.setTelefono(uDTO.getTelefono());
        u.setActivo(uDTO.getActivo());
        //probar esto mas tarde:
        //u.setActivo((uDTO.getActivo())? (short)1 : (short)0); 
        // Asegurarse de guardar el estado "activo"
        u.setRolidrol(r);
        
        if(!usuariorepository.existsByDni(u.getDni())){
             this.usuariorepository.save(u);
        }
    }
     //CHEQUEAR
     public void crearAlumno(AlumnoDTO a){
         
         Rol r=new Rol();
         r.setIdRol(7);//nro de rol Alumno segun la bbdd
         
         Curso c=new Curso();
         int idC=a.getIdCurso();
         c.setIdCurso(idC);
         
         Usuario alumno = new Usuario();
         alumno.setNombre(a.getNombre());
         alumno.setApellido(a.getApellido());
         alumno.setEMail(a.geteMail());
         alumno.setDni(a.getDni());
         alumno.setTelefono(a.getTelefono());
         alumno.setActivo(a.getActivo());
         alumno.setRolidrol(r);
         
         Usuario padre=new Usuario();
         padre.setIdUsuario(a.getIdPadre());
         
         //tabla intermedia para asociar un alumno a un curso
         CursoUsuario cu= new CursoUsuario();
         cu.setCursoIdCurso(c);
         cu.setUsuarioIdUsuario(alumno);
         
         //tabla intermedia para asociar un alumno con un padre
         UsuarioUsuario u2=new UsuarioUsuario();
         u2.setUsuarioIdUsuario(alumno);
         u2.setUsuarioIdUsuario1(padre);
         
         //validar si ya el alumno ingresado pertenece a un curso
         if(usuarioUsuarioRepo.existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(alumno.getIdUsuario(), padre.getIdUsuario())){
             throw new IllegalArgumentException("ese alumno ya tiene asociado a ese padre");
         }
         //validar si ya existe ese alumno en ese curso
         else if(cursoUsuarioRepository.existsByCursoIdCursoAndUsuarioIdUsuario(idC, alumno.getIdUsuario())){
             throw new IllegalArgumentException("ese alumno ya existe ene se curso");
         }else{
             this.usuariorepository.save(alumno);
             this.usuariorepository.save(padre);
             this.cursoUsuarioRepository.save(cu);
             this.usuarioUsuarioRepo.save(u2);
         }
        
     }
}
