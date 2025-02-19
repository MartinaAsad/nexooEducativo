
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventosDTO {
    
    @NotBlank (message="por favor, indicar una descripcion")
    @Length(min=5, max=255, message="minimo 5 caracteres y maximo 255 caracteres")
      private String descripcion;
    
     @NotBlank(message = "campo fecha invalido")
    private String fecha;
    
}
