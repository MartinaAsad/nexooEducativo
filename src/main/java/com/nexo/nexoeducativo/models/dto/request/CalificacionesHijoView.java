
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class CalificacionesHijoView implements Serializable {
    private String descripcion;
    private String nota;
    private String nombre;
    private String nombreP;
    private String apellidoP;
    
    
}
