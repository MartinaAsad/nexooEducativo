
package com.nexo.nexoeducativo.models.dto.request;

import com.nexo.nexoeducativo.models.entities.Usuario;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class EditarMateriaCursoView implements Serializable {
    
    private String dia;
     private LocalTime horaInicio;//poner tipo de dato Time en mysql
    private LocalTime horaFin;
    private Usuario profesor;
    
}
