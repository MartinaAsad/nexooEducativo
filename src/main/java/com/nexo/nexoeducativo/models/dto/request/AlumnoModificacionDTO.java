package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
// endpoint verAlumnos para listarlos
public class AlumnoModificacionDTO extends JefeColegioModificacionDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "Valor curso invalido")
    @Max(value = 25, message = "Valor curso invalido")
    private Integer idCurso;    //endpoint: /verCursoAdministrativo
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "Valor padre invalido")
    private Integer idPadre;    // endpoint: /getUsuarios/padre
     
    public AlumnoModificacionDTO (String nombre, String apellido, String dni, String mail, String clave, Integer telefono, short activo, Integer idCurso, Integer idPadre){
        super(nombre, apellido, dni, mail, clave,telefono, activo);
        this.idCurso=idCurso;
        this.idPadre=idPadre;
    }
    
}
