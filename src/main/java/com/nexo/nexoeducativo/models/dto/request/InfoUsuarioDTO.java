/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.nexo.nexoeducativo.models.dto.request;


import java.io.Serializable;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;



@Getter
@Setter
public class InfoUsuarioDTO extends UsernamePasswordAuthenticationToken implements Serializable{
   
    private String getNombre;
    private String getApellido;

    public InfoUsuarioDTO(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, String nombre, String apellido) {
        super(principal, credentials, authorities);
        this.getNombre = nombre;
        this.getApellido = apellido;
    }
    
    

    
}

