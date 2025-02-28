package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class RenovarMembresiaDTO implements Serializable{
    /*@NotNull(message = "Ingrese una escuela")
    private Escuela e;*/
    
    // @NotNull(message = "Ingrese un plan")
    private int idPlan; 
     
      @NotNull(message = "Ingrese una respuesta valida")
    private boolean renovo;
    
}
