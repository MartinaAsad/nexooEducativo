
package com.nexo.nexoeducativo.models.dto.request;

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
public class MensajeDTO {
   private Integer idMensaje;
    private String contenido;
    private String mail;
    private String mail2;
}