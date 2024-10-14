package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.PlanDTO;
import com.nexo.nexoeducativo.models.dto.request.RolDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.service.CursoEscuelaService;
import com.nexo.nexoeducativo.service.CursoService;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.PlanService;
import com.nexo.nexoeducativo.service.RolService;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/usuario")
//EN TODOS LOS METODOS, SI HAY UN ERROR EN EL CASO DE QUE EXISTAN, 
//NO SALTA EL MENSAJE EN POSTMAN SINO EN CONSOLA DE JAVA, ARREGLAR ESO
public class LoginController {
    //
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
    
    @GetMapping("/getUsuario")
    public ResponseEntity<?> prueba(){
        return new ResponseEntity<>("prueba", HttpStatus.OK);
    }
    
    @PostMapping("/saveUsuario")
    public ResponseEntity<?> prueba2(@Valid @RequestBody UsuarioDTO u ){
        uService.crearUsuario(u);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
        return new ResponseEntity<>("se guardo exitosamente el usuario creado", HttpStatus.OK);
        
        //cree esto como jefeColegio, ponerlo en el postman luego ejecutarlo
        /*{
   "nombre": "Juan",
   "apellido":"Perez",
   "dni":12345678,
   "eMail":"mail@gmail.com",
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
     
      @PostMapping("/saveRol")
     public ResponseEntity<?> prueba4(@Valid @RequestBody RolDTO r){
         
          rolService.crearRol(r);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
          return new ResponseEntity<>("prueba rol", HttpStatus.OK);
     }
     
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
}
