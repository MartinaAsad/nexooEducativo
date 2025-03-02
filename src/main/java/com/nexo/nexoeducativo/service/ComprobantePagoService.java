
package com.nexo.nexoeducativo.service;

import com.nexo.nexoeducativo.exception.CursoNotFound;
import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.exception.UsuarioNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.CancelarMembresiaDTO;
import com.nexo.nexoeducativo.models.dto.request.ComprobantePagoDto;
import com.nexo.nexoeducativo.models.dto.request.RenovarMembresiaDTO;
import com.nexo.nexoeducativo.models.dto.request.verCursoView;
import com.nexo.nexoeducativo.models.entities.ComprobantePago;
import com.nexo.nexoeducativo.models.entities.Curso;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaComprobantePago;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioComprobantePago;
import com.nexo.nexoeducativo.repository.ComprobantePagoRepository;
import com.nexo.nexoeducativo.repository.CursoRepository;
import com.nexo.nexoeducativo.repository.EscuelaComprobantePagoRepository;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.UsuarioComprobantePagoRepository;
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
public class ComprobantePagoService {
    LocalDateTime hoy = LocalDateTime.now();
     DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
     
     @Autowired
     private ComprobantePagoRepository cpRepository;
     
     @Autowired
     private UsuarioRepository usuarioRepository;
     
     @Autowired
     private EscuelaRepository escuelaRepository;
     
     @Autowired
     private EscuelaComprobantePagoRepository ecpRepository;
     
     @Autowired
     private UsuarioComprobantePagoRepository ucpRepository;
     
     @Autowired
     private CursoRepository cursoRepository;
    
    public void cuotaPagada(ComprobantePagoDto pago, int idEscuela){
         String fechaNueva=hoy.format(formato);
        LocalDateTime actual=LocalDateTime.parse(fechaNueva, formato);
         String formatoFechaMostrar=mostrarHora(actual);
         Date fechaDate = Date.from(actual.atZone(ZoneId.systemDefault()).toInstant());
         
        ComprobantePago comprobante=new ComprobantePago();
        comprobante.setImporte(pago.getImporte());
        comprobante.setFecha(fechaDate);
        cpRepository.save(comprobante);
        
        //guardar info en las tablas intermedias
        Usuario hijo=usuarioRepository.findById(pago.getIdUsuario())
                .orElseThrow(()-> new UsuarioNotFoundException("El alumno no existe"));
        
        Escuela escuela= escuelaRepository.findById(idEscuela)
                .orElseThrow(()-> new EscuelaNotFoundException("No existe esa escuela"));
        
        EscuelaComprobantePago ecPago=new EscuelaComprobantePago();
        ecPago.setComprobantePagoIdComprobantePago(comprobante);
        ecPago.setEscuelaIdEscuela(escuela);
        ecpRepository.save(ecPago);
        
        UsuarioComprobantePago ucPago=new UsuarioComprobantePago();
        ucPago.setComprobantePagoIdComprobantePago(comprobante);
        ucPago.setUsuarioIdUsuario(hijo);
        ucpRepository.save(ucPago);
        
        //actualizar info para que se vea reflejado el pago exitoso
        hijo.setPagoCuota((short)1);
        usuarioRepository.save(hijo);
        
    }
    
      private String mostrarHora(LocalDateTime actual){
        String hora=actual.format(formato);
        return hora;
        
    }
      
      @Transactional
      public String renovarMembresia(RenovarMembresiaDTO dto, Usuario u, Escuela e){
         // dto.setE(e);
          List<CancelarMembresiaDTO> usuarios=null;
           List<verCursoView> obtenerCursos=null;
          if(dto.isRenovo()){
              String mensaje="Nos comunicaremos a la brevedad para que pueda renovar correctamente su membresia!";
              return mensaje;
          }else{
              //obtengo todos los usuarios de la escuela e info sobre id escuela y si esta activa
              usuarios= usuarioRepository.usuariosEscuela(e);
              for (CancelarMembresiaDTO us : usuarios) {
                  Usuario existente=usuarioRepository.findById(us.getId1()).orElseThrow(()-> new UsuarioNotFoundException("No existe el usuario"));
                  if (existente!=null) {
                      existente.setActivo((short)0);
                  }
                  usuarioRepository.save(existente);
                  
              }
              
              //obtengo informacion sobre los cursos de la escuela
              obtenerCursos=usuarioRepository.obtenerCursos(e);
               for (verCursoView curso : obtenerCursos) {
                  Curso existente=cursoRepository.findById(curso.getIdCurso()).orElseThrow(()-> new CursoNotFound("No existe el curso"));
                  if (existente!=null) {
                      existente.setActivo((short)0);
                  }
                  cursoRepository.save(existente);
                  
              }
              
              //alumnos, escuela y cursos pasan a estar TODOS inactivos
                   
          }
          
          return "Los usuarios registrados estan inactivos debido a que cancelo su menbresia";
          
          
          
      }
    
}
