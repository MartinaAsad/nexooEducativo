
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import java.time.LocalTime;
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
public class EditarMateriaCursoView implements Serializable {
    
    private String dia;
     private LocalTime horaInicio;//poner tipo de dato Time en mysql
    private LocalTime horaFin;
    private String nombre;
    private String nombreProfesor;
    private String apellidoProfesor;
    private Integer idMateria;
    
}
