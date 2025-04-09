/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AsignarPreceptorDTO {
    
     //@Min(value = 100000, message = "El DNI debe tener al menos 6 numeros")
    //@Max(value = 99999999, message = "El DNI debe tener como maximo 8 numeros")
    private int preceptor;
    
    @NotNull
    private int curso;
    
}
