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
public class AdministrativoDTO extends UsuarioDTO implements Serializable{
    
    private int idEscuela;
    
    public AdministrativoDTO(String nombre, String apellido, int dni, String eMail, String clave,
            Integer telefono, short activo, int idEscuela ) {
        super(nombre,apellido,dni,eMail, clave, telefono, activo);
        this.idEscuela=idEscuela;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }
    
}
