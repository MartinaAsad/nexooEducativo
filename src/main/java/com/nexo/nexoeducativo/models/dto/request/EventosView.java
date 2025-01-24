package com.nexo.nexoeducativo.models.dto.request;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventosView {
    private String descripcion;
    private Date fecha;
     private String fechaFormateada; // Nueva fecha en formato dd-MM-yyyy HH:mm

    public EventosView(String descripcion, Date fecha) {
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    
    
}
