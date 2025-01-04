
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.models.dto.request.AdministrativoDTO;
import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoEscuela;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.exception.UsuarioExistingException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioWithPadreException;
import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioSegunRolDTO;
import com.nexo.nexoeducativo.models.dto.request.JefeColegioModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioUsuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.EscuelaUsuarioRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioUsuarioRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

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
     @Autowired
    private EscuelaRepository escuelaRepository;
    
    @Autowired
    private EscuelaUsuarioRepository escuelaUsuarioRepository;
 
    @Autowired
    private Validator validator;
   //para saber info del usuario logueado
    private Usuario usuario;
    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    
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
    
    
     public void crearUsuario(UsuarioDTO uDTO) throws Exception{
         
         Rol r=new Rol();
         r.setIdRol(uDTO.getRol());//aca se inserta el id del rol segun la bbdd
        
        Usuario u = new Usuario();
        u.setNombre(uDTO.getNombre());
        u.setApellido(uDTO.getApellido());
        u.setMail(uDTO.getMail());
        u.setClave(convertirSHA256(uDTO.getClave())); 
        u.setDni(uDTO.getDni());
        u.setTelefono(uDTO.getTelefono());
        u.setActivo(uDTO.getActivo());
        //probar esto mas tarde:
        //u.setActivo((uDTO.getActivo())? (short)1 : (short)0); 
        // Asegurarse de guardar el estado "activo"
        u.setRolidrol(r);
        
        if(!usuariorepository.existsByDni(u.getDni())&&
        !usuariorepository.existsByMail(u.getMail())){
             this.usuariorepository.save(u);//solo lo guarda si el dni y mail ingresado NO existen
        }else{
            throw new UsuarioExistingException("dni o mail ya registrado previamente");
        }
    }
     
     public void crearAlumno(AlumnoDTO a){
       Rol rolAlumno = rolRepository.findById(7)
        .orElseThrow(() -> new RolNotFound("El rol de Alumno no existe"));

        Curso curso = cursoRepository.findById(a.getIdCurso())
            .orElseThrow(() -> new CursoNotFound("El curso no existe"));

        if (usuariorepository.existsByDni(a.getDni())&&
        !usuariorepository.existsByMail(a.getMail())) {
            throw new UsuarioExistingException("El alumno ya existe");
        }
        Usuario alumno = new Usuario();
        alumno.setNombre(a.getNombre());
        alumno.setApellido(a.getApellido());
        alumno.setClave(a.getClave());
        alumno.setMail(a.getMail());
        alumno.setDni(a.getDni());
        alumno.setTelefono(a.getTelefono());
        alumno.setActivo(a.getActivo());
        alumno.setRolidrol(rolAlumno);
        alumno = this.usuariorepository.save(alumno);

        if (cursoUsuarioRepository.existsByCursoIdCursoAndUsuarioIdUsuario(curso, alumno)) {
            throw new UsuarioAssignedException("El alumno ya está asignado a ese curso");
        }
        Usuario padre = usuariorepository.findById(a.getIdPadre())
            .orElseThrow(() -> new UsuarioExistingException("El padre no existe"));

        Rol rolPadre = rolRepository.findById(6)
            .orElseThrow(() -> new RolNotFound("El rol de Padre no existe"));

        if (!verificarPermisos(rolPadre, 6)) {
            throw new UsuarioNotAuthorizedException("El usuario no posee permisos como padre: ");
        }

        padre.setRolidrol(rolPadre);
        padre = usuariorepository.save(padre);

        if (usuarioUsuarioRepo.existsByUsuarioIdUsuarioAndUsuarioIdUsuario1(alumno, padre)) {
            throw new UsuarioWithPadreException("Ese alumno ya tiene asociado a ese padre");
        }

        CursoUsuario cursoUsuario = new CursoUsuario();
        cursoUsuario.setCursoIdCurso(curso);
        cursoUsuario.setUsuarioIdUsuario(alumno);
        this.cursoUsuarioRepository.save(cursoUsuario);

        UsuarioUsuario usuarioUsuario = new UsuarioUsuario();
        usuarioUsuario.setUsuarioIdUsuario(alumno);
        usuarioUsuario.setUsuarioIdUsuario1(padre);
        this.usuarioUsuarioRepo.save(usuarioUsuario);
    }

    private boolean verificarPermisos(Rol rolVerificar, int nro) {
         return rolVerificar != null && rolVerificar.getIdRol() == nro;
    }
    
       public  List<NombreCompletoDTO> nombreYApellido(int idUsuario){
       /* Usuario u=new Usuario();
        u.setIdUsuario(idUsuario);//coloco el numero de id ingresado por parametro*/
        if(usuariorepository.getFullName(idUsuario).isEmpty()){//en caso de que se haya ingresado un id invalido
             throw new UsuarioExistingException("No existe ese usuario");
        }else{//sino mostrar el nombre y apellido
            
            return usuariorepository.getFullName(idUsuario);
           //return usuariorepository.findById(idUsuario);
        }
    }

    public void crearAdministrativo(AdministrativoDTO a) {
       Rol r=new Rol();
         r.setIdRol(3);//id segun lo asignado en bbdd
         
            Usuario u = new Usuario();
            u.setNombre(a.getNombre());
            u.setApellido(a.getApellido());
            u.setMail(a.getMail());
            u.setClave(convertirSHA256(a.getClave())); 
            u.setDni(a.getDni());
            u.setTelefono(a.getTelefono());
            u.setActivo(a.getActivo());
            u.setRolidrol(r);
            
            Escuela e=escuelaRepository.findById(a.getIdEscuela())
            .orElseThrow(() -> new EscuelaNotFoundException("La escuela no existe"));
            
            EscuelaUsuario eu=new EscuelaUsuario();
            eu.setUsuarioIdUsuario(u);
            eu.setEscuelaIdEscuela(e);
            
            this.usuariorepository.save(u);
            this.escuelaUsuarioRepository.save(eu);
    }

     public List<NombreCompletoDTO> jefeColegioSinAsignar(){
       return usuariorepository.getJefeColegioWithoutSchool();
        }
     
     public void eliminarUsuario(int idUsuario){
         usuariorepository.deleteById(idUsuario);
     }
     
     public List<InfoUsuarioSegunRolDTO> obtenerUsuarioSegunRol(String nombre){
         //List<Usuario> usuarios = usuariorepository.getUsuarioByRol(nombre);
         /*List<InfoUsuarioSegunRolDTO> lista = new ArrayList<>();
         for (Usuario u : usuarios) {
              InfoUsuarioSegunRolDTO iu = new InfoUsuarioSegunRolDTO
        (u.getIdUsuario(),u.getNombre(), u.getApellido(), u.getDni());
             
         }*///chequeo
         return usuariorepository.getUsuarioByRol(nombre);
     }
     
     public void validarElDto (JefeColegioModificacionDTO j){
    Set<ConstraintViolation<JefeColegioModificacionDTO>> violaciones = validator.validate(j);
    if (!violaciones.isEmpty()) {
        throw new ConstraintViolationException(violaciones);
    }
     //LOGGER.info("EL DTO A VALIDAR TIENE LAS SIGUIENTES PROPIEDADES: "+j.toString());
     }
     
     public void actualizarCampos( JefeColegioModificacionDTO dto, Usuario u){
         if (dto.getNombre() != null) {
             u.setNombre(dto.getNombre());
         }
         if (dto.getApellido() != null) {
             u.setApellido(dto.getApellido());
         }
         
         
         if (dto.getDni() != null) {
             int casteo=Integer.parseInt(dto.getDni());
             if(!usuariorepository.existsByDni(casteo)){
                 u.setDni(casteo); //PARA EVITAR QUE EL DNI ACTUALIZADO COINCIDA CON UNO PREVIAMENTE EXISTENTE
             }else{
                  throw new UsuarioExistingException("El dni ingresado ya esta asociado a otro usuario");
                 
             }
         }
                                        //PARA EVITAR QUE EL MAIL ACTUALIZADO COINCIDA CON UNO PREVIAMENTE EXISTENTE
        if (dto.getMail() != null) { // Solo intentamos actualizar si hay un mail en el DTO
    if (!usuariorepository.existsByMail(dto.getMail())) {
        u.setMail(dto.getMail());
    } else {
        throw new UsuarioExistingException("El mail ingresado ya está asociado a otro usuario: " + dto.getMail());
    }
}

         if (dto.getClave() != null) {
             u.setClave(convertirSHA256(dto.getClave()));
         }
         if (dto.getTelefono() != null) {
             u.setTelefono(dto.getTelefono());
         }
         if (dto.getActivo() != 0) {
             u.setActivo(dto.getActivo());
         }
         
         //LOGGER.info("el nuevo objeto contiene: "+u.toString());
         
         
     }
     
     @Transactional
    public JefeColegioModificacionDTO actualizarJefeColegio(int id, JefeColegioModificacionDTO j) {
    Usuario usuarioIngresado = usuariorepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("El usuario que se desea modificar no existe"));

        validarElDto(j);
        actualizarCampos(j,usuarioIngresado);
    Usuario actualizado= usuariorepository.save(usuarioIngresado);
     //LOGGER.info("EL OBJETO ACTUALIZADO QUE SE VA A GUARDAR: "+actualizado.toString());
     return new JefeColegioModificacionDTO (actualizado);
    }

    
    @Transactional
     public JefeColegioModificacionDTO getUsuarioPorID (int id){
         Optional<Usuario> usuarioIngresado = usuariorepository.findById(id);
         
         return new JefeColegioModificacionDTO (usuarioIngresado.get());
     }
     
     public Usuario buscarUsuario(String mail){
         return usuariorepository.getUsuarioByMail(mail);
     }
     
     public void tomarAsistencia(int idCurso){
         //primero, el preceptor selecciona a que curso de los que el tiene, le quiere tomar la asistencia
         //luego, le aparece la lista de alumnos de ese curso
         //colocar 1 en la bbdd SI asistio y 0 si NO asistio
         //fecha: toma automaticamente la del sistema
         //solo se puede 1 asistencia por curso y por dia
         //solo se puede modificar la asistencia hasta el mismo dia a las 23:59
         
         //SE UTILIZA LAS TABLAS ASISTENCIA, USUARIO_ASISTENCIA
         //VOLCAR CANT DE ASISTENCIAS E INASISTENCIAS EN PRESENTISMO
         //ASOCIAR LO ANTERIOR CON LA TABLA DE PRESENTISMO_USUARIO 
     }
     
    public List<NombreCompletoDTO> alumnosDelCurso(Curso curso) {
        List<NombreCompletoDTO> alumnosDelCurso = usuariorepository.tomarLista(curso);
        return alumnosDelCurso;
    }
     
}
     
     
   
     
     
        
     
