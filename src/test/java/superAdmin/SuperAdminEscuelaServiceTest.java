/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superAdmin;

import com.nexo.nexoeducativo.exception.EscuelaNotFoundException;
import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.models.dto.request.UsuarioDTO;
import com.nexo.nexoeducativo.models.entities.Escuela;
import com.nexo.nexoeducativo.models.entities.EscuelaUsuario;
import com.nexo.nexoeducativo.models.entities.Plan;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.EscuelaUsuarioRepository;
import com.nexo.nexoeducativo.repository.PlanRepository;
import com.nexo.nexoeducativo.service.EscuelaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(MockitoExtension.class)
public class SuperAdminEscuelaServiceTest {
    
    @Mock
    private PlanRepository planRepository;

    @Mock
    private EscuelaRepository escuelaRepository;
    
    @Mock
    private EscuelaUsuarioRepository escuelaUsuarioRepository;
    
    @InjectMocks
    private EscuelaService escuelaService;
    
    @Autowired 
    private Validator validator;
    
    private EscuelaDTO escuelaDto;
    
     private static final Logger LOGGER = LoggerFactory.getLogger(SuperAdminEscuelaServiceTest.class);
     
    @BeforeEach
    void setUp() throws Exception{
         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    String nombre="San Marcos";
    String direccion="Avenida La Plata 2334";
    short activo=0;
    int idPlan=1;
    int idJefe=22;
    
    @Test
    public void altaEscuelaExitosa() {
        EscuelaDTO esperado = new EscuelaDTO(nombre, direccion, activo, idPlan, idJefe);
        when(escuelaRepository.existsByDireccion(direccion)).thenReturn(false);
        when(!planRepository.existsById(idPlan)).thenReturn(true);
        when(planRepository.findById(idPlan)).thenReturn(Optional.of(new Plan()));

        escuelaService.crearEscuela(esperado);
        verify(planRepository, times(1)).findById(idPlan);
        verify(escuelaRepository, times(1)).save(any(Escuela.class));
        verify(escuelaUsuarioRepository, times(1)).save(any(EscuelaUsuario.class));

        //argumentCaptor captura un argumento pasado a un metodo para inspeccionarlo
        //es util cuando no podemos acceder al metodo fuera del metodo
        ArgumentCaptor<Escuela> escuelaCaptor = ArgumentCaptor.forClass(Escuela.class);
        verify(escuelaRepository).save(escuelaCaptor.capture());

        Escuela escGuardada = escuelaCaptor.getValue();
        assertEquals(nombre, escGuardada.getNombre());
        assertEquals(direccion, escGuardada.getDireccion());
        assertEquals(activo, escGuardada.getActivo());

        ArgumentCaptor<EscuelaUsuario> escuelaUsuarioCaptor = ArgumentCaptor.forClass(EscuelaUsuario.class);
        verify(escuelaUsuarioRepository).save(escuelaUsuarioCaptor.capture());
        EscuelaUsuario escuelaUsuarioGuardada = escuelaUsuarioCaptor.getValue();
        assertNotNull(escuelaUsuarioGuardada.getEscuelaIdEscuela());
        assertEquals(idJefe, escuelaUsuarioGuardada.getUsuarioIdUsuario().getIdUsuario());
    }
    
     @Test
    public void altaEscuelaConParametrosNulos() throws Exception {
        nombre="";
        direccion="";
        activo=0;
        idPlan=0;
        idJefe=0;
        EscuelaDTO esperado = new EscuelaDTO(nombre, direccion, activo, idPlan, idJefe);
        when(escuelaRepository.existsByDireccion(direccion)).thenReturn(false);
        when(!planRepository.existsById(idPlan)).thenReturn(true);
        when(planRepository.findById(idPlan)).thenReturn(Optional.of(new Plan()));

        escuelaService.crearEscuela(esperado);
        verify(planRepository, times(1)).findById(idPlan);
        verify(escuelaRepository, times(1)).save(any(Escuela.class));
        verify(escuelaUsuarioRepository, times(1)).save(any(EscuelaUsuario.class));

        //argumentCaptor captura un argumento pasado a un metodo para inspeccionarlo
        //es util cuando no podemos acceder al metodo fuera del metodo
        ArgumentCaptor<Escuela> escuelaCaptor = ArgumentCaptor.forClass(Escuela.class);
        verify(escuelaRepository).save(escuelaCaptor.capture());

        Escuela escGuardada = escuelaCaptor.getValue();
        assertEquals(nombre, escGuardada.getNombre());
        assertEquals(direccion, escGuardada.getDireccion());
        assertEquals(activo, escGuardada.getActivo());

        ArgumentCaptor<EscuelaUsuario> escuelaUsuarioCaptor = ArgumentCaptor.forClass(EscuelaUsuario.class);
        verify(escuelaUsuarioRepository).save(escuelaUsuarioCaptor.capture());
        EscuelaUsuario escuelaUsuarioGuardada = escuelaUsuarioCaptor.getValue();
        assertNotNull(escuelaUsuarioGuardada.getEscuelaIdEscuela());
        assertEquals(idJefe, escuelaUsuarioGuardada.getUsuarioIdUsuario().getIdUsuario());
        
         Set<ConstraintViolation<EscuelaDTO>> validaciones = validator.validate(esperado);

         for (ConstraintViolation<EscuelaDTO> validacion : validaciones) {
            LOGGER.error(validacion.getMessage());
        }
         
          assertTrue(validaciones.stream().anyMatch(v -> v.getMessage().contains("campo")), "Se esperaba una violación de validación para campos nulos");
    }
    
    @Test
    public void altaEscuelaConDireccionRepetida() throws Exception {
        nombre = "escuela 3";
        direccion = "santa 2334";
        activo = 0;
        idPlan = 1;
        idJefe = 2;
        EscuelaDTO esperado = new EscuelaDTO(nombre, direccion, activo, idPlan, idJefe);

        when(escuelaRepository.existsByDireccion(direccion)).thenReturn(true);
        EscuelaNotFoundException exception = assertThrows(EscuelaNotFoundException.class,
                () -> escuelaService.crearEscuela(esperado));
        assertEquals("La escuela ya existe en la plataforma", exception.getMessage());

    }
    
    @Test
    public void altaEscuelaConPlanInexistente() throws Exception {
        nombre = "escuela 3";
        direccion = "santa 2334";
        activo = 0;
        idPlan = 6;
        idJefe = 2;
        EscuelaDTO esperado = new EscuelaDTO(nombre, direccion, activo, idPlan, idJefe);

        when(planRepository.existsById(idPlan)).thenReturn(false);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> escuelaService.crearEscuela(esperado));
        assertEquals("El plan seleccionado no existe: " + idPlan, exception.getMessage());

    }

}
