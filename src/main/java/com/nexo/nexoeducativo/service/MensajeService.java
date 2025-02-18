package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.FormatoIncorrectoException;
import com.nexo.nexoeducativo.exception.RolNotFound;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.MensajeDTO;
import com.nexo.nexoeducativo.models.dto.request.NombreCompletoDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioMensaje;
import com.nexo.nexoeducativo.repository.MensajeRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioMensajeRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martina
 */
@Service
public class MensajeService {
    @Autowired
    private MensajeRepository mensajeRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private UsuarioMensajeRepository umRepository;
    LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    
    
    public void altaInfoPagoMensaje(String info, Escuela escuela){
        Mensaje infoPago = new Mensaje();
                
        int cantLetras=info.length();
        if(cantLetras>255 || cantLetras<10){
            throw new FormatoIncorrectoException("El mensaje debe tener entre 10 y 255 caracteres");
        }   
        infoPago.setContenido(info);
        mensajeRepository.save(infoPago);
        
        //obtengo el id del rol del padre
        Rol rol=rolRepository.findByNombre("padre").orElseThrow(
                ()-> new RolNotFound("No existe ese rol en la abse de datos"));
        
        List<NombreCompletoDTO> padres=usuarioRepository.obtenerInfoUsuario(rol, escuela);
        asignarMensajeAPadres(infoPago, padres);
    }
    
    public void asignarMensajeAPadres (Mensaje m, List<NombreCompletoDTO> padres){
        for(NombreCompletoDTO n:padres){
            //obtengo cada padre
            Usuario u=usuarioRepository.findById(n.getId_usuario()).orElseThrow(
                    ()-> new UsuarioNotFoundException("No existe el id "+n.getId_usuario()));
            
            UsuarioMensaje um=new UsuarioMensaje();
            um.setMensajeIdMensaje(m);
            um.setUsuarioIdUsuario(u);
            umRepository.save(um);
            
            
        }
        
    }
    
    @Transactional
     public void editarMensaje(String nuevoMensaje, Escuela escuela, Rol r){
                  
        int cantLetras=nuevoMensaje.length();
        if(cantLetras>255 || cantLetras<10){
            throw new FormatoIncorrectoException("El mensaje debe tener entre 10 y 255 caracteres");
        }
        
         Mensaje infoPago =umRepository.obtenerMensajesEscuela(escuela, r.getIdRol(), "cbu");
        infoPago.setContenido(nuevoMensaje);
       mensajeRepository.updateContenido(infoPago.getIdMensaje(), nuevoMensaje);
      
        }
     
     public Mensaje altaMensajeIndividual(MensajeDTO mensaje){
          Mensaje m = new Mensaje();
          m.setContenido(mensaje.getContenido());
        if (mensaje.getArchivo().isEmpty() || mensaje.getArchivo().isBlank()) {
            m.setArchivo(mensaje.getArchivo());
        }
        String fechaNueva = hoy.format(formato);
        LocalDateTime actual = LocalDateTime.parse(fechaNueva, formato);
        Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
        m.setFecha(fechaDate);
        
         m = mensajeRepository.save(m);
         
          Usuario comunicador=usuarioRepository.findByMail(mensaje.getComunicador()).orElseThrow(
                    ()-> new UsuarioNotFoundException("No existe el destinatario"));
          
         Usuario destinatario=usuarioRepository.findByMail(mensaje.getDestinatario()).orElseThrow(
                    ()-> new UsuarioNotFoundException("No existe el destinatario"));

        // tabla intermedia
        UsuarioMensaje usuarioMensaje = new UsuarioMensaje();
        usuarioMensaje.setMensajeIdMensaje(m);
        usuarioMensaje.setUsuarioIdUsuario(comunicador);

        UsuarioMensaje usuarioMensajeDestinatario = new UsuarioMensaje();
        usuarioMensajeDestinatario.setMensajeIdMensaje(m);
        usuarioMensajeDestinatario.setUsuarioIdUsuario(destinatario);

        // Retornar el mensaje guardado
        return m;
         
     }
        
    }


