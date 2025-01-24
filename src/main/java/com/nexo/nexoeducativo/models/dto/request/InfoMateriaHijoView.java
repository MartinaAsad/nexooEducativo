package com.nexo.nexoeducativo.models.dto.request;

import java.util.HashMap;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class InfoMateriaHijoView {
    //private String nombreProfesor;
    private HashMap<String, String> nota; //calificacion y descripcion de la tarea correspondiente
    private List<EventosDTO> eventos;   
    
}
