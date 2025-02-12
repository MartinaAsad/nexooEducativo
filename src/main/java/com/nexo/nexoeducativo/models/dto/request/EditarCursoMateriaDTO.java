package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
public class EditarCursoMateriaDTO implements Serializable {
     @NotNull(message = "la materia debe estar asociada a un curso")
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private int idCurso;    //endpoint: verCursoAdministrativo
   
      @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private int idMateria;  //endpoint: verMaterias
   
       @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
    private Integer idProfesor; //endpoint: verProfesAdministrativo 
    
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES   
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @Length(min = 5, max = 9, message="minimo 5 letras y maximo 9") //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String dia;
     
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    private LocalTime horaInicio;
    
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
     private LocalTime horaFin;
    
}
