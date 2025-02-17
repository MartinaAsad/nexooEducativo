
package com.nexo.nexoeducativo.models.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class InfoAlumnoCuotaView {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private Double importe;
    private String tipoJornada;
    
}
