/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package superAdmin;

import com.nexo.nexoeducativo.models.dto.request.EscuelaDTO;
import com.nexo.nexoeducativo.repository.EscuelaRepository;
import com.nexo.nexoeducativo.repository.PlanRepository;
import com.nexo.nexoeducativo.service.EscuelaService;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SuperAdminEscuelaServiceTest {
    
    @Mock
    private PlanRepository planRepository;

    @Mock
    private EscuelaRepository escuelaRepository;
    
    @InjectMocks
    private EscuelaService escuelaService;
    
    @Autowired 
    private Validator validator;
    
    private EscuelaDTO escuelaDto;
    
    String nombre="San Marcos";
    String direccion="Mariano Acosta 485";
    short activo=0;
    int idPlan=1;
    int idJefe=22;
    
    @Test
    public void altaEscuelaExitosa(){
        EscuelaDTO esperado=new EscuelaDTO(nombre, direccion, activo, idPlan, idJefe);
        when(escuelaRepository.existsByDireccion(direccion)).thenReturn(false);
        when(!planRepository.existsById(idPlan)).thenReturn(true);
        
        
    }
    
}
