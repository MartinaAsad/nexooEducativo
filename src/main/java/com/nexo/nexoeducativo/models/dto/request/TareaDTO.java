package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class TareaDTO {
    
    @NotNull(message="por favor, indicar un curso")
    private int idCurso;    //endpoint verCursoProfesor
    
    @NotNull (message="por favor, indicar una materia")
    private int idMateria; //endpoint: /selecMateriaProfesor/{cursoIdCurso}
    
    @NotBlank (message="por favor, indicar una descripcion")
    @Length(min=5, max=255, message="minimo 5 caracteres y maximo 255 caracteres")
    private String descripcion;
}
