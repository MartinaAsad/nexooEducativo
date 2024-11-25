/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JefeColegioModificacionDTO implements Serializable{
    
    private int id;
     @Pattern(regexp = "^[a-zA-Z]+$", message = "campo invalido")//solo acepta letras
    @Length(min=3, max=30) //min: cantidad minima, max: cantidad maxima (de caracteres), LENGTH ES PARA STRING NOMAS
    private String nombre;
    
    @Pattern(regexp = "^[a-zA-Z]+$", message = "formato de apellido es invalido")
    @Length(min=4, max=30)
    private String apellido;
    
    @Length(min = 6, max = 8, message = "El DNI debe tener entre 6 y 8 caracteres")
    private String dni;

    
    //@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Formato de email incorrecto")
    @Email(message="formato de email invalido")
    private String mail;
    
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$",
        message = "La clave debe tener entre 8 y 32 caracteres, al menos una letra mayúscula, una letra minúscula, un número y un carácter especial."
    )
    private String clave;
    
    private int idEscuela;
    

    @Min(value = 0100000, message = "El telefono debe tener al menos 7 numeros")
    @Max(value = 999999999, message = "El telefono debe tener como maximo 9 numeros")
    private Integer telefono;
    
    @Min(value = 0, message = "El valor debe ser 0 o 1")
    @Max(value = 1, message = "El valor debe ser 0 o 1")
    private short activo;
        
}
