package com.nexo.nexoeducativo.models.dto.request;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
public class CursoRequest {
    private CursoDTO r;
     List<AgregarInfoMateriaDTO> m;
    
}
