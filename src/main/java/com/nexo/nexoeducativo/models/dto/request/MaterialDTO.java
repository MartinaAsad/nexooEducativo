package com.nexo.nexoeducativo.models.dto.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MaterialDTO {

    @NotNull(message = "Ingrese una materia")
    private int idMateria; //endpoint /selecMateriaProfesor/{cursoIdCurso}

    @NotNull(message = "Ingrese un curso")
    private int idCurso; //endpoint /verCursoProfesor

    @Length(min = 5, max = 255, message = "minimo 5 caracteres y maximo 255 caracteres")
    private String descripcion;

    /*@NotNull(message = "por favor, colocar un archivo")
    private MultipartFile urlArchivo;*/

}
