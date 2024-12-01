/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superAdmin;

import com.nexo.nexoeducativo.exception.EscuelaExistingException;
import com.nexo.nexoeducativo.exception.UsuarioExistingException;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Rol;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.UsuarioService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import static java.lang.Math.log;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class SuperAdminUsuarioServiceTest {
    
    @Mock //simula el objeto UsuarioRepository, con los service NO se pone
    private UsuarioRepository usuarioRepository;
    
    @InjectMocks //crea una instancia e inyecta todo lo de @Mock
    private UsuarioService usuarioService;
    
    @Autowired 
    private Validator validator;
    
    private UsuarioDTO UsuarioDTO;
    
    String nombre="Prueba";
    String apellido="Unitaria";
    String mail="pruebaunitaria1@gmail.com";
    String clave="PruebitaU123@";
    int telefono=25803369;
    int dni=40123699;
    short activo=1;
    Rol r=new Rol();
     private static final Logger LOGGER = LoggerFactory.getLogger(SuperAdminUsuarioServiceTest.class);
    @BeforeEach
    void setUp() throws Exception{
          r.setIdRol(2);
         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    //lo que hay que hacer ahora es simular la logica del metodo y para eso, ver en el metodo
    //lo que esta fuera de la logica (repositorys por ejemplo)
    @Test
    public void altaJefeColegioCorrectamente() throws Exception{
        UsuarioDTO esperado=new UsuarioDTO(nombre,apellido, dni, mail, clave, telefono, activo, r.getIdRol());
        when(usuarioRepository.existsByDni(dni)).thenReturn(false);
        when(usuarioRepository.existsByMail(mail)).thenReturn(false);
        
        usuarioService.crearUsuario(esperado);
        
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }
    
    @Test
    public void altaJefeColegioConMailRepetido() throws Exception
    {
        mail="mail2@gmail.com";//ya existe este mail en la bbdd
       UsuarioDTO esperado = new UsuarioDTO(nombre, apellido, dni, mail, clave, telefono, activo, r.getIdRol());
        when(usuarioRepository.existsByDni(dni)).thenReturn(false);
        when(usuarioRepository.existsByMail(mail)).thenReturn(true);

        //usuarioService.crearUsuario(esperado);
        Exception excepcion = assertThrows(UsuarioExistingException.class,()-> 
                usuarioService.crearUsuario(esperado));
        String mensajeEsperado="dni o mail ya registrado previamente";
        String mensajeActual=excepcion.getMessage();
        
        assertTrue(mensajeActual.contains(mensajeEsperado));
        verify(usuarioRepository, times(0)).save(any(Usuario.class)); 
        
    }
    
    @Test
    public void altaJefeColegioConDniRepetido() throws Exception
    {
        dni=365896;//ya existe este dni en la bbdd
       UsuarioDTO esperado = new UsuarioDTO(nombre, apellido, dni, mail, clave, telefono, activo, r.getIdRol());
        when(usuarioRepository.existsByDni(dni)).thenReturn(true);
        //when(usuarioRepository.existsByMail(mail)).thenReturn(false);

        //usuarioService.crearUsuario(esperado);
        Exception excepcion = assertThrows(UsuarioExistingException.class,()-> 
                usuarioService.crearUsuario(esperado));
        String mensajeEsperado="dni o mail ya registrado previamente";
        String mensajeActual=excepcion.getMessage();
        
        assertTrue(mensajeActual.contains(mensajeEsperado));
        verify(usuarioRepository, times(0)).save(any(Usuario.class)); 
        
    }
    
    @Test
    public void altaJefeColegioConValoresNulos() throws Exception {
        nombre = null;
        apellido = null;
        dni = 0;
        mail = null;
        clave = null;
        telefono = 0;
        activo = 0;
        r.setIdRol(0);
        UsuarioDTO esperado = new UsuarioDTO(nombre, apellido, dni, mail, clave, telefono, activo, r.getIdRol());
        Set<ConstraintViolation<UsuarioDTO>> validaciones = validator.validate(esperado);
        for (ConstraintViolation<UsuarioDTO> validacion : validaciones) {
            LOGGER.error(validacion.getMessage());
        }
        assertTrue(validaciones.stream().anyMatch(v -> v.getMessage().contains("vacio")), "Se esperaba una violación de validación para campos nulos");

        //verify(usuarioRepository, times(0)).save(any(Usuario.class)); 
    }
    
    

}
