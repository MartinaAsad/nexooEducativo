/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;

/**
 *
 * @author Martina
 */
public class AuthRequestDTO extends UsuarioDTO implements Serializable {
    public AuthRequestDTO(String eMail, String clave){
       super(eMail,clave);
    }
}
