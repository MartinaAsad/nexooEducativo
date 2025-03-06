package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class EditarEventoDTO implements Serializable {
    @NotNull(message = "es obligatorio ingresar un evento a editar")
    public Integer idEvento;
    
     //@JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@Length(min=5, max=255, message="minimo 5 caracteres y maximo 255 caracteres")
      private String descripcion;
    
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
      @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime  fecha;
    
}
