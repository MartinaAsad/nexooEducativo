
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@Getter
@Setter
public class MateriaDTO implements Serializable{
    @Pattern(regexp = "^[A-Za-z0-9\\s]+$", message = "campo invalido")//solo acepta letras
    @NotBlank(message = "campo nombre invalido")//notblank para string
    @Length(min = 3, max = 30, message="minimo 3 caracteres y maximo 30") //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String nombre;   
}
