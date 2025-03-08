package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @Length(min=3, max=8, message="Jornada debe tener entre 3 y 8 letras") //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String jornada;
    
     
    public AlumnoModificacionDTO (String nombre, String apellido, String dni, String mail, String clave, 
            Integer telefono, short activo, Integer idCurso, Integer idPadre, String jornada){
        super(nombre, apellido, dni, mail, clave,telefono, activo);
        this.idCurso=idCurso;
        this.idPadre=idPadre;
        this.jornada=jornada;
    }
    
}
