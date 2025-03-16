
package com.nexo.nexoeducativo.models.dto.request;

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
    private double importe;
    
}
