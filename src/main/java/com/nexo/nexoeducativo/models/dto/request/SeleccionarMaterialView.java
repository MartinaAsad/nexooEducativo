
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Martina
 */
@Getter
@Setter
@AllArgsConstructor
public class SeleccionarMaterialView implements Serializable{
    private int idMaterial;
    private String descripcion;
    
}
