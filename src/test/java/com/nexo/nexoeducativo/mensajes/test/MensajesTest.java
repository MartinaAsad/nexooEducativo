package com.nexo.nexoeducativo.mensajes.test;

import com.nexo.nexoeducativo.controller.MensajeController;
import com.nexo.nexoeducativo.models.dto.request.MensajeIndividualDTO;
import com.nexo.nexoeducativo.models.dto.request.MensajeView;
import com.nexo.nexoeducativo.models.entities.Mensaje;
import com.nexo.nexoeducativo.models.entities.Usuario;
import com.nexo.nexoeducativo.models.entities.UsuarioMensaje;
import com.nexo.nexoeducativo.repository.MensajeRepository;
import com.nexo.nexoeducativo.repository.RolRepository;
import com.nexo.nexoeducativo.repository.UsuarioMensajeRepository;
import com.nexo.nexoeducativo.repository.UsuarioRepository;
import com.nexo.nexoeducativo.service.CursoService;
import com.nexo.nexoeducativo.service.EscuelaService;
import com.nexo.nexoeducativo.service.LoginService;
import com.nexo.nexoeducativo.service.MensajeService;
import com.nexo.nexoeducativo.service.RolService;
import com.nexo.nexoeducativo.service.UsuarioService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 *
 * @author Martina
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MensajesTest {
    private MockMvc mockMvc;

    @MockBean //para evitar error de ejecucuon
    private MensajeService mensajeService;

    @MockBean
    private EscuelaService escuelaService;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private LoginService loginService;
    
    @MockBean
    private RolService rolService;
    
    @MockBean
    private CursoService cursoService;
            
    @MockBean
    private Authentication authentication;
    
    @MockBean
    private UsuarioRepository usuarioRepository;
    
    
    @MockBean
    private MensajeRepository mensajeRepository;
    
     @MockBean
    private UsuarioMensajeRepository umRepository;
     
     @MockBean
    private RolRepository rolRepository;

    @InjectMocks
    private MensajeController mensajeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mensajeController).build();
    }

    @Test
    void nuevoMensaje_Exito() throws Exception {
           String comunicadorMail = "martina@gmail.com";
        Integer destinatarioId = 28;
        MensajeIndividualDTO mensajeDTO = new MensajeIndividualDTO();
        mensajeDTO.setContenido("Mensaje de prueba");
        mensajeDTO.setDestinatario(destinatarioId);

        Usuario comunicador = new Usuario();
        comunicador.setMail(comunicadorMail);

        Usuario destinatario = new Usuario();
        destinatario.setIdUsuario(destinatarioId);

        Mensaje mensajeGuardado = new Mensaje();
        mensajeGuardado.setContenido(mensajeDTO.getContenido());

        when(usuarioRepository.findByMail(comunicadorMail)).thenReturn(Optional.of(comunicador));
        when(usuarioRepository.findById(destinatarioId)).thenReturn(Optional.of(destinatario));
        when(mensajeRepository.save(any(Mensaje.class))).thenReturn(mensajeGuardado);


        Mensaje resultado = mensajeService.altaMensajeIndividual(mensajeDTO, comunicadorMail);

        // Assert
        assertNotNull(resultado, "El mensaje retornado no debe ser nulo"+resultado.toString());
        assertEquals(mensajeDTO.getContenido(), resultado.getContenido());

        // Verifying the interactions with the mocked repositories
        verify(mensajeRepository).save(any(Mensaje.class));
        verify(usuarioRepository).findByMail(comunicadorMail);
        verify(usuarioRepository).findById(destinatarioId);
        verify(umRepository, times(2)).save(any(UsuarioMensaje.class));
    }
    

    @Test
    void nuevoMensaje_Fallo() throws Exception {
        when(authentication.getPrincipal()).thenReturn("usuario@example.com");
        when(mensajeService.altaMensajeIndividual(any(), anyString())).thenReturn(null);

        mockMvc.perform(post("/nuevoMensaje")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .principal(authentication))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Mensaje no enviado"));
    }

    @Test
    void obtenerMensaje_Exito() throws Exception {
        List<MensajeView> mensajes = Arrays.asList(new MensajeView());
        when(authentication.getPrincipal()).thenReturn("usuario@example.com");
        when(mensajeService.obtenerMensajes(anyString())).thenReturn(mensajes);

        mockMvc.perform(get("/obtenerMensajes").principal(authentication))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void obtenerMensaje_Fallo() throws Exception {
        when(authentication.getPrincipal()).thenReturn("usuario@example.com");
        when(mensajeService.obtenerMensajes(anyString())).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/obtenerMensajes").principal(authentication))
                .andExpect(status().isNoContent());
    }

    
}
