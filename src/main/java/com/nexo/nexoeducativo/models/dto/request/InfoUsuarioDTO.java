/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;


import java.io.Serializable;

/**
 *
 * @author Martina
 */
public interface InfoUsuarioDTO  extends Serializable{
    Integer getId_usuario();
    String getNombre();
    String getApellido();
    Integer getDni();
    
}

