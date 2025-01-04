
package com.nexo.nexoeducativo.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlumnoAsistenciaDTO {
    private Integer idUsuario;
     private short asistio;
    private short mediaFalta;
    private short retiroAntes;
}
