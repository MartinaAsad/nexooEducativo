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
    private int idCurso;
    
    @NotNull (message="por favor, indicar una materia")
    private int idMateria;
    
    @NotBlank (message="por favor, indicar una descripcion")
    @Length(min=5, max=255, message="minimo 5 caracteres y maximo 255 caracteres")
    private String descripcion;
    
    private String archivo;//se coloca la url
    
    @NotBlank (message="por favor, indicar una calificacion")
    @Length(min=1, max=15, message="minimo 1 caracter y maximo 15 caracteres")
    private String calificacion;
}
