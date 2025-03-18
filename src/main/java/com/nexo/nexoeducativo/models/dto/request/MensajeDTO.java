
package com.nexo.nexoeducativo.models.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */

@Data
@NoArgsConstructor
public class MensajeDTO {
    private Integer idMensaje;
    private String contenido;
    private String remitenteMail;
}