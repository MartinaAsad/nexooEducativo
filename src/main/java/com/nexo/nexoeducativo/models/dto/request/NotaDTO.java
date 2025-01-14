package com.nexo.nexoeducativo.models.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotaDTO {
    private int idCurso;    //endpoint linea 366 usuario controller
    private int idTarea;    //endpoint linea 443 usuario controller
    List<NombreCompletoDTO> alumnos;  //endpoint linea 375 usuario controller
    private String calificacion;
    
    
}
