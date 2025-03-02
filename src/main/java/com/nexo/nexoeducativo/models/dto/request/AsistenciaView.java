package com.nexo.nexoeducativo.models.dto.request;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class AsistenciaView {
    
    public Date fecha;
    public Integer idAsistencia;
}
