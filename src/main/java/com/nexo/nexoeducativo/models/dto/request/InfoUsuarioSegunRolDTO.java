/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Martina
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoUsuarioSegunRolDTO {
    // u.id_usuario, u.nombre, u.apellido, u.dni
    private int idUsuario;
    private String nombre;
    private String apellido;
    private int dni;
    
    
}
