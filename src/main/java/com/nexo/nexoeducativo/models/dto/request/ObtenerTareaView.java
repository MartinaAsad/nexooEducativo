/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Getter
@Setter
public class ObtenerTareaView {
    private Integer idTarea;
    private String descripcion;
    
}
