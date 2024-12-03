/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Sebastian
 */
@Getter
@Setter
@AllArgsConstructor
public class EscuelaView implements Serializable{
    private String nombre;
    private String direccion;
    
}
