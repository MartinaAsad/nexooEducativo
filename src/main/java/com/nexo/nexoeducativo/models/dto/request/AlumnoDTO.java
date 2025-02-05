
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Getter
@Setter
public class AlumnoDTO extends UsuarioDTO implements Serializable{
    @NotNull(message="campo curso invalido")
    @Min(value = 0, message = "Valor invalido")
    @Max(value = 25, message = "Valor invalido")
    private int idCurso;
    
    @NotNull(message="campo padre invalido")
    @Min(value = 0, message = "Valor invalido")
    private int idPadre;
    
    @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @NotBlank(message="campo jornada invalido")//notblank para string
    @Length(min=3, max=8) //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String jornada;
    
    
    public AlumnoDTO(String nombre, String apellido, int dni, String eMail, String clave,
            Integer telefono, short activo, int idCurso,int idPadre, String jornada ) {
        super(nombre,apellido,dni,eMail, clave, telefono, activo);
        this.idCurso=idCurso;
        this.idPadre=idPadre;
        this.jornada=jornada;
    }

    
    
}
