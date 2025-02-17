package com.nexo.nexoeducativo.models.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class EditarCursoDTO implements Serializable{
     @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
        private int numero;
     
      @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
        private Character division;
        
       @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        @Max(value = 1, message = "El valor debe ser 0 o 1")
        private short activo;
       
        @JsonInclude(JsonInclude.Include.NON_NULL)//PERMITE IGNORAR LOS VALORES
      private List<EditarCursoMateriaDTO> materias;
    
}
