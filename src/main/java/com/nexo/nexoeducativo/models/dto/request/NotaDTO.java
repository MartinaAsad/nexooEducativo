package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    @NotNull
    private int idCurso;    //endpoint /verCursoProfesor
    @NotNull
    private int idTarea;    //endpoint /obtenerTareas/{cursoIdCurso}
    @NotNull
    private int idAlumno;   ///verAlumnosCurso/{cursoIdCurso}
   
    private String calificacion;  
    
    public NotaDTO(int idCurso, int idTarea, int idAlumno){
        this.idCurso=idCurso;
        this.idTarea=idTarea;
        this.idAlumno=idAlumno;
    }
}
