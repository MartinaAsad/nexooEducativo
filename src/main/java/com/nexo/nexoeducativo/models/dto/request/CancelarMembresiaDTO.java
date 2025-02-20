
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
public class CancelarMembresiaDTO implements Serializable{
    private int id1;
    private short activo1;
    private int id2;
    private short activo2;
    
}
