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
public class MensajeView {
    private Integer idMensaje;
    private String contenido;
    private Date fecha;
    private String nombre;
     private String apellido;
    
}
