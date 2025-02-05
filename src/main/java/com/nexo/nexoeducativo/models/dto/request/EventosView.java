
package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
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
public class EventosView {
     @NotBlank(message="descripcion no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "formato de descripcion es invalido")
    @Length(min=4, max=80, message="la descripcion debe tener entre 4 y 80 caracteres")
     private String descripcion;
     
     @NotNull(message="Ingresar una fecha")
     @JsonFormat(pattern="dd-MM-yyyy HH:mm")

    private Date fecha;
    
}
