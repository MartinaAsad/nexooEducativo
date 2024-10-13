/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author Martina
 */
@Data
public class RolDTO implements Serializable {
    @Pattern(regexp = "^[a-zA-Z\s]+$", message = "campo nombre invalido")//solo acepta letras
    @NotBlank(message="campo nombre invalido")//notblank para string
    @Length(min=4, max=15, message = "el nombre debe tener como minimo 4 caracteres y maximo 15")
    private String nombre;
    
}
