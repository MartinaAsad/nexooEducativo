
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author Martina
 */
@Data
@EqualsAndHashCode
public class DesplegableChatView implements Serializable {
     public Integer id_usuario;
     public String nombre;
     public String apellido;
     public String mail;
     
     
}


