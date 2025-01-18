package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
public class MaterialDTO {

    @NotNull(message = "Ingrese una materia")
    private int idMateria; //endpoint /selecMateriaProfesor/{cursoIdCurso}

    @NotNull(message = "Ingrese un curso")
    private int idCurso; //endpoint /verCursoProfesor

    @Length(min = 5, max = 255, message = "minimo 5 caracteres y maximo 255 caracteres")
    private String descripcion;

    @NotBlank(message = "por favor, colocar un archivo")
    private MultipartFile urlArchivo;

}
