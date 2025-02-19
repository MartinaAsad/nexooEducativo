package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
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
    
}
