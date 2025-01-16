/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
//@Data
@Getter
@Setter
//probar el dto
public class CursoDTO implements Serializable {
     @NotNull(message="campo numero de curso no puede estar vacio")
     @Min(value = 1, message = "1 debe ser el grado minimo")
     @Max(value = 12, message = "12 debe ser el grado maximo")
        private int numero;
     
        @NotNull(message="campo division no puede estar vacio")
        private Character division;
        
        @NotNull(message="campo activo no puede estar vacio")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        @Max(value = 1, message = "El valor debe ser 0 o 1")
        private short activo;
        
        /*@NotNull(message="campo activo no puede estar vacio")
        @Min(value = 1, message = "El valor debe ser igual o mayor a 1")
        private int escuela;*/

    public CursoDTO(int numero, Character division) {
        this.numero = numero;
        this.division = division;
        //this.escuela = escuela;
    }
        
        


    
}
