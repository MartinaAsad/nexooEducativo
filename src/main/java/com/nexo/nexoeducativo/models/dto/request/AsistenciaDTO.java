
package com.nexo.nexoeducativo.models.dto.request;

import java.util.Date;
import java.util.List;


public class AsistenciaDTO {
    Date fechaAsistencia;
    List<UsuarioAsistenciaDTO> alumnosCurso;
}
