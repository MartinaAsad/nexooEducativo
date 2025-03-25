
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormContactoDTO {
     private Date fecha = new Date(); // Se asigna automáticamente la fecha actual
    @NotBlank(message = "El contenido no puede estar vacío")
    private String contenido;
    
}
