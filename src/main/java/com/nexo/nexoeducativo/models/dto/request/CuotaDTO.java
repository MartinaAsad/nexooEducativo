
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class CuotaDTO {
    
     @NotNull(message="campo monto invalido")
    public Double monto;
    
     @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @NotBlank(message="campo jornada invalido")//notblank para string
    @Length(min=6, max=8, message="la jornada puede ser simple o completa") //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    public String jornada;
    
}
