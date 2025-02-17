
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class ComprobantePagoDto {  
    @NotNull
    @Min(value=1)
    private int idUsuario;
    
    @NotNull
    private double importe;
    
}
