
package com.nexo.nexoeducativo.models.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class NovedadesDTO {
    
     @Length(min=2, max=255)
    private String contenido;
    
}
