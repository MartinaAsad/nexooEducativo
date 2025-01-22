
package com.nexo.nexoeducativo.controller;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.AdministrativoDTO;
import com.nexo.nexoeducativo.models.dto.request.AsignarPreceptorDTO;
import com.nexo.nexoeducativo.models.dto.request.AsistenciaDTO;
import com.nexo.nexoeducativo.models.dto.request.BorrarMateriaRequestDTO;
import com.nexo.nexoeducativo.models.dto.request.CursoDTO;
import com.nexo.nexoeducativo.models.dto.request.DesplegableMateriaView;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaView;
import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioSegunRolDTO;
import com.nexo.nexoeducativo.models.dto.request.JefeColegioModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.MateriaDTO;
import com.nexo.nexoeducativo.models.dto.request.MaterialDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreDireccionEscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.PlanDTO;
import com.nexo.nexoeducativo.models.dto.request.RolDTO;
import com.nexo.nexoeducativo.models.dto.request.SeleccionarMaterialView;
import com.nexo.nexoeducativo.models.dto.request.TareaDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Materia;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Tarea;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.service.AsistenciaService;
import com.nexo.nexoeducativo.service.CursoService;
import com.nexo.nexoeducativo.service.CursoUsuarioService;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.MateriaService;
import com.nexo.nexoeducativo.service.MaterialService;
import com.nexo.nexoeducativo.service.PlanService;
import com.nexo.nexoeducativo.service.RolService;
import com.nexo.nexoeducativo.service.TareaService;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/usuario") //reemplazarlo por api
@CrossOrigin(origins="http://localhost:3000")
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
    private MateriaService materiaService;
    
    @Autowired
    private CursoUsuarioService cursoUsuarioService;
    
    @Autowired
    private AsistenciaService asistenciaS;
    
    @Autowired
    private TareaService tareaService;
    
    @Autowired
    private MaterialService materialService;
     private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
    
    @PreAuthorize(" hasAuthority('super admin') ")
    @PostMapping("/saveUsuarioJefeColegio")
    public ResponseEntity<?> prueba2(@Valid @RequestBody UsuarioDTO u ) throws Exception{
        uService.crearUsuarioJefeColegio(u);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
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
        
        {
    "nombre": "Nerea",
    "apellido": "Soto",
    "dni": 365896,
    "eMail": "nereaa.soto@gmail.com",
    "clave": "123456",
    "telefono": 11472033,
    "activo": 1,
    "rol": 4
}
        */
    }
    @PreAuthorize("hasAuthority('super admin') ")
    @PostMapping("/saveEscuela")
     public ResponseEntity<?> prueba3(@Valid @RequestBody EscuelaDTO e ){
        
        escuelaService.crearEscuela(e);
        
        //poner esto en el Postman
        /*{
   "nombre":"escuela 1",
   "direccion":"direccion 1",
   "activo":1,
   "idPlan":1,
   "jefeColegio":3
}*/
        return new ResponseEntity<>("Escuela guardada exitosamente", HttpStatus.OK);
    }
     
     
     @PreAuthorize("hasAuthority('super admin') ")
      @PostMapping("/saveRol")
     public ResponseEntity<?> prueba4(@Valid @RequestBody RolDTO r){
         
          rolService.crearRol(r);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
          return new ResponseEntity<>("Rol guardado exitosamente", HttpStatus.OK);
     }
     @PreAuthorize("hasAuthority('administrativo') "
            + "or hasAuthority('preceptor') "+
             "or hasAuthority('super admin') ") //despues sacar este permiso mas adelante
     @PostMapping("/saveCurso")
     public ResponseEntity<?> prueba5(@Valid @RequestBody CursoDTO r, Authentication auth){
         String mailUsuario=auth.getPrincipal().toString();
         Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
         cursoService.crearCurso(r, e);//buscar la manera de que en caso que no se haya creado, mostrar en el Postman un mensaje de error
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
     
     /*metodo saveAlumno*/
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
     
      @PreAuthorize("hasAuthority('administrativo') ")
     @GetMapping("/obtenerPadres")
     ResponseEntity<?> obtenerPadres(Authentication auth){
         Rol r=new Rol();
         r.setIdRol(6);
         String mailUsuario=auth.getPrincipal().toString();
         Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
         List<NombreCompletoDTO> padres=uService.infoUsuarioSegunEscuela(r, e);
         /*Usuario u=
         escuelaService.obtenerIdEscuelaUsuario(usuarioIdUsuario);
         Escuela e=new Escuela();
         uService.infoUsuarioSegunEscuela(r, e);*/
          return new ResponseEntity<>(padres, HttpStatus.OK);
         
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
         List<NombreCompletoDTO> nombreCompleto = new ArrayList<NombreCompletoDTO>();
	uService.nombreYApellido(idUsuario).forEach(nombreCompleto::add);
		
		if(nombreCompleto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(nombreCompleto, HttpStatus.OK);
         
         
     }
     
     @PreAuthorize("hasAuthority('jefe colegio') "
      + "or hasAuthority('administrativo') ")
     @PostMapping("/altaUsuario")
      ResponseEntity<?> prueba9(@Valid @RequestBody AdministrativoDTO a, Authentication auth){
          Rol r=new Rol();
          r.setIdRol(a.getRol());
          String mailUsuario=auth.getPrincipal().toString();
          Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
          //setear la escuela del usuario logueado
          a.setIdEscuela(e.getIdEscuela()); //VER QUE ERROR HAY ACA
         uService.crearUsuario(a, r.getIdRol());
          return new ResponseEntity<>("el administrativo fue creado correctamente", HttpStatus.OK);
          /*PONER ESTO EN POSTMAN:
          {
   "nombre": "administrativo",
   "apellido": "agua",
   "dni":12358800,
   "mail":"adminsprueba@gmail.com",
   "clave":"prueba",
   "telefono":43239965,
   "activo":1,
    "rol":2,
   "idEscuela":4
}*/
     }
      @PreAuthorize("hasAuthority('super admin') ")
      @GetMapping(value="/getJefeColegioSinEscuela")
     ResponseEntity<?> prueba11(){
         List<NombreCompletoDTO> nombreCompleto = new ArrayList<NombreCompletoDTO>();
		
		/*if(nombreCompleto==null)
			usuarioRepository.forEach(materias::add);
		else*/
			uService.jefeColegioSinAsignar().forEach(nombreCompleto::add);
		
		if(nombreCompleto.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(nombreCompleto, HttpStatus.OK);   
     }
     
      @PreAuthorize("hasAuthority('super admin') ")
    @GetMapping(value = "/getNombreRoles")
    ResponseEntity<?> prueba12() {
        List<String> roles = new ArrayList<String>();
        rolService.obtenerNombreRoles().forEach(roles::add);

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);

    }
    @PreAuthorize("hasAuthority('super admin') ")
     @GetMapping(value = "/getNombrePlanes")
    ResponseEntity<?> prueba13() {
        List<String> planes = new ArrayList<String>();
        planService.obtenerNombrePlanes().forEach(planes::add);

        if (planes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(planes, HttpStatus.OK);

    }
    
     @PreAuthorize("hasAuthority('administrativo') ")
     //@PreAuthorize("hasAuthority('super admin') ")
    @PostMapping(value="/saveMateria")
    ResponseEntity<?> prueba14(@Valid @RequestBody MateriaDTO m){
        materiaService.crearMateria(m);
         return new ResponseEntity<>("la materia fue creada correctamente", HttpStatus.CREATED);
    }
    
      @PreAuthorize("hasAuthority('administrativo') ")
     //@PreAuthorize("hasAuthority('super admin') ")
    @GetMapping(value="/verMaterias")
    ResponseEntity<?> prueba14(Authentication auth){
        String mailUsuario=auth.getPrincipal().toString();
         Escuela escuelaIdEscuela=escuelaService.obtenerIdEscuela(mailUsuario);
       List<String> materias= materiaService.verMateriasEscuela(escuelaIdEscuela);
         return new ResponseEntity<>(materias, HttpStatus.OK);
    }
    
    
    
    
    /*endpoint a partir de aca y el de abajo para metodo bajaMateria*/
    @PreAuthorize("hasAuthority('administrativo')")
    @GetMapping(value="/verCursoAdministrativo")
    ResponseEntity<?> prueba150(Authentication auth) throws NoSuchFieldException{
       String mail=auth.getPrincipal().toString();//obtengo el mail del usuario logueado
        //Usuario obtenerUsuario=uService.buscarUsuario(mail);
        List<verCursoView> listaCursos=cursoService.verCursosAdministrativo(mail);
         return new ResponseEntity<>(listaCursos, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('administrativo')")
    @GetMapping(value="/verProfesAdministrativo")
    ResponseEntity<?> verProfesAdm(Authentication auth) throws NoSuchFieldException{
        Rol r=new Rol();
         r.setIdRol(5);
         String mailUsuario=auth.getPrincipal().toString();
         Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
         List<NombreCompletoDTO> profes=uService.infoUsuarioSegunEscuela(r, e);
         /*Usuario u=
         escuelaService.obtenerIdEscuelaUsuario(usuarioIdUsuario);
         Escuela e=new Escuela();
         uService.infoUsuarioSegunEscuela(r, e);*/
          return new ResponseEntity<>(profes, HttpStatus.OK);
    }
    
        
    @PreAuthorize("hasAuthority('administrativo')")
    @GetMapping(value="/verMaterias/{cursoIdCurso}")
    ResponseEntity<?> pruebita2(@PathVariable("cursoIdCurso") int cursoIdCurso){
        if(cursoIdCurso==0){
           throw new CursoNotFound("El curso ingresado no existe");
        }
        Curso c=new Curso();
        c.setIdCurso(cursoIdCurso);
        List<DesplegableMateriaView> materias=materiaService.verMaterias(c);
         return new ResponseEntity<>(materias, HttpStatus.OK);
    }
    
    /*endpoints necesarios para asistencia*/
     @PreAuthorize("hasAuthority('preceptor')"
             + "or hasAuthority('profesor') ")
    @GetMapping(value="/verCursoPreceptor") //este endpoint tambien es necesario para altaNota
    ResponseEntity<?> prueba151(Authentication auth) throws NoSuchFieldException{
       String mail=auth.getPrincipal().toString();//obtengo el mail del usuario logueado
        //Usuario obtenerUsuario=uService.buscarUsuario(mail);
        List<verCursoView> listaCursos=cursoService.verCursosPreceptor(mail);
         return new ResponseEntity<>(listaCursos, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('preceptor')"
    + "or hasAuthority('administrativo') ")
    @GetMapping(value="/verAlumnosCurso/{cursoIdCurso}") //este endpoint tambien es necesario para altaNota
    ResponseEntity<?> prueba153(@PathVariable("cursoIdCurso") int cursoIdCurso){
        Curso curso=new Curso();
        curso.setIdCurso(cursoIdCurso);
       List<NombreCompletoDTO> alumnosDelCurso=uService.alumnosDelCurso(curso);
         return new ResponseEntity<>(alumnosDelCurso, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('preceptor')")
    @PostMapping(value="/tomarAsistencia/{cursoIdCurso}")
    ResponseEntity<?> prueba152(@Valid @RequestBody AsistenciaDTO asistencia, @PathVariable("cursoIdCurso") Integer cursoIdCurso){
       asistenciaS.altaAsistencia(asistencia, cursoIdCurso);
       //asistenciaS.guardarPresentismo(asistencia.getAlumnosCurso());
         return new ResponseEntity<>("Asistencia creada correctamente", HttpStatus.CREATED);
         /*{
    "alumnosCurso": [
    {
        "idUsuario":7,
        "asistio":1,
        "mediaFalta":0,
        "retiroAntes":0
    },
     {
        "idUsuario":27,
        "asistio":1,
        "mediaFalta":0,
        "retiroAntes":0
    }
    ]
}*/
    }
    
    /*endpoint para metodo altaTarea*/
      @PreAuthorize("hasAuthority('profesor') ")
    @GetMapping(value="/verCursoProfesor")
    ResponseEntity<?> cursoProfesor(Authentication auth) throws NoSuchFieldException{
        //buscar el id del usuario ingresado
        String mail=auth.getPrincipal().toString();
        Usuario usuario=uService.buscarUsuario(mail);
         List<MateriaCurso> obtenerCursos=uService.obtenerCursos(usuario);
         List<verCursoView> verCursos=uService.verCursos(obtenerCursos);
        return new ResponseEntity<>(verCursos, HttpStatus.OK);
    }
    
    /*endpoint para altaMaterial*/
      @PreAuthorize("hasAuthority('profesor') ")
    @PostMapping(value="/altaMaterial", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> altaMaterial(@RequestPart ("urlArchivo") MultipartFile urlArchivo,@RequestPart("material")@Valid MaterialDTO material, Authentication auth) throws IOException {
        String mail=auth.getPrincipal().toString();
        Usuario usuario=uService.buscarUsuario(mail); 
        try {
             materialService.altaMaterial(urlArchivo,material, usuario);
             return ResponseEntity.ok()
                .body( "Material publicado exitosamente"  
                );

         } catch (IOException ex) {
             java.util.logging.Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new HashMap<String, String>() {{
                    put("error", "Error processing file upload: " + ex.getMessage());
                }});

         }
        //return new ResponseEntity<>("Material publicado exitosamente", HttpStatus.OK);
    }
    
    /*endpoints para bajaMaterial*/
    @PreAuthorize("hasAuthority('profesor') ")
    @GetMapping(value="/selecMaterialProfesor")
      ResponseEntity<?> selectMaterial(@RequestParam Integer cursoIdCurso, @RequestParam Integer materiaIdMateria){
         List<SeleccionarMaterialView>materiales=materialService.seleccionarMaterial(cursoIdCurso, materiaIdMateria);
         return new ResponseEntity<>(materiales, HttpStatus.OK);
         /*request que puse en Postman para que funcione RECREARLA EN EL FRONT, los numeros son de ejemplo: 
         http://localhost:8080/api/usuario/selecMaterialProfesor?cursoIdCurso=3&materiaIdMateria=1*/
     }
    
     @PreAuthorize("hasAuthority('profesor') ")
     @DeleteMapping(value="borrarMaterial")
     ResponseEntity<?> bajaMaterial(@RequestParam Integer cursoIdCurso, @RequestParam Integer materiaIdMateria,@RequestParam Integer materialIdMaterial){
         materialService.borrarMaterial(materialIdMaterial, materiaIdMateria);
         return new ResponseEntity<>("", HttpStatus.OK);
     }
     
    
        
      @PreAuthorize("hasAuthority('profesor') ")
    @GetMapping(value="/selecMateriaProfesor/{cursoIdCurso}")
    ResponseEntity<?> selecMateriaProfesor(@PathVariable("cursoIdCurso") Integer cursoIdCurso, Authentication auth) throws NoSuchFieldException{
        //obtener el curso
        Curso c=new Curso();
        c.setIdCurso(cursoIdCurso);
        //buscar el id del usuario ingresado
        String mail=auth.getPrincipal().toString();
        Usuario usuario=uService.buscarUsuario(mail);
         List<DesplegableMateriaView> materias=materiaService.mostrarMateriasProfe(c, usuario);
        return new ResponseEntity<>(materias, HttpStatus.OK);
    }
    
     @PreAuthorize("hasAuthority('profesor') ")
    @PostMapping(value="/altaTarea/{cursoIdCurso}")
    ResponseEntity<?> altaTarea(@PathVariable("cursoIdCurso") Integer cursoIdCurso,@Valid @RequestBody TareaDTO tarea) throws NoSuchFieldException{
        Tarea t=tareaService.altaTarea(tarea, cursoIdCurso);
        
        return new ResponseEntity<>(t, HttpStatus.CREATED);
    }
    
    /*endpoints necesarios para altaCalificacion*/
     @PreAuthorize("hasAuthority('profesor') ")
    @GetMapping(value="/obtenerTareas/{cursoIdCurso}")
    ResponseEntity<?> obtenerTareas(@PathVariable("cursoIdCurso") Integer cursoIdCurso, Authentication auth) throws NoSuchFieldException{
        //obtener el curso
        Curso c=new Curso();
        c.setIdCurso(cursoIdCurso);
        //buscar el id del usuario ingresado
        String mail=auth.getPrincipal().toString();
        Usuario usuario=uService.buscarUsuario(mail);
         List<DesplegableMateriaView> materias=materiaService.mostrarMateriasProfe(c, usuario);
        return new ResponseEntity<>(materias, HttpStatus.OK);
    }
    
    
    
    
    /*otros endpoints*/
    
    @PreAuthorize("hasAuthority('administrativo') ")
    @DeleteMapping(value="/borrarMateria")
    ResponseEntity<?> prueba15(@Valid @RequestBody BorrarMateriaRequestDTO borrar){
        int resultado=materiaService.borrarMateria(borrar.getIdCurso(), borrar.getIdMateria());
        if(resultado!=0){
              return new ResponseEntity<>("la materia fue borrada correctamente", HttpStatus.OK);
        }else{
            
        }return new ResponseEntity<>("la materia no fue borrada correctamente", HttpStatus.BAD_REQUEST);
        
       
    }
    
    
    /*NEDPOINTS NECESARIOS PARA EL FRONT METODO SELECCIONAR CURSO*/
    @PreAuthorize("hasAuthority('jefe colegio')")
    @GetMapping(value="/verCursos")
    ResponseEntity<?> pruebita1(Authentication auth) throws NoSuchFieldException{
        String mail=auth.getPrincipal().toString();//obtengo el mail del usuario logueado
        List<verCursoView> cursos=cursoService.verCursos(mail);
         return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
    
      //@PreAuthorize("hasAuthority('super admin')")
    @PreAuthorize("hasAuthority('jefe colegio')")
    @GetMapping(value="/selectCurso/{idCurso}")
    ResponseEntity<?> pruebita(@PathVariable("idCurso") int idCurso){
        List<Curso> c= new ArrayList<Curso>();
        return new ResponseEntity<>(cursoService.seleccionarCurso(idCurso), HttpStatus.OK);
    }
    
    /*otros metodos*/
    
     @PreAuthorize("hasAuthority('super admin') ")
     @DeleteMapping("borrarEscuela/{idEscuela}")
    ResponseEntity<?> prueba14(@PathVariable("idEscuela") int idEscuela){
        escuelaService.borrarEscuela(idEscuela);
         return new ResponseEntity<>("escuela borrada exitosamente", HttpStatus.OK);
    }
    
      @PreAuthorize("hasAuthority('super admin') "
            + "or hasAuthority('jefe colegio') "
            + "or hasAuthority('administrativo') ")
    @DeleteMapping("borrarUsuario/{idUsuario}")
    ResponseEntity<?> prueba15(@PathVariable(value = "idUsuario") int idUsuario){
        uService.eliminarUsuario(idUsuario);
        return new ResponseEntity<>("usuario borrado exitosamente", HttpStatus.OK);
    }
    
    /*endpoint necesario para bajaAlumno.js*/
     @PreAuthorize("hasAuthority('preceptor')"
    + "or hasAuthority('administrativo') ")
    @GetMapping(value="/verAlumnos")
    ResponseEntity<?> verAlumnos(Authentication auth){
        String mailUsuario=auth.getPrincipal().toString();
        Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
         Escuela escuelaIdEscuela=escuelaService.obtenerIdEscuela(mailUsuario);  
       List<NombreCompletoDTO> alumnosDelCurso=uService.obtenerAlumnos(escuelaIdEscuela);
         return new ResponseEntity<>(alumnosDelCurso, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('administrativo')")
    @PostMapping(value="/crearMateria")
    ResponseEntity<?> prueba16(@Valid @RequestBody MateriaDTO m){
        materiaService.crearMateria(m);
        return new ResponseEntity<>("se creo una materia", HttpStatus.OK);
        /*PONER ESTO EN EL POSTMAN:
        */
    }
    
         @PreAuthorize("hasAuthority('super admin') "
            + "or hasAuthority('jefe colegio') "
            + "or hasAuthority('administrativo') "
            + "or hasAuthority('preceptor')" 
            + "or hasAuthority('padre')"
            + "or hasAuthority('profesor')" 
            + "or hasAuthority('alumno')" )
    @GetMapping("/getRolUsuarioLogueado")
    public ResponseEntity<?> prueba17(Authentication authentication) {
        String rol = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)//recorre
                .findFirst()
                .orElse("error");
       
        return new ResponseEntity<>("el rol del usuario logueado es: "+rol, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('super admin') ")
     @GetMapping(value="/getEscuelas")
    ResponseEntity<?> prueba18(){
        List<NombreDireccionEscuelaDTO> escuelas = new ArrayList<NombreDireccionEscuelaDTO>();
        escuelaService.obtenerEscuelas().forEach(escuelas::add);
         if (escuelas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }
    
     @PreAuthorize("hasAuthority('super admin') ")
     @GetMapping(value="/getEscuela/{id}")
    ResponseEntity<?> prueba23(@PathVariable("id") @Valid Integer id){
        EscuelaView escuela = null;
         for (NombreDireccionEscuelaDTO esc : escuelaService.obtenerEscuelas()) {
             if (esc.getId_escuela() == id) {
                 escuela=new EscuelaView(esc.getNombre(), esc.getDireccion());
             }
         }
         if (escuela == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(escuela, HttpStatus.OK);
    }
    
    
    
    
    @PreAuthorize("hasAuthority('super admin') "
            + "or hasAuthority('jefe colegio') "
    + "or hasAuthority('administrativo') ")
    @GetMapping(value="/getUsuarios/{nombre}")
    ResponseEntity<?> prueba19(@PathVariable(value = "nombre") String nombre, Authentication auth){
        String mailUsuario=auth.getPrincipal().toString();
        Escuela e=escuelaService.obtenerIdEscuela(mailUsuario);
         Escuela escuelaIdEscuela=escuelaService.obtenerIdEscuela(mailUsuario);  
        List<InfoUsuarioSegunRolDTO> usuarios = new ArrayList<InfoUsuarioSegunRolDTO>();
	uService.obtenerUsuarioSegunRol(nombre, escuelaIdEscuela).forEach(usuarios::add);
		
		if(usuarios.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(usuarios, HttpStatus.OK);   
    }
    
    @PreAuthorize("hasAuthority('super admin')"
            + "or hasAuthority('administrativo') ")
    @GetMapping(value="/getUsuario/{id}")
    ResponseEntity<?> prueba20 (@PathVariable(value = "id") int id){
     JefeColegioModificacionDTO s=uService.getUsuarioPorID(id);
        
        return new ResponseEntity<>(s,HttpStatus.OK);   
    }
       
    
    @PreAuthorize("hasAuthority('super admin') "
            + "or hasAuthority('jefe colegio') "
            + "or hasAuthority('administrativo') "
            + "or hasAuthority('preceptor')" 
            + "or hasAuthority('padre')"
            + "or hasAuthority('profesor')" 
            + "or hasAuthority('alumno')" )
    @PatchMapping(value="/modificarUsuario/{id}")
    ResponseEntity<?> prueba21 (@PathVariable(value = "id") int id,  @Valid @RequestBody JefeColegioModificacionDTO jc){
     JefeColegioModificacionDTO s=uService.actualizarJefeColegio(id, jc);
        
        return new ResponseEntity<>(s,HttpStatus.OK);   
    }
    
    @PreAuthorize("hasAuthority('super admin')")
    @PatchMapping(value="/modificarEscuela/{id}")
     ResponseEntity<?> prueba22 (@PathVariable(value = "id") int id,  @Valid @RequestBody EscuelaModificacionDTO em){
          EscuelaModificacionDTO s=escuelaService.actualizarEscuela(id, em);
          //ver problema aca
           return new ResponseEntity<>(s,HttpStatus.OK);   
    }
    
    @PreAuthorize("hasAuthority('administrativo')") //asignado como ejemplo, despues cambiar a administrativo
    @PostMapping(value="/asignarPreceptor")
     ResponseEntity<?> prueba23 ( @Valid @RequestBody AsignarPreceptorDTO em){
          cursoUsuarioService.asignarPreceptor(em);
          
           return new ResponseEntity<>(HttpStatus.OK);   
    }   
    
    
    
}

     
  
    

