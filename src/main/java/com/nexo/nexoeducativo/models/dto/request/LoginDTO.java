/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Martina
 */
@Getter
@Setter
public class LoginDTO extends UsuarioDTO implements Serializable {
    public LoginDTO(String eMail, String clave){
        super(eMail, clave);
    }
    
}
