package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
//@Data
@Getter
@Setter
//probar el dto
public class CursoDTO implements Serializable {
     @NotNull(message="campo numero de curso no puede estar vacio")
     @Min(value = 1, message = "1 debe ser el grado minimo")
     @Max(value = 12, message = "12 debe ser el grado maximo")
        private int numero;
     
        @NotNull(message="campo division no puede estar vacio")
        private Character division;
        
        @NotNull(message="campo activo no puede estar vacio")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        @Max(value = 1, message = "El valor debe ser 0 o 1")
        private short activo;
        
        /*esto es de la materia*/
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

    public CursoDTO(int numero, Character division) {
        this.numero = numero;
        this.division = division;
        //this.escuela = escuela;
    }
        
        


    
}
