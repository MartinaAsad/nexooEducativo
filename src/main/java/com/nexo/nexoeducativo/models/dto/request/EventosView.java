
package com.nexo.nexoeducativo.models.dto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventosView {
     private String descripcion;
    private Date fecha;
    
}
