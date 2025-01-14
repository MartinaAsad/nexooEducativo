
package com.nexo.nexoeducativo.models.dto.request;

import java.util.Date;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AsistenciaDTO {
   // Date fechaAsistencia;
    List<AlumnoAsistenciaDTO> alumnosCurso;
    //linea 365 a 388 endpoints necesarios para este metodo
}
