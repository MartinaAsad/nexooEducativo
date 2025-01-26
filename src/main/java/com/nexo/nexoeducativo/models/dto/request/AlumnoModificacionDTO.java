package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class AlumnoModificacionDTO extends JefeColegioModificacionDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "Valor curso invalido")
    @Max(value = 25, message = "Valor curso invalido")
    private Integer idCurso;
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(value = 0, message = "Valor padre invalido")
    private Integer idPadre;
    
}
