package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.configuration.FailureHandler;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioExistingException;
import com.nexo.nexoeducativo.exception.UsuarioNotAuthorizedException;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.EscuelaModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.JefeColegioModificacionDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreDireccionEscuelaDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Plan;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.EscuelaUsuarioRepository;
import com.nexo.nexoeducativo.repository.PlanRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import static com.nexo.nexoeducativo.service.UsuarioService.convertirSHA256;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
public class EscuelaService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private EscuelaRepository escuelaRepository;

    @Autowired
    private EscuelaUsuarioRepository escuelaUsuarioRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
     @Autowired
    private Validator validator;
     
     private static final Logger LOGGER = LoggerFactory.getLogger(EscuelaService.class);

    public void crearEscuela(EscuelaDTO e) {
        if (escuelaRepository.existsByDireccion(e.getDireccion())) {
            throw new EscuelaNotFoundException("La escuela ya existe en la plataforma");
        }

        if (!planRepository.existsById(e.getIdPlan())) {
            throw new IllegalArgumentException("El plan seleccionado no existe: " + e.getIdPlan());
        }

        Escuela escuela = new Escuela();
        escuela.setNombre(e.getNombre());
        escuela.setDireccion(e.getDireccion());
        escuela.setActivo(e.getActivo());
        escuela.setPlanIdPlan(planRepository.findById(e.getIdPlan()).orElseThrow());

        Usuario jefeColegio = new Usuario();
        jefeColegio.setIdUsuario(e.getJefeColegio());

        EscuelaUsuario escuelaUsuario = new EscuelaUsuario();
        escuelaUsuario.setEscuelaIdEscuela(escuela);
        escuelaUsuario.setUsuarioIdUsuario(jefeColegio);

        escuelaRepository.save(escuela);
        escuelaUsuarioRepository.save(escuelaUsuario);
    }

    public void borrarEscuela(int idEscuela) {
        escuelaRepository.deleteById(idEscuela);
    }

    public List<NombreDireccionEscuelaDTO> obtenerEscuelas() {
        return escuelaRepository.getInfoEscuelas();
    }
    
       public void validarElDto(EscuelaModificacionDTO j) {
        Set<ConstraintViolation<EscuelaModificacionDTO>> violaciones = validator.validate(j);
        if (!violaciones.isEmpty()) {
            throw new ConstraintViolationException(violaciones);
        }
        
    }
       
       public void actualizarCampos( EscuelaModificacionDTO dto, Escuela e, Plan p,int idEscuela){
         if (dto.getNombre() != null) {
             e.setNombre(dto.getNombre());
         }
         
         if (dto.getDireccion() != null && !escuelaRepository.existsByDireccion(dto.getDireccion())) {
             e.setDireccion(dto.getDireccion());
             //LOGGER.info("LA DIRECCION TRAIDA DEL DTO ES: "+dto.getDireccion());
             //LOGGER.info("LA DIRECCION TRAIDA DEL DTO ES: "+dto.getDireccion());
         }else if(dto.getDireccion()==null){
             
         }
         else{
             //LOGGER.info("LA DIRECCION TRAIDA DEL DTO (else) ES: "+dto.getDireccion());
             throw new EscuelaNotFoundException("Ya existe una escuela registrada en esa direccion");
              
         }
        
         if (dto.getIdPlan()!=null) {
             p.setIdPlan(dto.getIdPlan());//SUPUESTAMENTE NO ANDA
             e.setPlanIdPlan(p);
         }
         
         if(dto.getJefeColegio()!=null){
             Usuario u=new Usuario(); //obtener el nuevo jefe colegio
             u.setIdUsuario(dto.getJefeColegio());
             
             Rol r = new Rol();
             r.setIdRol(2);
             //chequear que el jefe colegio ingresado tenga ese rol
             if(usuarioRepository.existsByIdUsuarioAndRolidrol(u.getIdUsuario(), r)){
              
               //ver si esa escuela tenia un jefe colegio asignado previamente
               Optional<EscuelaUsuario> yaExistente=escuelaUsuarioRepository.siHayJefeAsignado(e.getIdEscuela(), r.getIdRol());
               if(yaExistente.isPresent()){
                   Usuario nuevo= new Usuario();
                   nuevo.setIdUsuario(dto.getJefeColegio()); //almacenar el jefe colegio obtenido del dto
                   EscuelaUsuario valorAnterior= yaExistente.get();
                   valorAnterior.setUsuarioIdUsuario(nuevo); //actualziar el jefe colegio anterior por el actual
                   //valorAnterior.setEscuelaIdEscuela(e);
                   escuelaUsuarioRepository.save(valorAnterior);
               }else{//si ese colegio no tiene establecido un jefe colegio de antes, guardarlo desde cero
                   EscuelaUsuario eu = new EscuelaUsuario();
                   eu.setEscuelaIdEscuela(e);
                   eu.setUsuarioIdUsuario(u);
                   escuelaUsuarioRepository.save(eu);
               }
              
             }else{
                 throw new UsuarioNotAuthorizedException("El usuario no tiene permisos como jefe colegio");
             }
             
         }
         
         if (escuelaRepository.findActivoByIdEscuela(idEscuela).getActivo()!=dto.getActivo()) {
             e.setActivo(dto.getActivo());
         }
         
     }
       
        @Transactional
    public EscuelaModificacionDTO actualizarEscuela(int id, EscuelaModificacionDTO e) {
    Escuela escuelaIngresada = escuelaRepository.findById(id)
            .orElseThrow(() -> new EscuelaNotFoundException("La escuela que se desea modificar no existe"));

        validarElDto(e);
        Plan p=new Plan();
        p.setIdPlan(e.getIdPlan());
        
        actualizarCampos(e,escuelaIngresada,p,id);
    Escuela actualizado= escuelaRepository.save(escuelaIngresada);
     //LOGGER.info("EL OBJETO ACTUALIZADO QUE SE VA A GUARDAR: "+actualizado.toString());
     return new EscuelaModificacionDTO (actualizado);
    }
    
    public Escuela obtenerIdEscuela(String mail){
        Usuario usuarioIdUsuario=usuarioRepository.findIdUsuarioByMail(mail);
        //LOGGER.info("id usuario logueado: "+usuarioIdUsuario.getIdUsuario());
        EscuelaUsuario eu= escuelaUsuarioRepository.findEscuelaIdEscuelaByUsuarioIdUsuario(usuarioIdUsuario);
        Escuela e=eu.getEscuelaIdEscuela();
        //LOGGER.info("id escuela del usuario logueado: "+e.getIdEscuela());
        return e;
    }

}
