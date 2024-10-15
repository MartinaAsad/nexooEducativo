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
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioUsuarioRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    @Autowired
    private CursoRepository cursoRepository;
    
    @Autowired
    private RolRepository rolRepository;
 
   
    /*public void crearUsuario(UsuarioDTO u){
        //reglas de negocio, ejemplo campos obligatorios con validaciones
    }*/
    
    public static final String convertirSHA256(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }    
    
    
     public void crearUsuario(UsuarioDTO uDTO){
         
         Rol r=new Rol();
         r.setIdRol(uDTO.getRol());//aca se inserta el id del rol segun la bbdd
        
        Usuario u = new Usuario();
        u.setNombre(uDTO.getNombre());
        u.setApellido(uDTO.getApellido());
        u.setMail(uDTO.getEMail());
        //U.setClave(convertirSHA256(uDTO.getClave()); 
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
     
     public void crearAlumno(AlumnoDTO a){
       Rol rolAlumno = rolRepository.findById(7)
        .orElseThrow(() -> new IllegalArgumentException("El rol de Alumno no existe"));

        Curso curso = cursoRepository.findById(a.getIdCurso())
            .orElseThrow(() -> new IllegalArgumentException("El curso no existe"));

        if (usuariorepository.existsByDni(a.getDni())) {
            throw new IllegalArgumentException("El alumno ya existe");
        }
        Usuario alumno = new Usuario();
        alumno.setNombre(a.getNombre());
        alumno.setApellido(a.getApellido());
        alumno.setMail(a.getEMail());
        alumno.setDni(a.getDni());
        alumno.setTelefono(a.getTelefono());
        alumno.setActivo(a.getActivo());
        alumno.setRolidrol(rolAlumno);
        alumno = this.usuariorepository.save(alumno);

        if (cursoUsuarioRepository.existsByCursoIdCursoAndUsuarioIdUsuario(curso, alumno)) {
            throw new IllegalArgumentException("El alumno ya está asignado a ese curso");
        }
        Usuario padre = usuariorepository.findById(a.getIdPadre())
            .orElseThrow(() -> new IllegalArgumentException("El padre no existe"));

        Rol rolPadre = rolRepository.findById(6)
            .orElseThrow(() -> new IllegalArgumentException("El rol de Padre no existe"));

        if (!verificarPermisos(rolPadre, 6)) {
            throw new IllegalArgumentException("El rol asignado no es un rol de padre válido: " + rolPadre.getIdRol());
        }

        padre.setRolidrol(rolPadre);
        padre = usuariorepository.save(padre);

        if (usuarioUsuarioRepo.existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(alumno, padre)) {
            throw new IllegalArgumentException("Ese alumno ya tiene asociado a ese padre");
        }

        // Create the course-student relationship
        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setCursoIdCurso(curso);
        cursoUsuario.setUsuarioIdUsuario(alumno);
        this.cursoUsuarioRepository.save(cursoUsuario);

        // Create the student-parent relationship
        UsuarioUsuario usuarioUsuario = new UsuarioUsuario();
        usuarioUsuario.setUsuarioIdUsuario(alumno);
        usuarioUsuario.setUsuarioIdUsuario1(padre);
        this.usuarioUsuarioRepo.save(usuarioUsuario);
    }

    private boolean verificarPermisos(Rol rolVerificar, int nro) {
         return rolVerificar != null && rolVerificar.getIdRol() == nro;
    }
     
        
     
}
