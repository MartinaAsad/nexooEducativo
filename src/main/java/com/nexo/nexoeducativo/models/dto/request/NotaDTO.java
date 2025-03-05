package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    @NotNull
    private int idCurso;    //endpoint /verCursoProfesor
    @NotNull
    private int idTarea;    //endpoint /obtenerTareas
    //@NotNull
    private int idAlumno;   ///verAlumnosCurso/{cursoIdCurso}
   
    private String calificacion;  
    
    public NotaDTO(int idCurso, int idTarea, int idAlumno){
        this.idCurso=idCurso;
        this.idTarea=idTarea;
        this.idAlumno=idAlumno;
    }
}
