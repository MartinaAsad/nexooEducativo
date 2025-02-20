
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
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
public class CancelarMembresiaDTO implements Serializable{
    private Integer id1;
    private short activo1;
    private Integer id2;
    private short activo2;
    
}
