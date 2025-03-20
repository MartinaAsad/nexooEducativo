package com.nexo.nexoeducativo.models.dto.request;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@Getter
@Setter
public class SeleccionarProfesorView {
    private Integer cantCursos;
    private Double cantHoras;
    private List<Double> asistencias;
    
}
