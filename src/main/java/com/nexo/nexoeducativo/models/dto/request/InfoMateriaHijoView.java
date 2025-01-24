package com.nexo.nexoeducativo.models.dto.request;

import java.util.List;

/**
 *
 * @author Martina
 */
public class InfoMateriaHijoView {
    private String nombreProfesor;
    private List<String> nota; //calificacion y descripcion de la tarea correspondiente
    private List<EventosView> eventos;   
    
}
