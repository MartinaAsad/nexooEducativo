package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;

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
