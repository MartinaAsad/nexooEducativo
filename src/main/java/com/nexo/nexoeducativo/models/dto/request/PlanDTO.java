/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
public class PlanDTO implements Serializable{
    
    @Pattern(regexp = "^[a-zA-Z-0-9\s]+$", message = "campo descripcion solo debe tener letras")//solo acepta letras
    @NotBlank(message="campo descripcion no puede estar vacio")//notblank para string
    @Length(min=4, max=60, message = "minimo 4 caracteres y maximo 60")
    private String descripcion;
    
    @NotNull(message="campo activo no puede estar vacio")
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1") 
    private short activo;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser un número positivo")
    @NotNull(message="campo precio no puede estar vacio")
    private double precio;
    
   
    
}
