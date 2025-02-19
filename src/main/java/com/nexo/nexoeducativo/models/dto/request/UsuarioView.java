package com.nexo.nexoeducativo.models.dto.request;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioView implements Serializable{
    private int idUsuario;
    private String nombre;
    private String apellido;

    public UsuarioView(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
}
