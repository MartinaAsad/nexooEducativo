/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.Data;


public interface NombreCompletoDTO extends Serializable {
    Integer getId_usuario();
    String getNombre();
    String getApellido();
    
}

