
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

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
    
    
    public AlumnoDTO(String nombre, String apellido, int dni, String eMail, String clave,
            Integer telefono, short activo, int idCurso,int idPadre ) {
        super(nombre,apellido,dni,eMail, clave, telefono, activo);
        this.idCurso=idCurso;
        this.idPadre=idPadre;
    }

    
    
}
