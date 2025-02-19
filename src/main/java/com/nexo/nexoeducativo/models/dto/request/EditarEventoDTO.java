package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class EditarEventoDTO implements Serializable {
    @NotNull(message = "es obligatorio ingresar un evento a editar")
    public Integer idEvento;
    
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    @Length(min=5, max=255, message="minimo 5 caracteres y maximo 255 caracteres")
      private String descripcion;
    
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
      @JsonFormat(pattern="dd-MM-yyyy HH:mm")
    private Date fecha;
    
}
