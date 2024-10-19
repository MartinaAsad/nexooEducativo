/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.AdministrativoDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.PlanDTO;
import com.nexo.nexoeducativo.models.dto.request.RolDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.service.CursoEscuelaService;
import com.nexo.nexoeducativo.service.CursoService;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.PlanService;
import com.nexo.nexoeducativo.service.RolService;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martina
 */
@RestController
@RequestMapping("/api/usuario") //reemplazarlo por api
public class UsuarioController {
    //los controladores se comunican con el frontend
     @Autowired
    private UsuarioService uService;
    
    @Autowired
    private RolService rolService;
    
    @Autowired
    private CursoService cursoService;
    
    @Autowired
    private EscuelaService escuelaService;
    
    @Autowired
    private PlanService planService;
    //comentario de prueba
    
    @Autowired
    private CursoEscuelaService cursoEscuelaService;
    
    //aca especifica que roles de usuario estan autorizados a usar ese endpoint, lo de hasAuthority es segun lo escrito en la bbdd
    //se usan operadores de base de datos
    /*@PreAuthorize("hasAuthority('ROLE_BM_BACKOFFICE_ADMIN') "
            + "or hasAuthority('ROLE_BM_BACKOFFICE_DACTILAR') "
            + "or hasAuthority('ROLE_BM_BACKOFFICE_FACIAL') "
            + "or hasAuthority('ROLE_BM_BACKOFFICE_FACIAL_SEGURIDAD')")*/
    @GetMapping("/getUsuario")
    public ResponseEntity<?> prueba(){
        return new ResponseEntity<>("prueba", HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('administrativo') "
            + "or hasAuthority('jefe colegio') ")
    @PostMapping("/saveUsuario")
    public ResponseEntity<?> prueba2(@Valid @RequestBody UsuarioDTO u ) throws Exception{
        uService.crearUsuario(u);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
        return new ResponseEntity<>("se guardo exitosamente el usuario creado", HttpStatus.OK);
        
        //cree esto como jefeColegio, ponerlo en el postman luego ejecutarlo
        /*{
   "nombre": "Juan",
   "apellido":"Perez",
   "dni":12345678,
   "eMail":"mail@gmail.com",
   "clave":"123456",
   "telefono":42956630,
   "activo":1,
   "rol":2
}*/
        
        //cree esto como padre, ponerlo en el postman luego ejecutarlo
        /*{
   "nombre": "Pia",
   "apellido":"Rodriguez",
   "dni":12345578,
   "eMail":"mail6@gmail.com",
   "telefono":11445566,
   "activo":1,
   "rol":6
}
        */
    }
    @PreAuthorize("hasAuthority('super admin') ")
    @PostMapping("/saveEscuela")
     public ResponseEntity<?> prueba3(@Valid @RequestBody EscuelaDTO e ){
        /*if (u.getEMail().isEmpty()){
             return new ResponseEntity<>("email vacio", HttpStatus.BAD_REQUEST);
        }*/
        
        escuelaService.crearEscuela(e);//NO ME TOMA LAS VALIDACIONES HECHAS DEL SERVICE
        
        //poner esto en el Postman
        /*{
   "nombre":"escuela 1",
   "direccion":"direccion 1",
   "activo":1,
   "idPlan":1,
   "jefeColegio":3
}*/
        return new ResponseEntity<>("prueba", HttpStatus.OK);
    }
     @PreAuthorize("hasAuthority('super admin') ")
      @PostMapping("/saveRol")
     public ResponseEntity<?> prueba4(@Valid @RequestBody RolDTO r){
         
          rolService.crearRol(r);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
          return new ResponseEntity<>("prueba rol", HttpStatus.OK);
     }
     @PreAuthorize("hasAuthority('administrativo') "
            + "or hasAuthority('preceptor') ")
     @PostMapping("/saveCurso")
     public ResponseEntity<?> prueba5(@Valid @RequestBody CursoDTO r){
         
         cursoService.crearCurso(r);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
          return new ResponseEntity<>("el curso fue creado correctamente", HttpStatus.OK);
          
          //cree esto como curso, ponelo en el postman y ejecutalo
          /*
          {
   "numero":2,
   "division":"c",
   "activo":1,
   "escuela":4

}*/
          
          
     }
     @PreAuthorize("hasAuthority('super admin') ")
     @PostMapping("/savePlan")
     public ResponseEntity<?> prueba6(@Valid @RequestBody PlanDTO p){
         planService.crearPlan(p);
         return new ResponseEntity<>("el plan fue creado correctamente", HttpStatus.OK);
         
         //cree esto como plan, ponelo en el Postman y ejecutalo
         /*{
   "descripcion":"basico",
   "activo":1,
   "precio":200.5
}*/
     }
     //CHEQUEAR
     @PreAuthorize("hasAuthority('administrativo') ")
     @PostMapping("/saveAlumno")
     ResponseEntity<?> prueba7(@Valid @RequestBody AlumnoDTO a){
         uService.crearAlumno(a);
          return new ResponseEntity<>("el alumno fue creado correctamente", HttpStatus.OK);
         /*PONER ESTO EN POSTMAN:
          {
   "nombre": "alumno",
   "apellido": "agua",
   "dni":14852966,
   "eMail":"alumnoprueba@gmail.com",
   "telefono":43239965,
   "activo":1,
   "idCurso":3,
   "idPadre":6
}*/
     }
     //chequear
     @PreAuthorize("hasAuthority('super admin') "
            + "or hasAuthority('jefe colegio') "
            + "or hasAuthority('administrativo') "
            + "or hasAuthority('preceptor')" 
            + "or hasAuthority('padre')"
            + "or hasAuthority('profesor')" 
            + "or hasAuthority('alumno')" )
      @GetMapping(value="/getNombreCompleto/{idUsuario}")
     ResponseEntity<?> prueba8(@PathVariable(value = "idUsuario") int idUsuario){
         //uService.nombreYApellido(id);
         Optional<Usuario> usuarioObtenido = uService.nombreYApellido(idUsuario);

        if (usuarioObtenido.isPresent()) {
            Usuario usuario = usuarioObtenido.get();
            NombreCompletoDTO usuarioDTO = new NombreCompletoDTO(usuario.getNombre(), usuario.getApellido());

            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
         //return new ResponseEntity<>( uService.nombreYApellido(idUsuario), HttpStatus.OK);
         
     }
     
     @PreAuthorize("hasAuthority('jefe colegio') ")//chequear
     @PostMapping("/altaAdministrativo")
      ResponseEntity<?> prueba9(@Valid @RequestBody AdministrativoDTO a){
         uService.crearAdministrativo(a);
          return new ResponseEntity<>("el administrativo fue creado correctamente", HttpStatus.OK);
          /*PONER ESTO EN POSTMAN:
          {
   "nombre": "administrativo",
   "apellido": "agua",
   "dni":12358800,
   "eMail":"adminsprueba@gmail.com",
   "clave":"prueba",
   "telefono":43239965,
   "activo":1,
   "idEscuela":4
}*/
     }
     
  }
    

