
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;


public interface NombreCompletoDTO extends Serializable {
    Integer getId_usuario();
    String getNombre();
    String getApellido();
    
}

