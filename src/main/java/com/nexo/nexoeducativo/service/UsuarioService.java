
package com.nexo.nexoeducativo.service;


import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.FormatoIncorrectoException;
import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioAssignedException;
import com.nexo.nexoeducativo.exception.UsuarioExistingException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioWithPadreException;
import com.nexo.nexoeducativo.models.dto.request.AdministrativoDTO;
import com.nexo.nexoeducativo.models.dto.request.AlumnoDTO;
import com.nexo.nexoeducativo.models.dto.request.AlumnoModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.DesplegableChatGrupalView;
import com.nexo.nexoeducativo.models.dto.request.DesplegableChatView;
import com.nexo.nexoeducativo.models.dto.request.InfoUsuarioSegunRolDTO;
import com.nexo.nexoeducativo.models.dto.request.JefeColegioModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioView;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.CursoUsuario;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.MateriaCurso;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioUsuario;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.CursoUsuarioRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.EscuelaUsuarioRepository;
import com.nexo.nexoeducativo.repository.MateriaCursoRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.repository.UsuarioUsuarioRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private MateriaCursoRepository matCursoRepository;
    
 
    @Autowired
    private Validator validator;
   //para saber info del usuario logueado
    private Usuario usuario;
    
    @Autowired 
    private UsuarioUsuarioRepository uuRepository;
    
    
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
    
    
     public void crearUsuarioJefeColegio(UsuarioDTO uDTO) throws Exception{
         
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
     
     public void crearAlumno(AlumnoDTO a, Escuela e){
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
        alumno.setClave(convertirSHA256(a.getClave()));
        alumno.setMail(a.getMail());
        alumno.setDni(a.getDni());
        alumno.setTelefono(a.getTelefono());
        alumno.setActivo(a.getActivo());
        alumno.setRolidrol(rolAlumno);
        if(a.getJornada().isEmpty() || a.getJornada().isBlank()){
            alumno.setTipoJornada("simple");
        }
         if(!(a.getJornada().equalsIgnoreCase("simple") || a.getJornada().equalsIgnoreCase("completa"))){
            throw new FormatoIncorrectoException("La jornada puede ser simple o completa");
        }
        alumno.setTipoJornada(a.getJornada());
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
        
        //asociar el alumno a la escuela
        Escuela escuela=escuelaRepository.findById(e.getIdEscuela()).orElseThrow(()-> new EscuelaNotFoundException("Esa escuela no existe"));
        EscuelaUsuario eu=new EscuelaUsuario();
        eu.setEscuelaIdEscuela(escuela);
        eu.setUsuarioIdUsuario(alumno);
        escuelaUsuarioRepository.save(eu);

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

    public void crearUsuario(AdministrativoDTO a, int rol) {
       Rol r=new Rol();
         r.setIdRol(rol);//id segun lo asignado en bbdd
         
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
            
             if(!usuariorepository.existsByDni(u.getDni())&&
        !usuariorepository.existsByMail(u.getMail())){
             this.usuariorepository.save(u);//solo lo guarda si el dni y mail ingresado NO existen
             this.escuelaUsuarioRepository.save(eu);
        }else{
            throw new UsuarioExistingException("dni o mail ya registrado previamente");
        }
          
            
    }

     public List<NombreCompletoDTO> jefeColegioSinAsignar(){
       return usuariorepository.getJefeColegioWithoutSchool();
        }
     
     public void eliminarUsuario(int idUsuario){
         usuariorepository.deleteById(idUsuario);
     }
     
     public List<InfoUsuarioSegunRolDTO> obtenerUsuarioSegunRol(String nombre,Escuela escuelaIdEscuela){
         //List<Usuario> usuarios = usuariorepository.getUsuarioByRol(nombre);
         /*List<InfoUsuarioSegunRolDTO> lista = new ArrayList<>();
         for (Usuario u : usuarios) {
              InfoUsuarioSegunRolDTO iu = new InfoUsuarioSegunRolDTO
        (u.getIdUsuario(),u.getNombre(), u.getApellido(), u.getDni());
             
         }*///chequeo
         return usuariorepository.getUsuarioByRol(nombre, escuelaIdEscuela);
     }
     
        public List<InfoUsuarioSegunRolDTO> obtenerUsuario(String nombreRol){
         //List<Usuario> usuarios = usuariorepository.getJefeColegio(nombreRol);
         List<InfoUsuarioSegunRolDTO> lista = usuariorepository.getJefeColegio(nombreRol);
         return lista;
       
     }
     
     
     
     public void validarElDto (JefeColegioModificacionDTO j){
    Set<ConstraintViolation<JefeColegioModificacionDTO>> violaciones = validator.validate(j);
    if (!violaciones.isEmpty()) {
        throw new ConstraintViolationException(violaciones);
    }
     //LOGGER.info("EL DTO A VALIDAR TIENE LAS SIGUIENTES PROPIEDADES: "+j.toString());
     }
     
      public void validarElAlumnoDto (AlumnoModificacionDTO j){
    Set<ConstraintViolation<AlumnoModificacionDTO>> violaciones = validator.validate(j);
    if (!violaciones.isEmpty()) {
        throw new ConstraintViolationException(violaciones);
    }
     //LOGGER.info("EL DTO A VALIDAR TIENE LAS SIGUIENTES PROPIEDADES: "+j.toString());
     } 
      
      public void actualizarCampos( JefeColegioModificacionDTO dto, Usuario u){
           LOGGER.info("dto que llega: "+dto.toString());
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

         if (dto.getClave() != null && !dto.getClave().isEmpty()) {
             u.setClave(convertirSHA256(dto.getClave()));
         }
         if (dto.getTelefono() != null) {
             u.setTelefono(dto.getTelefono());
         }
         if (dto.getActivo()==0 || dto.getActivo()==1) {
             u.setActivo(dto.getActivo());
              LOGGER.info("valor ingresado: "+dto.getActivo());
         }else{
             LOGGER.info("valor ingresado: "+dto.getActivo());
         }
         
         //LOGGER.info("el nuevo objeto contiene: "+u.toString());
         
         
     }
      
     public void actualizarCamposAlumno( AlumnoModificacionDTO dto, Usuario u){
         CursoUsuario cu=new CursoUsuario();
         cu.setUsuarioIdUsuario(u);
         
         UsuarioUsuario uu=new UsuarioUsuario();
         uu.setIdUsuarioUsuario(u.getIdUsuario());
         
         
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
          if (dto.getActivo()==0 || dto.getActivo()==1) {
             u.setActivo(dto.getActivo());
             LOGGER.info("valor ingresado: "+dto.getActivo());
         }else{
             LOGGER.info("valor ingresado: "+dto.getActivo());
         }
         
         if(dto.getIdCurso() !=null){
             Curso cursoIdCurso=new Curso();
             cursoIdCurso.setIdCurso(dto.getIdCurso());
             cu.setCursoIdCurso(cursoIdCurso);
         }
         
         if(dto.getIdPadre() !=null){
             Usuario padre=usuariorepository.findById(dto.getIdPadre()).orElseThrow(
                     ()-> new UsuarioNotAuthorizedException("El usuario ingresado como padre no es un padre"));
             uu.setUsuarioIdUsuario1(padre);
             
         }
         
         if(dto.getJornada() !=null){
             u.setTipoJornada(dto.getJornada());
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
    public AlumnoModificacionDTO actualizarAlumno(int id, AlumnoModificacionDTO j) {
    Usuario usuarioIngresado = usuariorepository.findById(id)
            .orElseThrow(() -> new UsuarioNotFoundException("El usuario que se desea modificar no existe"));

        validarElAlumnoDto(j);
        actualizarCamposAlumno(j,usuarioIngresado);
    Usuario actualizado= usuariorepository.save(usuarioIngresado);
     //LOGGER.info("EL OBJETO ACTUALIZADO QUE SE VA A GUARDAR: "+actualizado.toString());
     // Si el alumno tiene un nuevo curso, actualizar la relación CursoUsuario
    if (j.getIdCurso() != null) {
        Curso curso = cursoRepository.findById(j.getIdCurso())
                .orElseThrow(() -> new RuntimeException("El curso no existe")); // Puedes lanzar una excepción específica

        CursoUsuario cursoUsuario = cursoUsuarioRepository.findByUsuarioIdUsuario(usuarioIngresado)
                .orElse(new CursoUsuario()); // Si no existe, crear uno nuevo

        cursoUsuario.setUsuarioIdUsuario(usuarioIngresado);
        cursoUsuario.setCursoIdCurso(curso);

        cursoUsuarioRepository.save(cursoUsuario);
    }

    // Si el alumno tiene un nuevo padre, actualizar la relación UsuarioUsuario
     if (j.getIdPadre() != null) {
        if (j.getIdPadre() > 0) { // Validar que no sea 0
            Usuario padre = usuariorepository.findById(j.getIdPadre())
                    .orElseThrow(() -> new UsuarioNotAuthorizedException("El usuario ingresado como padre no es un padre"));

            UsuarioUsuario usuarioUsuario = uuRepository.findByUsuarioIdUsuario(usuarioIngresado)
                    .orElse(new UsuarioUsuario());

            usuarioUsuario.setUsuarioIdUsuario(usuarioIngresado);
            usuarioUsuario.setUsuarioIdUsuario1(padre);
            uuRepository.save(usuarioUsuario);
        } else {
            // Si el ID del padre es 0, eliminar la relación UsuarioUsuario
            uuRepository.deleteById(usuarioIngresado.getIdUsuario());
        }
        
        
    }
     return new AlumnoModificacionDTO(j.getNombre(), j.getApellido(), j.getDni(),j.getMail(), j.getClave(),j.getTelefono(), j.getActivo(), j.getIdCurso(), j.getIdPadre(), j.getJornada());
    }
    
    
    @Transactional
     public JefeColegioModificacionDTO getUsuarioPorID (int id){
         Optional<Usuario> usuarioIngresado = usuariorepository.findById(id);
         
         return new JefeColegioModificacionDTO (usuarioIngresado.get());
     }
     
     public Usuario buscarUsuario(String mail){
         return usuariorepository.findUsuarioByMail(mail);
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
    
    public List<MateriaCurso> obtenerCursos(Usuario u){
        List<MateriaCurso> cursosProfe=matCursoRepository.findDistinctByProfesor(u); //obtengo todos los id de los cursos
        
        return cursosProfe;
    }
    
    public List<NombreCompletoDTO> obtenerAlumnos (Escuela escuelaIdEscuela){
        List<NombreCompletoDTO> alumnos=usuariorepository.obtenerAlumnos(escuelaIdEscuela);
        return alumnos;
    }
    
    public List<verCursoView> verCursos(List<MateriaCurso> lista){
        List<verCursoView> cursos=new ArrayList<>();
        List<Integer> idCursos=new ArrayList<>();
        verCursoView curso=null;
        lista.forEach(
        (c)->   //obtengo los id de cada curso de la lista del parametro
                idCursos.add(c.getCursoIdCurso().getIdCurso()
        ));
        
         for (Integer idCurso : idCursos) {
        List<verCursoView> cursosPorId = cursoRepository.verCursos(idCurso);
        if (cursosPorId != null) {
            cursos.addAll(cursosPorId); // Agregar todos los resultados encontrados
        }
    }
        
        return cursos;
        
    }
    
      public List<verCursoView> verCursos2(Usuario u){
        List<verCursoView> cursos=matCursoRepository.obtenerCursosProfe(u);    
        return cursos;
        
    }
    
    public List<NombreCompletoDTO> infoUsuarioSegunEscuela(Rol r, Escuela e){
        List<NombreCompletoDTO> usuarios=usuariorepository.obtenerInfoUsuario(r, e);
        return usuarios;
    }
    
    public List<UsuarioView> obtenerHijos(Usuario padre){
        List<UsuarioView> obtenerHijos= uuRepository.obtenerHijos(padre);
        return obtenerHijos;
    }
    
    //ESTE DESPLEGABLE ES PARA CHAT INDIVIDUAL
    public List<DesplegableChatView> infoUsuariosChat(Escuela e, Usuario auth){
        //obtener el rol y en base a eso, ver a que tipos de usuaios le puede enviar mensaje
         List<Integer> roles = new ArrayList<>();
        Rol obtenido=auth.getRolidrol();
        switch(obtenido.getIdRol()){
            //rol administrativo: padres, alumnos
            case 3 ->{ 
                roles.add(6);
                roles.add(7);
            }
            
               //rol preceptor: padres y alumnos 
            case 4 -> {
                roles.add(6);
                roles.add(7);
            }
            
            //rol profesor: padres y alumnos 
            case 5 ->{ 
                roles.add(6);
                roles.add(7);
            }
            
            //rol  padre: preceptor, profesor, administrativo
             case 6 ->{ 
                roles.add(3);
                 roles.add(4);
                roles.add(5);
            }
             
             //rol alumno: preceptor, profesor
              case 7 ->{ 
                roles.add(3);
                 roles.add(4);
            }
              
           
        }
        Set <DesplegableChatView> usuarios= new HashSet<>();
        List<DesplegableChatView> alumnos2=new ArrayList<>();
        //DesplegableChatView alumno=null;
       for (Integer rol : roles) {
        Rol iterado = new Rol();
        iterado.setIdRol(rol);
        
        List<DesplegableChatView> info = usuariorepository.obtenerInfoDesplegables(iterado, e);
        if(iterado.getIdRol()==7){
           alumnos2 = usuariorepository.obtenerAlumnosChat(e);
           for (DesplegableChatView alumno : alumnos2) {
                usuarios.add(alumno); // Asegura que no haya duplicados
            } 
        }
        
         for (DesplegableChatView usuario : info) {
            usuarios.add(usuario); // Asegura que no haya duplicados
        }
        
        
        // Si el rol es alumno, obtener alumnos de los cursos a cargo
        /*if (obtenido.getIdRol() == 5 && rol == 7 || obtenido.getIdRol() == 4 && rol == 7) { // Si el usuario es profesor y está buscando alumnos
            for (verCursoView curso : verCursos) {
                Curso c = new Curso();
                c.setIdCurso(curso.getIdCurso());
                List<DesplegableChatView> alumnos = usuariorepository.obtenerAlumnosProfe(e, c);
                usuarios.addAll(alumnos); // Agregar los alumnos de cada curso
            }
        }*/
    }
    
    return new ArrayList<> (usuarios);
}
    
    //ESTE DESPLEGABLE ES PARA CHAT GRUPAL
     public List<DesplegableChatGrupalView> desplegableChatGrupal(Escuela e, Usuario auth){
        //obtener el rol y en base a eso, ver a que tipos de usuaios le puede enviar mensaje
        Rol obtenido=auth.getRolidrol();
        List<UsuarioView>  alumnos = new ArrayList<>(); 
        List<DesplegableChatGrupalView> desplegables=new ArrayList<>();
        List<MateriaCurso> desplegables2=new ArrayList<>();
        switch(obtenido.getIdRol()){
            //rol administrativo: padres, alumnos
            case 3 ->{ 
                List<verCursoView> obtenerCursos=usuariorepository.obtenerCursos(e);
                for (verCursoView obtenerCurso : obtenerCursos) {
                    Curso c=cursoRepository.findById(obtenerCurso.getIdCurso()).orElseThrow(
                            ()-> new CursoNotFound("El curso no existe"));
                    //LOGGER.info("cursos encontardos: "+c.toString());
                alumnos=usuariorepository.infoAlumnos(c);
                LOGGER.info("cursos encontardos: "+c.getIdCurso());
                if(!alumnos.isEmpty()){
                    //agregar al desplegable los cursos
                    DesplegableChatGrupalView cursos=new DesplegableChatGrupalView();
                    cursos.setIdCurso(c.getIdCurso());
                    cursos.setActivo(c.getActivo());
                    cursos.setDivision(c.getDivision());
                    cursos.setNumero(c.getNumero());
                    
                    desplegables.add(cursos);
                    
                }else{
                    LOGGER.info("lista vacia: ");
                    LOGGER.info("cursos encontardos: "+c.getIdCurso());
                }
                
                }
            }
            
            //rol  profesor:alumnos
            case 5 -> {
                desplegables2 = matCursoRepository.findDistinctByProfesor(auth); //obtengo todos los cursos donde esta el profesor que inicio la sesion
                for (MateriaCurso cursos : desplegables2) {
                    //obtengo todos los alumnos del curso
                    alumnos = usuariorepository.infoAlumnos(cursos.getCursoIdCurso());
                }
                
                //recorro la lista de alumnos
                if(!alumnos.isEmpty()){
                  for (UsuarioView alumno : alumnos) {
                       //agregar al desplegable los alumnos
                        DesplegableChatGrupalView alumnos2 = new DesplegableChatGrupalView();
                        alumnos2.setIdUsuario(alumno.getIdUsuario());

                        desplegables.add(alumnos2);
                    
                }   
                }

            }
            
            //rol padre: profesor, preceptor, administrativo
            case 6 ->
            {
                List<InfoUsuarioSegunRolDTO> profes=usuariorepository.getUsuarioByRol("profesor", e);
                 List<InfoUsuarioSegunRolDTO> preceptor=usuariorepository.getUsuarioByRol("preceptor", e);
                  List<InfoUsuarioSegunRolDTO> adminisrativos=usuariorepository.getUsuarioByRol("administrativo", e);
                  
                  //unifico todo
                  List<InfoUsuarioSegunRolDTO> usuarios = Stream.concat(
                        Stream.concat(profes.stream(), preceptor.stream()),
                        adminisrativos.stream()
                ).collect(Collectors.toList());
                  
                  for (InfoUsuarioSegunRolDTO u : usuarios) {
                    DesplegableChatGrupalView info= new DesplegableChatGrupalView();
                    info.setIdUsuario(u.getIdUsuario());
                    info.setNombre(u.getNombre());
                    info.setApellido(u.getApellido());
                    //guardar en desplegables la lista
                    desplegables.add(info);
                }

            }
            
            //rol alumno: preceptor, profesor
            case 7 -> {
                List<InfoUsuarioSegunRolDTO> profes=usuariorepository.getUsuarioByRol("profesor", e);
                 List<InfoUsuarioSegunRolDTO> preceptor=usuariorepository.getUsuarioByRol("preceptor", e);
                  
                  //unifico todo
                 List<InfoUsuarioSegunRolDTO> usuarios = Stream.concat(
                        profes.stream(),
                        preceptor.stream()
                ).collect(Collectors.toList());

                  for (InfoUsuarioSegunRolDTO u : usuarios) {
                    DesplegableChatGrupalView info= new DesplegableChatGrupalView();
                    info.setIdUsuario(u.getIdUsuario());
                    info.setNombre(u.getNombre());
                    info.setApellido(u.getApellido());
                    //guardar en desplegables la lista
                    desplegables.add(info);
                }
                
            }
           
        }
        return desplegables;
     }
}
    
    
  
    

     


     
   
     
     
        
     
