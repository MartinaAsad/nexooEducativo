
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

    /*@NotNull(message = "la materia debe estar asociada a una escuela")
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private int idEscuela;

    @NotNull(message = "la materia debe estar asociada a un curso")
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private int idCurso;    //endpoint: verCursoAdministrativo
    
    @NotNull(message = "la materia debe estar asociada a un curso")
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private int idProfesor; //endpoint: verProfesAdministrativo
    
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @NotBlank(message = "campo dia invalido")//notblank para string
    @Length(min = 5, max = 9, message="minimo 5 letras y maximo 9") //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String dia;
    
    @NotNull(message = "campo fecha inicio invalido")//notblank para string
    private LocalTime horaInicio;
    
     @NotNull(message = "campo fecha fin invalido")//notblank para string
    private LocalTime horaFin;*/
    
}
